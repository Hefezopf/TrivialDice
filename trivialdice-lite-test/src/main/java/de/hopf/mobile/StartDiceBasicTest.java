package de.hopf.mobile;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import de.hopf.mobile.R;
import de.hopf.mobile.StartDice;

/**
 * This demonstrates completely isolated "unit test" of an Activity class.
 *
 * <p>This model for testing creates the entire Activity (like {@link Focus2ActivityTest}) but does
 * not attach it to the system (for example, it cannot launch another Activity).  It allows you to
 * inject additional behaviors via the 
 * {@link android.test.ActivityUnitTestCase#setActivityContext(Context)} and 
 * {@link android.test.ActivityUnitTestCase#setApplication(android.app.Application)} methods.  
 * It also allows you to more carefully test your Activity's performance 
 * Writing unit tests in this manner requires more care and attention, but allows you to test
 * very specific behaviors, and can also be an easier way to test error conditions.
 * 
 * <p>Because ActivityUnitTestCase creates the Activity under test completely outside of
 * the usual system, tests of layout and point-click UI interaction are much less useful
 * in this configuration.  It's more useful here to concentrate on tests that involve the 
 * underlying data model, internal business logic, or exercising your Activity's life cycle.
 *
 * <p>See {@link com.example.android.apis.AllTests} for documentation on running
 * all tests and individual tests in this application.
 */
public class StartDiceBasicTest extends ActivityInstrumentationTestCase2<StartDice> {

    @SuppressWarnings("unused")
    private Intent mStartIntent;
    
    private Button mButton;

    public StartDiceBasicTest() {
        super("de.hopf.mobile", StartDice.class);
      }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // In setUp, you can create any shared test data, or set up mock components to inject
        // into your Activity.  But do not call startActivity() until the actual test methods.
        mStartIntent = new Intent(Intent.ACTION_MAIN);
    }

    /**
     * The name 'test preconditions' is a convention to signal that if this
     * test doesn't pass, the test case was not set up properly and it might
     * explain any and all failures in other tests.  This is not guaranteed
     * to run before other tests, as junit uses reflection to find the tests.
     */
    @MediumTest
    public void testPreconditions() {
        mButton = (Button) getActivity().findViewById(R.id.start);
        
        assertNotNull(getActivity());
        assertNotNull(mButton);
    }
    
    /**
     * This test demonstrates examining the way that activity calls startActivity() to launch 
     * other activities.
     */
    @MediumTest
    public void testSubLaunch() {
        mButton = (Button) getActivity().findViewById(R.id.start);
    }    
}
