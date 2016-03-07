package hr.istratech.bixolon.driver.command.print;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author ksaric
 */

public class CharacterSizeTest {

    @Test
    public void testCurriedCharacterSize() throws Exception {
        //Before
        final byte[] curriedCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33};

        //When
        final byte[] command = CharacterSize.CURRIED_CHARACTER_SIZE.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( curriedCharacterSizeCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testNormalCharacterSize() throws Exception {
        //Before
        final byte[] normalCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33, (byte) ( 0 + 0 )};

        //When
        final byte[] command = CharacterSize.NORMAL.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( normalCharacterSizeCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testLargeCharacterSize() throws Exception {
        //Before
        final byte[] largeCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33, (byte) ( 16 + 1 )};

        //When
        final byte[] command = CharacterSize.LARGE.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( largeCharacterSizeCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testXLargeCharacterSize() throws Exception {
        //Before
        final byte[] XLargeCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33, (byte) ( 32 + 2 )};

        //When
        final byte[] command = CharacterSize.X_LARGE.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( XLargeCharacterSizeCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testXXLargeCharacterSize() throws Exception {
        //Before
        final byte[] XXLargeCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33, (byte) ( 48 + 3 )};

        //When
        final byte[] command = CharacterSize.XX_LARGE.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( XXLargeCharacterSizeCommand ), Arrays.toString( command ) );
    }

    @Test
    public void testXXXLargeCharacterSize() throws Exception {
        //Before
        final byte[] XXXLargeCharacterSizeCommand = new byte[]{(byte) 29, (byte) 33, (byte) ( 64 + 4 )};

        //When
        final byte[] command = CharacterSize.XXX_LARGE.getCommand();

        //Then
        Assert.assertEquals( Arrays.toString( XXXLargeCharacterSizeCommand ), Arrays.toString( command ) );
    }


}