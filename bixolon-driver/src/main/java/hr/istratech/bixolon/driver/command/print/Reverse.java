package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 94</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Reverse implements TextControlSequence {

    REVERSE_OFF( new byte[]{(byte) 29, (byte) 66, (byte) 0} ),
    REVERSE_ON( new byte[]{(byte) 29, (byte) 66, (byte) 1} );

    private final byte[] command;

    Reverse( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return command;
    }

}