package de.hopf.android.common;

import android.app.Activity;
import de.hopf.android.common.drawable.Drawable;

public class StartDiceDelegate extends StartDelegate {

    // Premium
    public StartDiceDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, Drawable drawable, int maxNum, int fakeNumber) {
        super(activity, diceSoundKey, hitMsgKey, mainMsgKey, drawable, new DrawViewDice(activity, activity.getWindowManager(), diceSoundKey, hitMsgKey, drawable, maxNum, fakeNumber, false));
    }
    
    // Lite
    public StartDiceDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, int linkMsgKey,
            int titleMsgKey, int startMsgKey, int fullVersionLinkKey, Drawable drawable, int maxNum, int fakeNumber) {
        super(activity, diceSoundKey, hitMsgKey, mainMsgKey, linkMsgKey, titleMsgKey, startMsgKey, fullVersionLinkKey,
                drawable, new DrawViewDice(activity, activity.getWindowManager(), diceSoundKey, hitMsgKey, drawable, maxNum, fakeNumber, true));
    }
    

    @Override
    public void setNumber(Integer number) {
        super.setNumber(number);
        if (this.bLite) {
            if ((getCounter() > 10) && number != null && number.intValue() == 5) {
                setCounter(0);
                showSplashScreen();
            }                
        }
    }    
}