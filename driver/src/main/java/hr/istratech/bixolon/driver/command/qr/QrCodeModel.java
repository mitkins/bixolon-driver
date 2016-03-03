package hr.istratech.bixolon.driver.command.qr;


import hr.istratech.bixolon.driver.general.QrControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 67</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum QrCodeModel implements QrControlSequence {

    MODEL1( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 4, (byte) 0, (byte) 49, (byte) 65, (byte) 49, (byte) 0} ),
    MODEL2( new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 4, (byte) 0, (byte) 49, (byte) 65, (byte) 50, (byte) 0} );

    private final byte[] command;

    QrCodeModel( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}
