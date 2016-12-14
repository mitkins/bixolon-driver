package hr.istratech.bixolon.driver.command.raster;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 94</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum LineSpacing implements TextControlSequence {

    DEFAULT( new byte[]{ (byte)27, (byte)50} ),
    LINE_SPACING_24( new byte[]{ (byte)27, (byte)51, (byte)24} );

    private final byte[] command;

    LineSpacing(final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return command;
    }

}