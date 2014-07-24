package de.hopf.android.trivialdice.doubling.lite;

import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceBase;
import de.hopf.mobile.StartDiceDelegate;
import de.hopf.mobile.drawable.DoublingDiceDrawable;
import de.hopf.mobile.drawable.Drawable;

public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new DoublingDiceDrawable(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, DiceType.DICE_DOUBLING, R.string.link, drawable);
    }
}