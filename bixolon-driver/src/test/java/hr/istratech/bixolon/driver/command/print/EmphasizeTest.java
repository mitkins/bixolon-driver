package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class EmphasizeTest {

    @Test
    public void testEmphasizedOff() throws Exception {
        //Before
        final byte[] emphasizeOffCommand = new byte[]{(byte) 27, (byte) 69, (byte) 0};

        //When
        final byte[] command = Emphasize.EMPHASIZED_OFF.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( emphasizeOffCommand ), Arrays.toString( command ) );

    }

    @Test
    public void testEmphasizedOn() throws Exception {
        //Before
        final byte[] emphasizeOnCommand = new byte[]{(byte) 27, (byte) 69, (byte) 1};

        //When
        final byte[] command = Emphasize.EMPHASIZED_ON.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( emphasizeOnCommand ), Arrays.toString( command ) );

    }
}