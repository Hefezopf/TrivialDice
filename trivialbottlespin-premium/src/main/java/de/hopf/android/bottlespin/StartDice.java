package de.hopf.android.bottlespin;

import de.hopf.android.common.StartBase;
import de.hopf.android.common.StartDelegate;
import de.hopf.android.common.drawable.Drawable;
import de.hopf.android.common.drawable.custom.BottleSpinDrawable;

public class StartDice extends StartBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new BottleSpinDrawable(this.getResources(), R.raw.bottle, kantenLaenge, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDelegate(this, 0, R.string.hit, R.layout.main, drawable, 0, true);
    }
}