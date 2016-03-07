package hr.istratech.bixolon.driver.command.general;


import hr.istratech.bixolon.driver.general.GeneralControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 40</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum Alignment implements GeneralControlSequence {

    LEFT( new byte[]{(byte) 27, (byte) 97, (byte) 0} ),
    CENTER( new byte[]{(byte) 27, (byte) 97, (byte) 1} ),
    RIGHT( new byte[]{(byte) 27, (byte) 97, (byte) 2} );

    private final byte[] command;

    Alignment( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
