package hr.istratech.bixolon.example;

import android.app.Activity;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.test.ActivityInstrumentationTestCase2;

/**
 * @author ksaric
 */

public class BluetoothBixolonActivityTest extends ActivityInstrumentationTestCase2<BluetoothBixolonActivity> {

    public BluetoothBixolonActivityTest() {
        super( BluetoothBixolonActivity.class );
    }

    private Activity mainActivity;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mainActivity = getActivity();
//        IdlingPolicies.setIdlingResourceTimeout( 10, TimeUnit.SECONDS );


    }

    public void testPrintDriver() throws Exception {
        Espresso.onView( ViewMatchers.withId( R.id.connect_button ) ).perform( ViewActions.click() );

        Thread.sleep( 2000 ); // just wait for a while...

        Espresso.onView( ViewMatchers.withId( R.id.text_print_button ) ).perform( ViewActions.click() );
    }

    public void testPrintSimpleQrCode() throws Exception {
        Espresso.onView( ViewMatchers.withId( R.id.connect_button ) ).perform( ViewActions.click() );

        Thread.sleep( 2000 ); // just wait for a while...

        Espresso.onView( ViewMatchers.withId( R.id.qr_print_button ) ).perform( ViewActions.click() );
    }

}