package test.de.hopf.android.trivialdice.doubling.lite;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;
import de.hopf.android.trivialdice.doubling.lite.R;
import de.hopf.android.trivialdice.doubling.lite.StartDice;
import de.hopf.mobile.DrawView;

public class TrivialDoublingDiceLiteTest extends ActivityInstrumentationTestCase2<StartDice>
{ 
    private Button mButton;
    private TextView mLink;
    private StartDice startDice;

    public TrivialDoublingDiceLiteTest() {
        super(StartDice.class);
    }    

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startDice = getActivity();
        mButton = (Button) getActivity().findViewById(R.id.start); 
        mLink = (TextView) getActivity().findViewById(R.id.link_text); 
    }

    @Override
    protected void tearDown() throws Exception { 
        super.tearDown();
    }
    
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
        
        for (int i = 0; i < 100; i++) {
        	TouchUtils.tapView(this, dv);
        	assertNotNull(startDice.getNumber());
        	if(startDice.getCounter() == 0){
        		break;
        	}
		}
        assertNotNull(startDice.getNumber());
        assertTrue(startDice.getCounter() == 0);
        startDice.finish();
    }
    
    @MediumTest
    public void testViewsCreated() {
      assertNotNull(startDice);
      assertNotNull(mButton);
      ViewAsserts.assertOnScreen(mButton.getRootView(), mButton);
    }
    
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
     
    @MediumTest
    public void testPurchaseLink() {        
        assertFalse(startDice.getCurrentFocus() instanceof DrawView);
        assertTrue(startDice.getCurrentFocus() instanceof Button);

        TouchUtils.tapView(this, mLink);
        
        assertFalse(startDice.getCurrentFocus() instanceof Button);
                
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
    }
}
