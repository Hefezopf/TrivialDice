package de.hopf.android.common;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;

import android.os.Bundle;
import de.hopf.android.common.drawable.Drawable;

@RunWith(RobolectricTestRunner.class)
public class StartBaseTest {

    private StartBaseTemp base;

    @Before
    public void setup(){
        base = new StartBaseTemp();
        base.initDelegate();
    }

    @Ignore
    @Test
    public void testOnCreate() {
        base.onCreate(new Bundle());
    }

    @Test
    public void testOnSaveInstanceState() {
        base.onSaveInstanceState(new Bundle());
    }

    @Test
    public void testOnPause() {
        base.onPause();
    }

    @Test
    public void testSetterGetter() {
        base.setInterrupted(true);
        Assert.assertEquals(true, base.hasInterrupted());
        base.setRolled(true);
        Assert.assertEquals(true, base.hasRolled());
        base.setNumber(1);
        Assert.assertEquals(1, base.getNumber().intValue());
        Assert.assertEquals(1, base.getCounter());
        base.setNumber2(2);
        Assert.assertEquals(2, base.getNumber2().intValue());
        base.setNumber3(3);
        Assert.assertEquals(3, base.getNumber3().intValue());
        base.setNumber4(4);
        Assert.assertEquals(4, base.getNumber4().intValue());
        base.setNumber5(5);
        Assert.assertEquals(5, base.getNumber5().intValue());
        base.setNumber6(6);
        Assert.assertEquals(6, base.getNumber6().intValue());
    }

    class StartBaseTemp extends StartBase {

        @Override
        public void initDelegate() {
            Drawable drawable = Mockito.mock(Drawable.class);
            DrawViewImage drawViewImage = Mockito.mock(DrawViewImage.class);
            this.startDelegate = new StartImageDelegate(this, 1, 2, 3, 4, 5, 6, drawable, drawViewImage);
        }
    }
 }



