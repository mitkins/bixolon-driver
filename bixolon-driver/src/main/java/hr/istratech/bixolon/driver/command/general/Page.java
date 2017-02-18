package hr.istratech.bixolon.driver.command.general;


import hr.istratech.bixolon.driver.general.GeneralControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 10</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Page implements GeneralControlSequence {

    STANDARD_MODE( new byte[]{(byte)27, (byte)83} ),
    PAGE_MODE( new byte[]{(byte)27, (byte)76} ),

    CLEAR_BUFFER( new byte[]{(byte)24} ),
    PRINT_BUFFER( new byte[]{(byte)27, (byte)12} );

    private final byte[] command;

    Page(final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }
}
