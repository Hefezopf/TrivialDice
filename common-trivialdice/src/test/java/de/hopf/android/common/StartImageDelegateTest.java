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
        StartImageDelegate d = new StartImageDelegate(activity, 1, 2, drawable);
        Assert.assertNotNull(d);
        d.setNumber(1);
    }
 }
