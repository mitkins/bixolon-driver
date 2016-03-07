package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class ReverseTest {

    @Test
    public void testReverseOff() throws Exception {
        //Before
        final byte[] reverseOffCommand = new byte[]{(byte) 29, (byte) 66, (byte) 0};

        //When
        final byte[] command = Reverse.REVERSE_OFF.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( reverseOffCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testReverseOn() throws Exception {
        //Before
        final byte[] reverseOnCommand = new byte[]{(byte) 29, (byte) 66, (byte) 1};

        //When
        final byte[] command = Reverse.REVERSE_ON.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( reverseOnCommand ), Arrays.toString( command ) );
    }

}