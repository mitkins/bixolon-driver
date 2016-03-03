package hr.istratech.bixolon.driver.command.msr;


import hr.istratech.bixolon.driver.general.MsrControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 29</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum MsrReader implements MsrControlSequence {

    TRACK1( new byte[]{(byte) 27, (byte) 77, (byte) 70} ),
    TRACK2( new byte[]{(byte) 27, (byte) 77, (byte) 71} ),
    TRACK3( new byte[]{(byte) 27, (byte) 77, (byte) 68} ),
    TRACK12( new byte[]{(byte) 27, (byte) 77, (byte) 72} ),
    TRACK23( new byte[]{(byte) 27, (byte) 77, (byte) 69} ),
    TRACK123( new byte[]{(byte) 27, (byte) 77, (byte) 66} ),
    MSR_SETTING( new byte[]{(byte) 27, (byte) 77, (byte) 73} ),
    CANCEL_READING( new byte[]{(byte) 27, (byte) 77, (byte) 99} );

    private final byte[] command;

    MsrReader( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
