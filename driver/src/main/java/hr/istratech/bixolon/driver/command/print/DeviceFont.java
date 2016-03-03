package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 119</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum DeviceFont implements TextControlSequence {

    DEVICE_FONT_A( new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 65} ),
    DEVICE_FONT_B( new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 66} ),
    DEVICE_FONT_C( new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 67} );

    private final byte[] command;

    DeviceFont( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return command;
    }

}
