package de.hopf.mobile;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.test.ViewAsserts;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import android.widget.TextView;

public abstract class TrivialDiceBaseTest extends ActivityInstrumentationTestCase2<Activity>
{ 
    private Button startButton;
    private TextView mLink;
    private Activity startDice;
    
    private int keyStartBtn; 
    private int keyLinkTextView;
    
    // Premium
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TrivialDiceBaseTest(Class clazz) {
        super(clazz);
    }    
    
    // Lite
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TrivialDiceBaseTest(Class clazz, int keyStartBtn, int keyLinkTextView) {
        super(clazz);
        this.keyStartBtn = keyStartBtn;
        this.keyLinkTextView = keyLinkTextView;
    }    

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startDice = getActivity();
        startButton = (Button) getActivity().findViewById(keyStartBtn); 
        mLink = (TextView) getActivity().findViewById(keyLinkTextView); 
    }

    @Override
    protected void tearDown() throws Exception { 
        super.tearDown();
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
    
    @MediumTest
    public void testStartDiceViewCreated() {
      assertNotNull(startDice);
    }
    
    @MediumTest
    public void testStartButtonViewCreated() {
      assertNotNull(startButton);
      ViewAsserts.assertOnScreen(startButton.getRootView(), startButton);
    }
    
    @MediumTest
    public void testSubLaunch() { 
        if(keyStartBtn == 0){ // Premium
            assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        }
        else{ // Lite
            assertFalse(startDice.getCurrentFocus() instanceof DrawView);
            assertTrue(startDice.getCurrentFocus() instanceof Button);

            TouchUtils.tapView(this, startButton);                    
        }
    
        assertNotNull(((Data)startDice).getNumber());
        assertFalse(((Data)startDice).hasInterrupted());

        DrawView dv = (DrawView) startDice.getCurrentFocus();
        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
        
        TouchUtils.tapView(this, dv);
        
        assertNotNull(((Data)startDice).getNumber());
        
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
    public void testSplashScreen() {    
        assertFalse(startDice.getCurrentFocus() instanceof DrawView);
        assertTrue(startDice.getCurrentFocus() instanceof Button);

        TouchUtils.tapView(this, startButton);
        
        assertNotNull(((Data)startDice).getNumber());
        assertFalse(((Data)startDice).hasInterrupted());

        DrawView dv = (DrawView) startDice.getCurrentFocus();
        assertNotNull(dv);
        assertTrue(startDice.getCurrentFocus() instanceof DrawView);
    
        TouchUtils.tapView(this, dv);
        
        for (int i = 0; i < 100; i++) {
            TouchUtils.tapView(this, dv);
            assertNotNull(((Data)startDice).getNumber());
            if(((Data)startDice).getCounter() == 0){
                break;
            }
        }
        assertNotNull(((Data)startDice).getNumber());
        assertTrue(((Data)startDice).getCounter() == 0);
        startDice.finish();
    }    
}
