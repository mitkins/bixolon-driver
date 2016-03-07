package hr.istratech.bixolon.driver.command.qr;


import hr.istratech.bixolon.driver.general.QrControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 71</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum QrCodePrint implements QrControlSequence {

    PRINT( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 81, (byte) 48} );

    private final byte[] command;

    QrCodePrint( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
