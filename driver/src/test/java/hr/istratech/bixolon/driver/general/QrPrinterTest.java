package hr.istratech.bixolon.driver.general;

import hr.istratech.bixolon.driver.command.general.Alignment;
import hr.istratech.bixolon.driver.command.qr.QrCodeErrorCorrectionLevel;
import hr.istratech.bixolon.driver.command.qr.QrCodeModel;
import hr.istratech.bixolon.driver.command.qr.QrCodeSize;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrPrinterTest {

    @Test
    public void testPrint() throws Exception {
        //Before
        final Printer qrPrinter = QrPrinterBuilder.aPrinterBuilder()
                .withQrControlSequence( QrCodeModel.MODEL2 )
                .withQrControlSequence( QrCodeSize.SIZE7 )
                .withQrControlSequence( QrCodeErrorCorrectionLevel.L )
                .buildPrinter( "223175087923687075112234402528973166755123456781508151013321" );

        //When
        final byte[] command = qrPrinter.getCommand();

        //Then
        Assert.assertEquals( "[29, 40, 107, 4, 0, 49, 65, 50, 0, 29, 40, 107, 3, 0, 49, 67, 6, 29, 40, 107, 3, 0, 49, 69, 48, 29, 40, 107, 63, 0, 49, 80, 48, 50, 50, 51, 49, 55, 53, 48, 56, 55, 57, 50, 51, 54, 56, 55, 48, 55, 53, 49, 49, 50, 50, 51, 52, 52, 48, 50, 53, 50, 56, 57, 55, 51, 49, 54, 54, 55, 53, 53, 49, 50, 51, 52, 53, 54, 55, 56, 49, 53, 48, 56, 49, 53, 49, 48, 49, 51, 51, 50, 49, 29, 40, 107, 3, 0, 49, 81, 48]", Arrays.toString( command ) );

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
                .withGeneralControlSequence( Alignment.LEFT )
                .withQrControlSequence( QrCodeModel.MODEL2 )
                .withQrControlSequence( QrCodeSize.SIZE7 )
                .withQrControlSequence( QrCodeErrorCorrectionLevel.L )
                .buildPrinter( "223175087923687075112234402528973166755123456781508151013321" );

        // it composes
        final Printer combinedPrinter = QrPrinterBuilder
                .aPrinterBuilder()
                .withControlSequences( textLeftPrinter )
                .withControlSequences( qrPrinter )
                .buildPrinter( "http://www.istratech.hr/" );

        //When
        final byte[] command = combinedPrinter.getCommand();

        //Then
        Assert.assertEquals( "[27, 97, 0, 116, 101, 115, 116, 10, 0, 27, 97, 0, 29, 40, 107, 4, 0, 49, 65, 50, 0, 29, 40, 107, 3, 0, 49, 67, 6, 29, 40, 107, 3, 0, 49, 69, 48, 29, 40, 107, 63, 0, 49, 80, 48, 50, 50, 51, 49, 55, 53, 48, 56, 55, 57, 50, 51, 54, 56, 55, 48, 55, 53, 49, 49, 50, 50, 51, 52, 52, 48, 50, 53, 50, 56, 57, 55, 51, 49, 54, 54, 55, 53, 53, 49, 50, 51, 52, 53, 54, 55, 56, 49, 53, 48, 56, 49, 53, 49, 48, 49, 51, 51, 50, 49, 29, 40, 107, 3, 0, 49, 81, 48, 29, 40, 107, 27, 0, 49, 80, 48, 104, 116, 116, 112, 58, 47, 47, 119, 119, 119, 46, 105, 115, 116, 114, 97, 116, 101, 99, 104, 46, 104, 114, 47, 29, 40, 107, 3, 0, 49, 81, 48]", Arrays.toString( command ) );

    }

}