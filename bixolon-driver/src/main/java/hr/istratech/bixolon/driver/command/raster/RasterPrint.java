package hr.istratech.bixolon.driver.command.raster;


import hr.istratech.bixolon.driver.general.RasterControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 71</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum RasterPrint implements RasterControlSequence {

    NORMAL( new byte[]{(byte) 29, (byte) 118, (byte) 48, (byte) 48} ),
    DOUBLE_WIDTH( new byte[]{(byte) 29, (byte) 118, (byte) 48, (byte) 49} ),
    DOUBLE_HEIGHT( new byte[]{(byte) 29, (byte) 118, (byte) 48, (byte) 50} ),
    QUARDRUPAL( new byte[]{(byte) 29, (byte) 118, (byte) 48, (byte) 51} );

    private final byte[] command;

    RasterPrint(final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
