package de.hopf.android.common.drawable.custom;

import org.junit.Before;
import org.junit.Test;
import org.robolectric.annotation.Config;

import de.hopf.android.common.ItemAmountType;

@Config(manifest=Config.NONE)
public class LetterDiceDrawableTest extends BaseDiceDrawableTest {

    @Before
    public void setup() {
        drawable = new LetterDiceDrawable(1, 2, 3);
        drawable.initDrawableList(ItemAmountType.SIX);
    }

    @Test
    public void testSetupDice() {
        ((LetterDiceDrawable) drawable).setupDice(1, 2, 3, 4, 5, initList());
    }
}
