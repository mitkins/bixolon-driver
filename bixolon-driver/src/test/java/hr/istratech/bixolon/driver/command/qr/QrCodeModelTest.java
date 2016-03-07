package hr.istratech.bixolon.driver.command.qr;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrCodeModelTest {

    @Test
    public void testQrModel1() throws Exception {
        //Before
        final byte[] commandQrModel1 = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 4, (byte) 0, (byte) 49, (byte) 65, (byte) 49, (byte) 0};

        //When
        final byte[] command = QrCodeModel.MODEL1.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrModel1 ), Arrays.toString( command ) );
    }

    @Test
    public void testQrModel2() throws Exception {
        //Before
        final byte[] commandQrModel1 = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 4, (byte) 0, (byte) 49, (byte) 65, (byte) 50, (byte) 0};

        //When
        final byte[] command = QrCodeModel.MODEL2.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrModel1 ), Arrays.toString( command ) );
    }

}