package test.de.hopf.mobile.tt;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import de.hopf.android.trivialdice.color.StartDice;
import de.hopf.android.trivialdice.color.lite.DrawView;

public class TrivialColorDiceTest extends ActivityInstrumentationTestCase2<StartDice>
{ 
    private Button mButton;
    private StartDice startDice;

    public TrivialColorDiceTest() {
        super(StartDice.class);
    }    

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startDice = getActivity();
        mButton = (Button) getActivity().findViewById(de.hopf.android.trivialdice.color.lite.R.id.start); 
    }

    @Override
    protected void tearDown() throws Exception { 
        super.tearDown();
    }
    
    @MediumTest
    public void testSplashScreen() {        
    	assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        assertFalse(startDice.getCurrentFocus() instanceof Button);

        DrawView dv = (DrawView) startDice.getCurrentFocus();        
        TouchUtils.tapView(this, dv);
        
        assertNotNull(startDice.getNumber());
        assertFalse(startDice.hasInterrupted());

        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
    
        TouchUtils.tapView(this, dv);
        
        int MAX_ANZAHL = 40;
        for (int i = 0; i < MAX_ANZAHL; i++) {
        	TouchUtils.tapView(this, dv);
        	assertNotNull(startDice.getNumber());
		}
        assertNotNull(startDice.getNumber());
        assertTrue("Anzahl", startDice.getCounter()>MAX_ANZAHL);
        startDice.finish();
    }
    
    @SmallTest
    public void testViewsCreated() {
      assertNotNull(startDice);
      assertNull(mButton);
    }
    
    @MediumTest
    public void testSubLaunch() {        
    	assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        assertFalse(startDice.getCurrentFocus() instanceof Button);

        DrawView dv = (DrawView) startDice.getCurrentFocus();        
        TouchUtils.tapView(this, dv);
        
        assertNotNull(startDice.getNumber());
        assertFalse(startDice.hasInterrupted());

        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        
        TouchUtils.tapView(this, dv);
        
        assertNotNull(startDice.getNumber());
        
        startDice.finish();
    }
    
    @MediumTest
    public void testLifeCycleCreate() {
        // At this point, onCreate() has been called, but nothing else
        // Complete the startup of the activity
        getInstrumentation().callActivityOnStart(startDice);
        getInstrumentation().callActivityOnResume(startDice);
        
        // At this point you could test for various configuration aspects, or you could 
        // use a Mock Context to confirm that your activity has made certain calls to the system
        // and set itself up properly.
        
        getInstrumentation().callActivityOnPause(startDice);
        
        // At this point you could confirm that the activity has paused properly, as if it is
        // no longer the topmost activity on screen.
        
        getInstrumentation().callActivityOnStop(startDice);
        
        // At this point, you could confirm that the activity has shut itself down appropriately,
        // or you could use a Mock Context to confirm that your activity has released any system
        // resources it should no longer be holding.

        // ActivityUnitTestCase.tearDown(), which is always automatically called, will take care
        // of calling onDestroy().
    }
}
