package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 25</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Emphasize implements TextControlSequence {

    EMPHASIZED_ON( new byte[]{(byte) 27, (byte) 69, (byte) 1} ),
    EMPHASIZED_OFF( new byte[]{(byte) 27, (byte) 69, (byte) 0} );

    private final byte[] command;

    Emphasize( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return command;
    }

}