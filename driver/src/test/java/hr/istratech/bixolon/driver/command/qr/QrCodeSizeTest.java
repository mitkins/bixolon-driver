package hr.istratech.bixolon.driver.command.qr;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class QrCodeSizeTest {

    @Test
    public void testQrCodeSize1() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 0};

        //When
        final byte[] command = QrCodeSize.SIZE1.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize2() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 1};

        //When
        final byte[] command = QrCodeSize.SIZE2.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize3() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 2};

        //When
        final byte[] command = QrCodeSize.SIZE3.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize4() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 3};

        //When
        final byte[] command = QrCodeSize.SIZE4.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize5() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 4};

        //When
        final byte[] command = QrCodeSize.SIZE5.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize6() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 5};

        //When
        final byte[] command = QrCodeSize.SIZE6.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize7() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 6};

        //When
        final byte[] command = QrCodeSize.SIZE7.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize8() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 7};

        //When
        final byte[] command = QrCodeSize.SIZE8.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize9() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 8};

        //When
        final byte[] command = QrCodeSize.SIZE9.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

    @Test
    public void testQrCodeSize10() throws Exception {
        //Before
        final byte[] commandQrCodeSize = new byte[]{(byte) 29, (byte) 40, (byte) 107, (byte) 3, (byte) 0, (byte) 49, (byte) 67, (byte) 9};

        //When
        final byte[] command = QrCodeSize.SIZE10.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandQrCodeSize ), Arrays.toString( command ) );
    }

}