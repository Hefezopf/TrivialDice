package de.hopf.android.common;

import android.app.Activity;
import de.hopf.android.common.drawable.Drawable;

public class StartImageDelegate extends StartDelegate {

    // Premium
    public StartImageDelegate(Activity activity, int hitMsgKey, int mainMsgKey, Drawable drawable, DrawViewImage drawViewBase) {
        super(activity, 0, hitMsgKey, mainMsgKey, drawable, drawViewBase);
    }

    // Lite
    public StartImageDelegate(Activity activity, int hitMsgKey, int mainMsgKey, int linkMsgKey,
            int titleMsgKey, int startMsgKey, int fullVersionLinkKey, Drawable drawable, DrawViewImage drawViewBase) {
        super(activity, 0, hitMsgKey, mainMsgKey, linkMsgKey, titleMsgKey, startMsgKey, fullVersionLinkKey,
                drawable, drawViewBase);
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