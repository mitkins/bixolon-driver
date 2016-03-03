package hr.istratech.bixolon.driver.general;

import hr.istratech.bixolon.driver.command.general.Alignment;
import hr.istratech.bixolon.driver.command.print.CodePage;
import hr.istratech.bixolon.driver.command.print.DeviceFont;
import hr.istratech.bixolon.driver.command.qr.QrCodeErrorCorrectionLevel;
import hr.istratech.bixolon.driver.command.qr.QrCodeModel;
import hr.istratech.bixolon.driver.command.qr.QrCodeSize;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class TextPrinterTest {

    @Test
    public void testCodePage() throws Exception {
        //Before
        final CodePage codePage = CodePage.CP_437_USA;

        //When
        final String test = "test";
        final byte[] codePageBytes = codePage.getBytes( test );

        //Then
        Assert.assertEquals( "[116, 101, 115, 116]", Arrays.toString( codePageBytes ) );
    }


    @Test
    public void testPrintPlain() throws Exception {
        //Before
        final Printer printer = TextPrinterBuilder
                .aPrinterBuilder()
                .buildPrinter( "test" );

        //When
        final byte[] command = printer.getCommand();

        //Then
        Assert.assertEquals( "[116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }

    @Test
    public void testPrintCodePage() throws Exception {
        //Before
        final Printer printer = TextPrinterBuilder
                .aPrinterBuilder()
                .withCodePage( CodePage.CP_852_LATIN2 )
                .buildPrinter( "test" );

        //When
        final byte[] command = printer.getCommand();

        //Then
        Assert.assertEquals( "[27, 116, 18, 116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }

    @Test
    public void testPrintAlignment() throws Exception {
        //Before
        final Printer printer = TextPrinterBuilder
                .aPrinterBuilder()
                .withGeneralControlSequence( Alignment.LEFT )
                .buildPrinter( "test" );

        //When
        final byte[] command = printer.getCommand();

        //Then
        Assert.assertEquals( "[27, 97, 0, 116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }

    @Test
    public void testPrintFontB() throws Exception {
        //Before
        final Printer printer = TextPrinterBuilder
                .aPrinterBuilder()
                .withTextControlSequence( DeviceFont.DEVICE_FONT_B )
                .buildPrinter( "test" );

        //When
        final byte[] command = printer.getCommand();

        //Then
        Assert.assertEquals( "[8, 77, 0, 66, 116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }


    @Test
    public void testPrintCombine() throws Exception {
        //Before
        final Printer leftPrinter = TextPrinterBuilder
                .aPrinterBuilder()
                .withGeneralControlSequence( Alignment.LEFT )
                .buildPrinter( "test" );

        // it composes
        final Printer combinedPrinter = TextPrinterBuilder
                .aPrinterBuilder()
                .withControlSequences( leftPrinter )
                .withControlSequences(
                        TextPrinterBuilder
                                .aPrinterBuilder()
                                .withGeneralControlSequence( Alignment.RIGHT )
                                .buildPrinter( "test" )
                )
                .buildPrinter( "test" );

        //When
        final byte[] command = combinedPrinter.getCommand();

        //Then
        Assert.assertEquals( "[27, 97, 0, 116, 101, 115, 116, 10, 0, 27, 97, 2, 116, 101, 115, 116, 10, 0, 116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }

    @Test
    public void testPrintCombineMultiplePrinters() throws Exception {

        //Before
        final Printer textLeftPrinter = TextPrinterBuilder
                .aPrinterBuilder()
                .withGeneralControlSequence( Alignment.LEFT )
                .buildPrinter( "test" );

        final Printer qrPrinter = QrPrinterBuilder
                .aPrinterBuilder()
                .withQrControlSequence( QrCodeModel.MODEL2 )
                .withQrControlSequence( QrCodeSize.SIZE7 )
                .withQrControlSequence( QrCodeErrorCorrectionLevel.L )
                .buildPrinter( "223175087923687075112234402528973166755123456781508151013321" );

        // it composes
        final Printer combinedPrinter = TextPrinterBuilder
                .aPrinterBuilder()
                .withControlSequences( textLeftPrinter )
                .withControlSequences( qrPrinter )
                .buildPrinter( "test" );

        //When
        final byte[] command = combinedPrinter.getCommand();

        //Then
        Assert.assertEquals( "[27, 97, 0, 116, 101, 115, 116, 10, 0, 29, 40, 107, 4, 0, 49, 65, 50, 0, 29, 40, 107, 3, 0, 49, 67, 6, 29, 40, 107, 3, 0, 49, 69, 48, 29, 40, 107, 63, 0, 49, 80, 48, 50, 50, 51, 49, 55, 53, 48, 56, 55, 57, 50, 51, 54, 56, 55, 48, 55, 53, 49, 49, 50, 50, 51, 52, 52, 48, 50, 53, 50, 56, 57, 55, 51, 49, 54, 54, 55, 53, 53, 49, 50, 51, 52, 53, 54, 55, 56, 49, 53, 48, 56, 49, 53, 49, 48, 49, 51, 51, 50, 49, 29, 40, 107, 3, 0, 49, 81, 48, 116, 101, 115, 116, 10, 0]", Arrays.toString( command ) );

    }

}