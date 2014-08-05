package de.hopf.mobile;

import android.test.suitebuilder.annotation.MediumTest;

public abstract class TrivialDicePremiumBaseTest extends TrivialDiceBaseTest
{ 
    @SuppressWarnings("rawtypes")
    public TrivialDicePremiumBaseTest(Class clazz) {
        super(clazz);
    }

    @Override
    @MediumTest
    public void testPurchaseLink() {        
    }    
    
    @Override
    @MediumTest
    public void testSplashScreen() {
    }
    
    @Override
    @MediumTest
    public void testStartButtonViewCreated() {
    }
}
