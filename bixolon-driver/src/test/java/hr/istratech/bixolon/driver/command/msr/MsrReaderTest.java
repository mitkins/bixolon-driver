package hr.istratech.bixolon.driver.command.msr;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class MsrReaderTest {

    @Test
    public void testMsrReaderSetting() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 73};

        //When
        final byte[] command = MsrReader.MSR_SETTING.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }


    @Test
    public void testMsrReaderCancel() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 99};

        //When
        final byte[] command = MsrReader.CANCEL_READING.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack1() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 70};

        //When
        final byte[] command = MsrReader.TRACK1.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack2() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 71};

        //When
        final byte[] command = MsrReader.TRACK2.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack3() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 68};

        //When
        final byte[] command = MsrReader.TRACK3.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack12() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 72};

        //When
        final byte[] command = MsrReader.TRACK12.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack23() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 69};

        //When
        final byte[] command = MsrReader.TRACK23.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testMsrReaderTrack123() throws Exception {
        //Before
        final byte[] msrReaderCommand = new byte[]{(byte) 27, (byte) 77, (byte) 66};

        //When
        final byte[] command = MsrReader.TRACK123.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( msrReaderCommand ), Arrays.toString( command ) );
    }

}