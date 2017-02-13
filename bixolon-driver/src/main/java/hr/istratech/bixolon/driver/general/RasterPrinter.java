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
import hr.istratech.bixolon.driver.command.raster.RasterPrint;

/**
 * @author mitkins
 *
 * Based on article that explains the process:
 * http://new-grumpy-mentat.blogspot.com.au/2014/06/java-escpos-image-printing.html
 *
 * And sample code from:
 * http://stackoverflow.com/questions/26269019/print-bitmap-full-page-width-in-thermal-dot-printer-using-esc-pos-in-java
 */

class RasterPrinter implements Printer {
	public static final int DEFAULT_THRESHOLD = 200;

    private final Collection<ControlSequence> controlSequences;
    private final Bitmap bitmap;
	private final boolean pageMode;
	private final int printerWidth;
	private final int luminanceThreshold;

	private RasterPrinter(final List<ControlSequence> controlSequences, final Bitmap bitmap, boolean pageMode, final int printerWidth, final int luminanceThreshold) {
        this.controlSequences = Collections.unmodifiableCollection( controlSequences );
        this.bitmap = bitmap;
		this.pageMode = pageMode;
		this.printerWidth = printerWidth;
		this.luminanceThreshold = luminanceThreshold;
    }

    public static Printer create(final List<ControlSequence> controlSequences, final Bitmap bitmap, boolean pageMode, int printerWidth) {
        return create( controlSequences, bitmap, pageMode, printerWidth, DEFAULT_THRESHOLD );
    }

	public static Printer create(final List<ControlSequence> controlSequences, final Bitmap bitmap, boolean pageMode, final int printerWidth, final int luminanceThreshold) {
		return new RasterPrinter( controlSequences, bitmap, pageMode, printerWidth, luminanceThreshold );
	}

	@Override
    public byte[] getCommand() {
        return print( bitmap );
    }

	private BitSet getBits( Bitmap bitmap ) {
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
				imageBitsData.set(index, (luminance < luminanceThreshold));
				index++;
			}
		}

		return imageBitsData;
	}

	private int calculateBytes( final Bitmap bitmap ) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();

		int verticalBytes = (int)Math.ceil( height / 8 );
		int lines = verticalBytes / 3;

		int lineBytes;
		if ( pageMode ) {
			lineBytes = RasterPrint.BIT_IMAGE_MODE.getCommand().length + 2 + (width * 3) + Print.PRINT_LINE_FEED_24.getCommand().length + Print.PRINT_LINE_FEED_24.getCommand().length;

		} else {
			lineBytes = RasterPrint.BIT_IMAGE_MODE.getCommand().length + 2 + (width * 3) + Print.PRINT_LINE_FEED_24.getCommand().length;
		}

		return lineBytes * ( lines + 1 );
	}

	public byte[] toBytes( final Bitmap bitmap ) {
		BitSet imageBits = getBits(bitmap);

		int width = bitmap.getWidth();

		// Bitmaps wider than the printer width are ignored (instead of sending bad data to the device)
		if ( width > printerWidth) return new byte[0];

		byte widthLSB = (byte)(width & 0xFF);
		byte widthMSB = (byte)((width >> 8) & 0xFF);

		int messageSize = calculateBytes( bitmap );
		final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

		int offset = 0;
		while (offset < bitmap.getHeight()) {
			buffer.put( RasterPrint.BIT_IMAGE_MODE.getCommand() );
			buffer.put( widthLSB );
			buffer.put( widthMSB );

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
			buffer.put( Print.PRINT_LINE_FEED_24.getCommand() );
			if ( pageMode ) buffer.put( Print.PRINT_LINE_FEED_24.getCommand() );
		}

		return buffer.array();
	}

    protected byte[] print( final Bitmap bitmap ) {
		// Truncate bitmap (if necessary)
		byte[] dataBytes = toBytes( bitmap );

	    // get message size
		int messageSize = dataBytes.length;

		if ( pageMode ) {
			messageSize = messageSize +
				Print.PAGE_MODE.getCommand().length +
				Print.FORM_FEED.getCommand().length;

		} else {
			messageSize = messageSize + Print.STANDARD_MODE.getCommand().length;
		}

        for ( final ControlSequence controlSequence : controlSequences ) {
            messageSize += controlSequence.getCommand().length;
        }

        // create a buffer from message size
        final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

		// put the instructions
        for ( final ControlSequence controlSequence : controlSequences ) {
            buffer.put( controlSequence.getCommand() );
        }

		if ( pageMode ) {
			buffer.put( Print.PAGE_MODE.getCommand() );
		} else {
			buffer.put( Print.STANDARD_MODE.getCommand() );
		}

		buffer.put( dataBytes );

		if ( pageMode ) {
			buffer.put( Print.FORM_FEED.getCommand() );
		}

        return buffer.array();
    }
}
