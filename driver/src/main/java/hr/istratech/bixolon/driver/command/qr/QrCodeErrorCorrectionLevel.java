package hr.istratech.bixolon.driver.command.qr;


import hr.istratech.bixolon.driver.general.QrControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 69</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum QrCodeErrorCorrectionLevel implements QrControlSequence {

    L( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 48} ),
    M( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 49} ),
    Q( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 50} ),
    H( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 51} );

    private final byte[] command;

    QrCodeErrorCorrectionLevel( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
