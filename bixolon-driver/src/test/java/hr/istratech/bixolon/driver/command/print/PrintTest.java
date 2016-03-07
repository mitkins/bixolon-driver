package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class PrintTest {

    @Test
    public void testInitCommand() throws Exception {
        //Before
        final byte[] printLineFeedCommand = new byte[]{(byte) 27, (byte) 64};

        //When
        final byte[] command = Print.INITIALIZATION.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( printLineFeedCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testPrintCommand() throws Exception {
        //Before
        final byte[] printLineFeedCommand = new byte[]{(byte) 10};

        //When
        final byte[] command = Print.PRINT_LINE_FEED.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( printLineFeedCommand ), Arrays.toString( command ) );
    }

}