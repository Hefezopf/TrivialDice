package de.hopf.mobile;

import de.hopf.mobile.drawable.Drawable;
import de.hopf.mobile.drawable.NormalDiceDrawable;


public class StartDice extends StartDiceBase {

    @Override
    public void initDelegate() {
        Drawable drawable = new NormalDiceDrawable(kantenLaengeWuerfel, linkerWuerfelRand, obererWürfelRand);
        this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main,
                R.id.link_text, R.id.title, R.id.start, DiceType.DICE_NORMAL, R.string.link, drawable);
    }
}