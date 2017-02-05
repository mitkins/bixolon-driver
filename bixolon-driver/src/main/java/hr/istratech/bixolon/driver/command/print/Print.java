package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 10</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Print implements TextControlSequence {

    INITIALIZATION( new byte[]{(byte) 27, (byte) 64} ),
    PRINT_LINE_FEED( new byte[]{(byte) 10} ),
    PRINT_LINE_FEED_24( new byte[]{(byte)27, (byte)74, (byte)24} ),

    PAGE_MODE( new byte[]{(byte)27, (byte)76} ),
    STANDARD_MODE( new byte[]{(byte)27, (byte)83} ),
    FORM_FEED( new byte[]{(byte)27, (byte)12} ),
    CLEAR_BUFFER( new byte[]{(byte)24} );

    private final byte[] command;

    Print( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
