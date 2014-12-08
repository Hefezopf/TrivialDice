package de.hopf.android.common.drawable.custom;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.annotation.Config;

import de.hopf.android.common.ItemAmountType;

@Config(manifest=Config.NONE)
public class NormalDiceDrawableTest extends BaseDiceDrawableTest {

    @Before
    public void setup() {
        drawable = new NormalDiceDrawable(1, 2, 3);
        drawable.initDrawableList(ItemAmountType.ONE);
        drawable.initDrawableList(ItemAmountType.TWO);
        drawable.initDrawableList(ItemAmountType.THREE);
        drawable.initDrawableList(ItemAmountType.FOUR);
        drawable.initDrawableList(ItemAmountType.FIVE);
        drawable.initDrawableList(ItemAmountType.SIX);
    }

    @Test
    public void testSetupDice() {
        ((NormalDiceDrawable) drawable).setupDice(1, 2, 3, 4, 5, initList());
    }
}
