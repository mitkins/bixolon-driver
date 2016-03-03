package hr.istratech.bixolon.driver.general;

import hr.istratech.bixolon.driver.command.print.CodePage;
import hr.istratech.bixolon.driver.command.print.Print;
import junit.framework.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ksaric
 */

public class ControlSequencesTest {

    @Test
    public void testCreateControlSequence() throws Exception {
        //Before
        final String testData = "test";

        final Printer printer = TextPrinterBuilder
                .aPrinterBuilder()
                .buildPrinter( testData );

        final ControlSequences controlSequences = new ControlSequences(); // for the sake of code coverage

        //When
        final byte[] codePageCommand = CodePage.CP_437_USA.getBytes( testData );
        final byte[] printCommand = Print.PRINT_LINE_FEED.getCommand();

        final ByteBuffer byteBuffer = ByteBuffer.allocate( codePageCommand.length + printCommand.length + 1 );
        byteBuffer.put( codePageCommand );
        byteBuffer.put( printCommand );

        final ControlSequence controlSequence =
                ControlSequences.createControlSequence( byteBuffer.array() );

        //Then
        Assert.assertEquals( Arrays.toString( printer.getCommand() ), Arrays.toString( controlSequence.getCommand() ) );
    }

}