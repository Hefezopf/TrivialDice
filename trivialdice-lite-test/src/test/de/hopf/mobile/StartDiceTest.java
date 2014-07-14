package test.de.hopf.mobile;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import de.hopf.mobile.DrawView;
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
public class StartDiceTest extends ActivityInstrumentationTestCase2<StartDice> { //ActivityUnitTestCase<StartDice> {

    private Button mButton;
    private StartDice startDice;

    public StartDiceTest(/*String name*/) {
        super("de.hopf.mobile", StartDice.class);
        setName("name");
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startDice = getActivity();
        mButton = (Button) getActivity().findViewById(de.hopf.mobile.R.id.start); 
    }

    @Override
    protected void tearDown() throws Exception { 
        super.tearDown();
    }
    
    
    /**
     * This test demonstrates examining the way that activity calls startActivity() to launch 
     * other activities.
     */
    @MediumTest
    public void testSplashScreen() {        
        assertFalse(startDice.getCurrentFocus() instanceof DrawView);
        assertTrue(startDice.getCurrentFocus() instanceof Button);

        TouchUtils.tapView(this, mButton);
        
        assertNotNull(startDice.getNumber());
        assertFalse(startDice.hasInterrupted());

        DrawView dv = (DrawView) startDice.getCurrentFocus();
        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
    
        TouchUtils.tapView(this, dv);
        
        for (int i = 0; i < 20; i++) {
        	TouchUtils.tapView(this, dv);
        	 assertNotNull(startDice.getNumber());
//        	 if(startDice.showSplashScreen(startDice.getNumber())){
//        		 assertTrue(startDice.getCurrentFocus() instanceof Button);
//        		 break;
//        	 }
//        	 else
//        	 {
//        		 assertTrue(startDice.getCurrentFocus() instanceof DrawView);
//        	 }
		}
        assertNotNull(startDice.getNumber());
        startDice.finish();
    }
    
    @SmallTest
    public void testViewsCreated() {
      assertNotNull(startDice);
      assertNotNull(mButton);
      ViewAsserts.assertOnScreen(mButton.getRootView(), mButton);
    }
    
    /**
     * This test demonstrates examining the way that activity calls startActivity() to launch 
     * other activities.
     */
    @MediumTest
    public void testSubLaunch() {        
        assertFalse(startDice.getCurrentFocus() instanceof DrawView);
        assertTrue(startDice.getCurrentFocus() instanceof Button);

        TouchUtils.tapView(this, mButton);
        
        assertNotNull(startDice.getNumber());
        assertFalse(startDice.hasInterrupted());

        DrawView dv = (DrawView) startDice.getCurrentFocus();
        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        
        TouchUtils.tapView(this, dv);
        
        assertNotNull(startDice.getNumber());
        
        startDice.finish();
    }    
}
