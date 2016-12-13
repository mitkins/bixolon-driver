package hr.istratech.bixolon.driver.general;


import android.graphics.Bitmap;
import android.graphics.Color;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author ksaric
 */

class RasterPrinter implements Printer {

    private final Collection<ControlSequence> controlSequences;
    private final Bitmap bitmap;

	private RasterPrinter(final List<ControlSequence> controlSequences, final Bitmap bitmap ) {
        this.bitmap = bitmap;
        this.controlSequences = Collections.unmodifiableCollection( controlSequences );
    }

    public static Printer create(final List<ControlSequence> controlSequences, final Bitmap bitmap) {
        return new RasterPrinter( controlSequences, bitmap );
    }

    @Override
    public byte[] getCommand() {
        return print( bitmap );
    }

	private final byte[] INITIALIZE_PRINTER = new byte[]{0x1B,0x40};

	private final byte[] PRINT_AND_FEED_PAPER = new byte[]{0x0A};

	private final byte[] SELECT_BIT_IMAGE_MODE = new byte[]{(byte)0x1B, (byte)0x2A};
	private final byte[] SET_LINE_SPACING = new byte[]{0x1B, 0x33};
	//private final byte[] TEST = new byte[]{0x1B, 0x2A, 0x0, 0x8, 0x0, (byte)128, (byte)64, (byte)32, (byte)16, (byte)8, (byte)4, (byte)2, (byte)1, (byte)0};
	//private final byte[] TEST = new byte[]{0x1B, 0x2A, 0x0, 0x8, 0x0, (byte)238, (byte)42, (byte)59, (byte)0, (byte)255, (byte)255, (byte)2, (byte)1, (byte)0};
	private final byte[] TEST = new byte[]{0x1B, 0x2A, (byte)33, 0x1, 0x0, (byte)255, (byte)0, (byte)255, (byte)255, (byte)0, (byte)255, (byte)255, (byte)0, (byte)255};

	private FileOutputStream printOutput;

	public int maxBitsWidth = 255;

	private byte[] buildPOSCommand(byte[] command, byte... args) {
		byte[] posCommand = new byte[command.length + args.length];

		System.arraycopy(command, 0, posCommand, 0, command.length);
		System.arraycopy(args, 0, posCommand, command.length, args.length);

		return posCommand;
	}

	private BitSet getBitsImageData(Bitmap image) {
		int threshold = 127;
		int index = 0;
		int width = image.getWidth();
		if ( width > 384 ) width = 384;
		int dimenssions = width * image.getHeight();
		BitSet imageBitsData = new BitSet(dimenssions);

		for (int y = 0; y < image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
			{
				int color = bitmap.getPixel( x, y );
				int  red = Color.red(color);
				int  green = Color.green(color);
				int  blue = Color.blue(color);
				int luminance = (int)(red * 0.3 + green * 0.59 + blue * 0.11);
				//dots[index] = (luminance < threshold);
				imageBitsData.set(index, (luminance < threshold));
				index++;
			}
		}

		return imageBitsData;
	}

