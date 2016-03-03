package hr.istratech.bixolon.driver.general;


import hr.istratech.bixolon.driver.command.print.CodePage;
import hr.istratech.bixolon.driver.command.print.Print;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author ksaric
 */

class TextPrinter implements Printer {

    private final Collection<ControlSequence> controlSequences;
    private final CodePage codePage;
    private final String data;

    private TextPrinter( final List<ControlSequence> controlSequences, final CodePage codePage, final String data ) {
        this.data = data;
        this.controlSequences = Collections.unmodifiableCollection( controlSequences );
        this.codePage = codePage;
    }

    public static Printer create( final List<ControlSequence> controlSequences, final CodePage codePage, final String data ) {
        return new TextPrinter( controlSequences, codePage, data );
    }

    @Override
    public byte[] getCommand() {
        return print( data );
    }

    protected byte[] print( final String data ) {
        final byte[] bytes = codePage.getBytes( data );

        // get message size
        Integer messageSize = 1 + bytes.length + 1;

        for ( final ControlSequence controlSequence : controlSequences ) {
            messageSize += controlSequence.getCommand().length;
        }

        // create a buffer from message size
        final ByteBuffer buffer = ByteBuffer.allocate( messageSize );

        // put the instructions
        for ( final ControlSequence controlSequence : controlSequences ) {
            buffer.put( controlSequence.getCommand() );
        }

        // put the text
        buffer.put( bytes );

        // print the buffer out
        buffer.put( Print.PRINT_LINE_FEED.getCommand() );

        return buffer.array();
    }

}
