package de.hopf.android.common;

import android.app.Activity;
import de.hopf.android.common.drawable.Drawable;

public class StartImageDelegate extends StartDelegate {
    
    // Premium
    public StartImageDelegate(Activity activity, int hitMsgKey, int mainMsgKey, Drawable drawable) {
        super(activity, 0, hitMsgKey, mainMsgKey, drawable, 0, new DrawViewImage(activity, activity.getWindowManager(), hitMsgKey, drawable, false));
    }
    
    // Lite
    public StartImageDelegate(Activity activity, int hitMsgKey, int mainMsgKey, int linkMsgKey,
            int titleMsgKey, int startMsgKey, int fullVersionLinkKey, Drawable drawable) {
        super(activity, 0, hitMsgKey, mainMsgKey, linkMsgKey, titleMsgKey, startMsgKey, fullVersionLinkKey,
                drawable, 0, new DrawViewImage(activity, activity.getWindowManager(), hitMsgKey, drawable, true));
    } 
    
    @Override
    public void setNumber(Integer number) {
        super.setNumber(number);
        if (this.bLite) {
            if ((getCounter() > 500)) {
                setCounter(0);
                showSplashScreen();
            }
        }
    }     
}