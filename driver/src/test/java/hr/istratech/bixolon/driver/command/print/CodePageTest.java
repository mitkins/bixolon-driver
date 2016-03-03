package hr.istratech.bixolon.driver.command.print;

import hr.istratech.bixolon.driver.TestDensity;
import net.java.quickcheck.StatefulGenerator;
import net.java.quickcheck.generator.iterable.Iterables;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Arrays;

import static net.java.quickcheck.generator.CombinedGenerators.uniqueValues;
import static net.java.quickcheck.generator.PrimitiveGenerators.strings;

/**
 * @author ksaric
 */

public class CodePageTest {

    @Test
    public void testControlCodeUSA() throws Exception {
        //Before
        final byte[] cp437UsaCodepageCommand = new byte[]{(byte) 27, (byte) 116, (byte) 0};

        //When
        final byte[] command = CodePage.CP_437_USA.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( cp437UsaCodepageCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testControlCodeUSAscii() throws Exception {
        //Before
        final byte[] cp437UsaCodepageCommand = new byte[]{(byte) 27, (byte) 116, (byte) 0};

        //When
        final byte[] command = CodePage.CP_437_USA.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( cp437UsaCodepageCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testTransformToUsaBytes() throws Exception {
        //Before
        final String test = "Test";

        //When
        final Charset charset = CodePage.CP_437_USA.getCharset();
        final CodePage codePage = CodePage.CP_437_USA;

        final byte[] transformedToBytes = codePage.getBytes( test );
        final byte[] transformedBytesCharset = test.getBytes( charset );

        //Then
        Assert.assertEquals( charset, codePage.getCharset() );
        Assert.assertEquals( Integer.valueOf( 0 ), codePage.getCodePageCommand() );
        Assert.assertEquals( Arrays.toString( transformedBytesCharset ), Arrays.toString( transformedToBytes ) );
    }

    @Test
    public void testTransformInverseBytes() throws Exception {
        //Before
        final String test = "Test";

        //When
        final byte[] usaBytes = CodePage.CP_437_USA.getBytes( test );
        final String usaText = new String( usaBytes );

        final byte[] latinBytes = CodePage.CP_1252_LATIN1.getBytes( usaText );
        final String latinText = new String( latinBytes );

        final byte[] usaBytesReverse = CodePage.CP_437_USA.getBytes( latinText );
        final byte[] transformedBytesCharset = test.getBytes( CodePage.CP_437_USA.getCharset() );

        //Then
        Assert.assertEquals( Arrays.toString( transformedBytesCharset ), Arrays.toString( usaBytesReverse ) );
    }

    @Test
    public void testTransformInverseMultipleBytes() throws Exception {
        //Before
        final StatefulGenerator<String> uniqueStrings = uniqueValues( strings() );

        for ( final String testString : Iterables.toIterable( uniqueStrings, TestDensity.VERY_HIGH.getNumberOfTests() ) ) {
            //When
            final byte[] usaBytes = CodePage.CP_437_USA.getBytes( testString );
            final String usaText = new String( usaBytes );

            final byte[] latinBytes = CodePage.CP_1252_LATIN1.getBytes( usaText );
            final String latinText = new String( latinBytes );

            final byte[] usaBytesReverse = CodePage.CP_437_USA.getBytes( latinText );
            final byte[] transformedBytesCharset = testString.getBytes( CodePage.CP_437_USA.getCharset() );

            //Then
            Assert.assertEquals( Arrays.toString( transformedBytesCharset ), Arrays.toString( usaBytesReverse ) );
        }
    }

    @Test
    public void testTransformToMultipleStringsBytes() throws Exception {
        //Before
        final StatefulGenerator<String> uniqueStrings = uniqueValues( strings() );

        for ( final String testString : Iterables.toIterable( uniqueStrings, TestDensity.VERY_HIGH.getNumberOfTests() ) ) {

            //When/Then
            final byte[] transformedBytesCharset = testString.getBytes( CodePage.CP_437_USA.getCharset() );
            final byte[] transformedBytesDirectCharset = CodePage.CP_437_USA.getBytes( testString );

            Assert.assertEquals( Arrays.toString( transformedBytesCharset ), Arrays.toString( transformedBytesDirectCharset ) );
        }
    }

}