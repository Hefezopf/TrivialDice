package de.hopf.mobile;

import de.hopf.android.trivialdice.color.lite.R;
import de.hopf.android.trivialdice.color.lite.StartDice;

public class TrivialColorDiceLiteTest extends TrivialDiceBaseTest
{ 
    public TrivialColorDiceLiteTest() {
        super(StartDice.class, R.id.start , R.id.link_text, "http://market.android.com/details?id=de.hopf.android.trivialdice.color");
    }   
}
