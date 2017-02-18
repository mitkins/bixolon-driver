package hr.istratech.bixolon.driver.command.raster;


import hr.istratech.bixolon.driver.general.RasterControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 71</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum RasterPrint implements RasterControlSequence {

    BIT_IMAGE_MODE( new byte[]{(byte)27, (byte)42, (byte)33} ),
    LINE_FEED_24( new byte[]{(byte)27, (byte)74, (byte)24} );

    private final byte[] command;

    RasterPrint(final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
