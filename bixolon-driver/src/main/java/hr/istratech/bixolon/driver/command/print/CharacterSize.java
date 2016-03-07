package hr.istratech.bixolon.driver.command.print;


import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 47</b> from 'commands manual'.
 * Use <b><em>CharacterSize.CURRIED_CHARACTER_SIZE</em></b> if you want to select a <b>custom size</b> yourself.
 *
 * @author ksaric
 */

public enum CharacterSize implements TextControlSequence {

    // Look at page 48
    CURRIED_CHARACTER_SIZE( new byte[]{(byte) 29, (byte) 33} ),
    NORMAL( new byte[]{(byte) 29, (byte) 33, (byte) ( 0 + 0 )} ),
    LARGE( new byte[]{(byte) 29, (byte) 33, (byte) ( 16 + 1 )} ),
    X_LARGE( new byte[]{(byte) 29, (byte) 33, (byte) ( 32 + 2 )} ),
    XX_LARGE( new byte[]{(byte) 29, (byte) 33, (byte) ( 48 + 3 )} ),
    XXX_LARGE( new byte[]{(byte) 29, (byte) 33, (byte) ( 64 + 4 )} );

    private final byte[] command;

    CharacterSize( final byte[] command ) {
        this.command = command;
    }

    @Override
    public byte[] getCommand() {
        return this.command;
    }

}

