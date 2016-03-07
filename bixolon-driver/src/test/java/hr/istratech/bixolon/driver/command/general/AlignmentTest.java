package hr.istratech.bixolon.driver.command.general;

import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class AlignmentTest {

    @Test
    public void testAlignLeft() throws Exception {
        //Before
        final byte[] leftCommand = new byte[]{(byte) 27, (byte) 97, (byte) 0};

        //When
        final byte[] command = Alignment.LEFT.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( leftCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testAlignCenter() throws Exception {
        //Before
        final byte[] leftCommand = new byte[]{(byte) 27, (byte) 97, (byte) 1};

        //When
        final byte[] command = Alignment.CENTER.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( leftCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testAlignRight() throws Exception {
        //Before
        final byte[] leftCommand = new byte[]{(byte) 27, (byte) 97, (byte) 2};

        //When
        final byte[] command = Alignment.RIGHT.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( leftCommand ), Arrays.toString( command ) );
    }

}