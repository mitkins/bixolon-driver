package hr.istratech.bixolon.driver.command.qr;


import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrCodePrintTest {

    @Test
    public void testPrint() throws Exception {
        //Before
        // new byte[]{(byte)29, (byte)40, (byte)107, (byte)3, (byte)0, (byte)49, (byte)81, (byte)48};
        final byte[] printCommand = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 81, (byte) 48};

        //When
        final byte[] command = QrCodePrint.PRINT.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( printCommand ), Arrays.toString( command ) );
    }

}