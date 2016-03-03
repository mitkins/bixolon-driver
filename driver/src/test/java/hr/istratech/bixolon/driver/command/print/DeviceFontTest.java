package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class DeviceFontTest {

    @Test
    public void testDeviceFontA() throws Exception {
        //Before
        final byte[] deviceFontACommand = new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 65};

        //When
        final byte[] command = DeviceFont.DEVICE_FONT_A.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( deviceFontACommand ), Arrays.toString( command ) );
    }

    @Test
    public void testDeviceFontB() throws Exception {
        //Before
        final byte[] deviceFontACommand = new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 66};

        //When
        final byte[] command = DeviceFont.DEVICE_FONT_B.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( deviceFontACommand ), Arrays.toString( command ) );
    }

    @Test
    public void testDeviceFontC() throws Exception {
        //Before
        final byte[] deviceFontACommand = new byte[]{(byte) 8, (byte) 77, (byte) 0, (byte) 67};

        //When
        final byte[] command = DeviceFont.DEVICE_FONT_C.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( deviceFontACommand ), Arrays.toString( command ) );
    }


}