package de.hopf.android.common.drawable.custom;

import org.junit.Before;
import org.junit.Test;

import de.hopf.android.common.ItemAmountType;

public class DoublingDiceDrawableTest extends BaseDiceDrawableTest {

	@Before
	public void setup() {
		drawable = new DoublingDiceDrawable(1, 2, 3);
		drawable.initDrawableList(ItemAmountType.SIX);
	}

	@Test
	public void testSetupDice() {
		((DoublingDiceDrawable) drawable).setupDice(1, 2, 3, 4, 5, initList());
	}
}
