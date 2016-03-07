package hr.istratech.bixolon.driver.command.qr;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrCodeErrorCorrectionLevelTest {

    @Test
    public void testErrorCorrectionLevelL() throws Exception {
        //Before
        final byte[] correctionLevelCommand = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 48};

        //When
        final byte[] command = QrCodeErrorCorrectionLevel.L.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( correctionLevelCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testErrorCorrectionLevelM() throws Exception {
        //Before
        final byte[] correctionLevelCommand = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 49};

        //When
        final byte[] command = QrCodeErrorCorrectionLevel.M.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( correctionLevelCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testErrorCorrectionLevelQ() throws Exception {
        //Before
        final byte[] correctionLevelCommand = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 50};

        //When
        final byte[] command = QrCodeErrorCorrectionLevel.Q.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( correctionLevelCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testErrorCorrectionLevelH() throws Exception {
        //Before
        final byte[] correctionLevelCommand = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 69, (byte) 51};

        //When
        final byte[] command = QrCodeErrorCorrectionLevel.H.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( correctionLevelCommand ), Arrays.toString( command ) );
    }

}