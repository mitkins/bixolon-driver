package hr.istratech.bixolon.driver.command.qr;

import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrCodeStoreTest {

    @Test
    public void testGetCommand() throws Exception {
        //Before
        final String testData = "test";
        final byte[] data = testData.getBytes();

        int pL = (byte) ( ( data.length + 3 ) % 256 );
        int pH = (byte) ( ( data.length + 3 ) / 256 );

        final byte[] command = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) pL, (byte) pH, (byte) 49, (byte) 80, (byte) 48};

        final ByteBuffer byteBuffer = ByteBuffer.allocate(
                QrCodeModel.MODEL1.getCommand().length
                        + QrCodeSize.SIZE7.getCommand().length
                        + QrCodeErrorCorrectionLevel.L.getCommand().length
                        + command.length
                        + data.length
                        + QrCodePrint.PRINT.getCommand().length
        );
        byteBuffer.put( QrCodeModel.MODEL1.getCommand() );
        byteBuffer.put( QrCodeSize.SIZE7.getCommand() );
        byteBuffer.put( QrCodeErrorCorrectionLevel.L.getCommand() );
        byteBuffer.put( command );
        byteBuffer.put( data );
        byteBuffer.put( QrCodePrint.PRINT.getCommand() );

        //When
        final byte[] array = byteBuffer.array();

        //Then
        Assert.assertEquals( "[29, 40, 107, 4, 0, 49, 65, 49, 0, 29, 40, 107, 3, 0, 49, 67, 6, 29, 40, 107, 3, 0, 49, 69, 48, 29, 40, 107, 7, 0, 49, 80, 48, 116, 101, 115, 116, 29, 40, 107, 3, 0, 49, 81, 48]", Arrays.toString( array ) );

    }

    @Test
    public void testLocal() throws Exception {
        //Before
        final String testData = "test";
        final byte[] data = testData.getBytes();

        final QrCodeStore qrCodeStore = QrCodeStore.createQrCodeStore( data );

        final ByteBuffer byteBuffer = ByteBuffer.allocate(
                QrCodeModel.MODEL1.getCommand().length
                        + QrCodeSize.SIZE7.getCommand().length
                        + QrCodeErrorCorrectionLevel.L.getCommand().length
                        + qrCodeStore.getCommand().length
                        + data.length
                        + QrCodePrint.PRINT.getCommand().length
        );
        byteBuffer.put( QrCodeModel.MODEL1.getCommand() );
        byteBuffer.put( QrCodeSize.SIZE7.getCommand() );
        byteBuffer.put( QrCodeErrorCorrectionLevel.L.getCommand() );
        byteBuffer.put( qrCodeStore.getCommand() );
        byteBuffer.put( data );
        byteBuffer.put( QrCodePrint.PRINT.getCommand() );

        //When
        final byte[] array = byteBuffer.array();

        //Then
        Assert.assertEquals( "[29, 40, 107, 4, 0, 49, 65, 49, 0, 29, 40, 107, 3, 0, 49, 67, 6, 29, 40, 107, 3, 0, 49, 69, 48, 29, 40, 107, 7, 0, 49, 80, 48, 116, 101, 115, 116, 29, 40, 107, 3, 0, 49, 81, 48]", Arrays.toString( array ) );

    }
}