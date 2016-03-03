package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 94</b> from 'commands manual'.
 * <p> Existing alterative:
 * <p> <b>{0,1,2}</b> = {48,49,50}
 *
 * @author ksaric
 */

public enum Underline implements TextControlSequence {

    UNDERLINE_OFF( new byte[]{(byte) 27, (byte) 45, (byte) 0} ),
    UNDERLINE_1DOT_THICK( new byte[]{(byte) 27, (byte) 45, (byte) 1} ),
    UNDERLINE_2DOT_THICK( new byte[]{(byte) 27, (byte) 45, (byte) 2} );

    private final byte[] command;

    Underline( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return command;
    }

}