package hr.istratech.bixolon.driver.command.raster;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import hr.istratech.bixolon.driver.command.print.Print;

/**
 * @author ksaric
 */

public class RasterTest {

    @Test
    public void testRasterPrintCommand() throws Exception {
        //Before
        final byte[] printLineFeedCommand = new byte[]{(byte) 10};

        //When
        final byte[] command = RasterPrint.NORMAL.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( printLineFeedCommand ), Arrays.toString( command ) );
    }

}