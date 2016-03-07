package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class UnderlineTest {

    @Test
    public void testUnderlineOff() throws Exception {
        //Before
        final byte[] commandUnderlineOff = new byte[]{(byte) 27, (byte) 45, (byte) 0};

        //When
        final byte[] command = Underline.UNDERLINE_OFF.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandUnderlineOff ), Arrays.toString( command ) );
    }

    @Test
    public void testUnderline1DotThick() throws Exception {
        //Before
        final byte[] commandUnderline1DotThick = new byte[]{(byte) 27, (byte) 45, (byte) 1};

        //When
        final byte[] command = Underline.UNDERLINE_1DOT_THICK.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandUnderline1DotThick ), Arrays.toString( command ) );
    }

    @Test
    public void testUnderline2DotThick() throws Exception {
        //Before
        final byte[] commandUnderline2DotThick = new byte[]{(byte) 27, (byte) 45, (byte) 2};

        //When
        final byte[] command = Underline.UNDERLINE_2DOT_THICK.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( commandUnderline2DotThick ), Arrays.toString( command ) );
    }

}