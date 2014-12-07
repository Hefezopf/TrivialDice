package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import android.app.Activity;
import de.hopf.android.common.drawable.Drawable;

@Ignore
public class StartImageDelegateTest {

    @Test
    public void testPremium() {
        Activity activity = Mockito.mock(Activity.class);
        Drawable drawable = Mockito.mock(Drawable.class);
        DrawViewImage drawViewImage = new DrawViewImage(activity, activity.getWindowManager(), 1, drawable, false);
        StartImageDelegate d = new StartImageDelegate(activity, 1, 2, drawable, drawViewImage);
        Assert.assertNotNull(d);
        d.setNumber(1);
    }
 }
