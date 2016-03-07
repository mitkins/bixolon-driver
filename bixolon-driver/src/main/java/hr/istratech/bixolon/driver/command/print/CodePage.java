package hr.istratech.bixolon.driver.command.print;

import hr.istratech.bixolon.driver.charset.*;
import hr.istratech.bixolon.driver.general.TextControlSequence;

/**
 * The <em>command instruction(s)</em> can be found on <b>page 42</b> from 'commands manual'.
 *
 * @author ksaric
 */

public enum CodePage implements TextControlSequence {

    CP_437_USA( new Cp437(), 0 ),
    CP_KATAKANA( new Katakana(), 1 ),
    CP_850_MULTILINGUAL( new Cp850(), 2 ),
    CP_860_PORTUGUESE( new Cp860(), 3 ),
    CP_863_CANADIAN_FRENCH( new Cp863(), 4 ),
    CP_865_NORDIC( new Cp865(), 5 ),
    CP_1252_LATIN1( new Windows1252(), 16 ),
    CP_866_CYRILLIC2( new Cp866(), 17 ),
    CP_852_LATIN2( new Cp852(), 18 ),
    CP_858_EURO( new Cp858(), 19 ),
    CP_862_HEBREW_DOS_CODE( new Cp862(), 21 ),
    CP_864_ARABIC( new Cp864(), 22 ),
    CP_THAI42( new Thai42(), 23 ),
    CP_1253_GREEK( new Windows1253(), 24 ),
    CP_1254_TURKISH( new Windows1254(), 25 ),
    CP_1257_BALTIC( new Windows1257(), 26 ),
    CP_FARSI( new Farsi(), 27 ),
    CP_1251_CYRILLIC( new Windows1251(), 28 ),
    CP_737_GREEK( new Cp737(), 29 ),
    CP_775_BALTIC( new Cp775(), 30 ),
    CP_THAI14( new Thai14(), 31 ),
    CP_1255_HEBREW_NEW_CODE( new Windows1255(), 33 ),
    CP_THAI11( new Thai11(), 34 ),
    CP_THAI18( new Thai18(), 35 ),
    CP_855_CYRILLIC( new Cp855(), 36 ),
    CP_857_TURKISH( new Cp857(), 37 ),
    CP_928_GREEK( new Cp928(), 38 ),
    CP_THAI16( new Thai16(), 39 ),
    CP_1256_ARABIC( new Windows1256(), 40 ),
    CP_1258_VIETNAM( new Windows1258(), 41 ),
    CP_KHMER_CAMBODIA( new KhmerCambodia(), 42 ),
    CP_1250_CZECH( new Windows1250(), 47 );

    private final ByteCharset charset;
    private final Integer codePageCommand;

    CodePage( final ByteCharset charset, final Integer codePageCommand ) {
        this.charset = charset;
        this.codePageCommand = codePageCommand;
    }

    public ByteCharset getCharset() {
        return charset;
    }

    public byte[] getBytes( final String text ) {
        return text.getBytes( charset );
    }

    @Override
    public byte[] getCommand() {
        return new byte[]{(byte) 27, (byte) 116, (byte) codePageCommand.intValue()};
    }

    public Integer getCodePageCommand() {
        return codePageCommand;
    }
}