	public void printImage(Bitmap image, ByteBuffer buffer) {
		BitSet imageBits = getBitsImageData(image);

		int width = image.getWidth();
		if ( width > 384 ) width = 384;

		byte widthLSB = (byte)(width & 0xFF);
		byte widthMSB = (byte)((width >> 8) & 0xFF);

		// COMMANDS
		byte[] selectBitImageModeCommand = buildPOSCommand(SELECT_BIT_IMAGE_MODE, (byte) 33, widthLSB, widthMSB);
		byte[] setLineSpacing24Dots = buildPOSCommand(SET_LINE_SPACING, (byte) 24);
		byte[] setLineSpacing30Dots = buildPOSCommand(SET_LINE_SPACING, (byte) 30);

		buffer.put(INITIALIZE_PRINTER);
		buffer.put(setLineSpacing24Dots);
		//buffer.put(TEST);

		int offset = 0;
		while (offset < image.getHeight()) {
			buffer.put(selectBitImageModeCommand);

			int imageDataLineIndex = 0;
			byte[] imageDataLine = new byte[3 * width];

			for (int x = 0; x < width; ++x) {

				// Remember, 24 dots = 24 bits = 3 bytes.
				// The 'k' variable keeps track of which of those
				// three bytes that we're currently scribbling into.
				for (int k = 0; k < 3; ++k) {
					byte slice = 0;

					// A byte is 8 bits. The 'b' variable keeps track
					// of which bit in the byte we're recording.
					for (int b = 0; b < 8; ++b) {
						// Calculate the y position that we're currently
						// trying to draw. We take our offset, divide it
						// by 8 so we're talking about the y offset in
						// terms of bytes, add our current 'k' byte
						// offset to that, multiple by 8 to get it in terms
						// of bits again, and add our bit offset to it.
						int y = (((offset / 8) + k) * 8) + b;

						// Calculate the location of the pixel we want in the bit array.
						// It'll be at (y * width) + x.
						int i = (y * width) + x;

						// If the image (or this stripe of the image)
						// is shorter than 24 dots, pad with zero.
						boolean v = false;
						if (i < imageBits.length()) {
							v = imageBits.get(i);
						}
						// Finally, store our bit in the byte that we're currently
						// scribbling to. Our current 'b' is actually the exact
						// opposite of where we want it to be in the byte, so
						// subtract it from 7, shift our bit into place in a temp
						// byte, and OR it with the target byte to get it into there.
						slice |= (byte) ((v ? 1 : 0) << (7 - b));
					}

					imageDataLine[imageDataLineIndex + k] = slice;
				}

				imageDataLineIndex += 3;
			}

			buffer.put(imageDataLine);
			offset += 24;
			buffer.put(PRINT_AND_FEED_PAPER);
		}

		buffer.put(setLineSpacing30Dots);
	}

    protected byte[] print( final Bitmap bitmap ) {
        //ByteArrayOutputStream stream = new ByteArrayOutputStream();
        //bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);

        //final byte[] bytes = stream.toByteArray();

		int width = bitmap.getWidth();
		if ( width > 384 ) width = 384;

		int verticalBytes = (int)Math.ceil( bitmap.getHeight() / 8 );
		int lines = bitmap.getHeight() / 24;
		int imageBytes = (verticalBytes * width) + ( PRINT_AND_FEED_PAPER.length * lines );

        //double widthInBytes = Math.ceil( bitmap.getScaledWidth(DisplayMetrics.DENSITY_MEDIUM) / 8 );

        // get message size
        //Integer messageSize = 6 + (int)widthInBytes * bitmap.getScaledHeight( DisplayMetrics.DENSITY_MEDIUM);
		//Integer messageSize = 6 + (int)bitmap.getWidth() * bitmap.getHeight();
		//Integer messageSize = 9 + TEST.length;
		Integer messageSize = INITIALIZE_PRINTER.length +
			SET_LINE_SPACING.length + 1 +
			SELECT_BIT_IMAGE_MODE.length + 3 +
			imageBytes +
			SET_LINE_SPACING.length + 1 +
			1000;


//        for ( final ControlSequence controlSequence : controlSequences ) {
//            messageSize += controlSequence.getCommand().length;
//        }

        // create a buffer from message size
        final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

        // put the instructions
        //for ( final ControlSequence controlSequence : controlSequences ) {
        //    buffer.put( controlSequence.getCommand() );
        //}

        //print_image( bitmap, buffer );

		//byte[] printBitmap = decodeBitmap( bitmap );
		//buffer.put( printBitmap );

        //int width = bitmap.getWidth();
        //int height = bitmap.getHeight();

		printImage( bitmap, buffer );

		// xL
        //buffer.put( (byte) (width & 0xFF) );

        // xH
        //buffer.put( (byte) ((width >> 8) & 0xFF) );

        // xL
        //buffer.put( (byte) (height & 0xFF) );

        // xH
        //buffer.put( (byte) ((height >> 8) & 0xFF) );

        // put the bitmap
        //buffer.put( bytes );
        //bitmap.copyPixelsToBuffer( buffer );

        // print the buffer out
        //buffer.put( Print.PRINT_LINE_FEED.getCommand() );

        return buffer.array();
    }
}
