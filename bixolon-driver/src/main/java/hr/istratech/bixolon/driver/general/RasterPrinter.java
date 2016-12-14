package hr.istratech.bixolon.driver.general;


import android.graphics.Bitmap;
import android.graphics.Color;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hr.istratech.bixolon.driver.command.print.Print;
import hr.istratech.bixolon.driver.command.raster.LineSpacing;

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

	private final byte[] SELECT_BIT_IMAGE_MODE = new byte[]{(byte)0x1B, (byte)0x2A};
	//private final byte[] TEST = new byte[]{0x1B, 0x2A, 0x0, 0x8, 0x0, (byte)128, (byte)64, (byte)32, (byte)16, (byte)8, (byte)4, (byte)2, (byte)1, (byte)0};
	//private final byte[] TEST = new byte[]{0x1B, 0x2A, 0x0, 0x8, 0x0, (byte)238, (byte)42, (byte)59, (byte)0, (byte)255, (byte)255, (byte)2, (byte)1, (byte)0};
	//private final byte[] TEST = new byte[]{0x1B, 0x2A, (byte)33, 0x1, 0x0, (byte)255, (byte)0, (byte)255, (byte)255, (byte)0, (byte)255, (byte)255, (byte)0, (byte)255};

	private byte[] buildPOSCommand(byte[] command, byte... args) {
		byte[] posCommand = new byte[command.length + args.length];

		System.arraycopy(command, 0, posCommand, 0, command.length);
		System.arraycopy(args, 0, posCommand, command.length, args.length);

		return posCommand;
	}

	private BitSet getBits( Bitmap bitmap ) {
		int threshold = 127;
		int index = 0;
		int dimension = bitmap.getWidth() * bitmap.getHeight();
		BitSet imageBitsData = new BitSet(dimension);

		for (int y = 0; y < bitmap.getHeight(); y++)
		{
			for (int x = 0; x < bitmap.getWidth(); x++)
			{
				int color = this.bitmap.getPixel( x, y );
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

	private int calculateBytes( final Bitmap bitmap ) {
		int verticalBytes = (int)Math.ceil( bitmap.getHeight() / 8 );
		int lines = verticalBytes / 3;

		int lineBytes = SELECT_BIT_IMAGE_MODE.length + 3 + (bitmap.getWidth() * 3) + Print.PRINT_LINE_FEED.getCommand().length;

		return lineBytes * lines;
	}

	public byte[] toBytes( final Bitmap bitmap ) {
		BitSet imageBits = getBits(bitmap);

		int width = bitmap.getWidth();

		byte widthLSB = (byte)(width & 0xFF);
		byte widthMSB = (byte)((width >> 8) & 0xFF);

		// COMMANDS
		byte[] selectBitImageModeCommand = buildPOSCommand(SELECT_BIT_IMAGE_MODE, (byte) 33, widthLSB, widthMSB);

		int messageSize = calculateBytes( bitmap );
		final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

		int offset = 0;
		while (offset < bitmap.getHeight()) {
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

						// If the bitmap (or this stripe of the bitmap)
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
			buffer.put( Print.PRINT_LINE_FEED.getCommand() );
		}

		return buffer.array();
	}

    protected byte[] print( final Bitmap bitmap ) {
		// Truncate bitmap (if necessary)
		byte[] dataBytes = toBytes( bitmap );

	    // get message size
		int messageSize =
			LineSpacing.LINE_SPACING_24.getCommand().length +
			dataBytes.length +
			LineSpacing.DEFAULT.getCommand().length;

        for ( final ControlSequence controlSequence : controlSequences ) {
            messageSize += controlSequence.getCommand().length;
        }

        // create a buffer from message size
        final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

		// put the instructions
        for ( final ControlSequence controlSequence : controlSequences ) {
            buffer.put( controlSequence.getCommand() );
        }

		buffer.put( LineSpacing.LINE_SPACING_24.getCommand() );
		buffer.put( dataBytes );
		buffer.put( LineSpacing.DEFAULT.getCommand() );

        return buffer.array();
    }
}
