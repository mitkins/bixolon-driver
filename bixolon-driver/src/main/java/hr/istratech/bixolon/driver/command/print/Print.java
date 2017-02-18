package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 10</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Print implements TextControlSequence {

    INITIALIZATION( new byte[]{(byte) 27, (byte) 64} ),

    LABEL_MODE( new byte[]{(byte)8, (byte)76, (byte)76} ),
    RECEIPT_MODE( new byte[]{(byte)8, (byte)76, (byte)82} ),

    PRINT_LINE_FEED( new byte[]{(byte) 10} ),
    FORM_FEED( new byte[]{(byte)12} ),

    // For when you want to conditionally send a print command
    EMPTY( new byte[]{} );

    private final byte[] command;

    Print( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }
}
