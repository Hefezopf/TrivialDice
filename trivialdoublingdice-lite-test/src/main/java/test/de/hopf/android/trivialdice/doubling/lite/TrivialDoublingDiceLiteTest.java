package test.de.hopf.android.trivialdice.doubling.lite;

import de.hopf.android.trivialdice.doubling.lite.R;
import de.hopf.android.trivialdice.doubling.lite.StartDice;
import de.hopf.mobile.TrivialDiceBaseTest;

public class TrivialDoublingDiceLiteTest extends TrivialDiceBaseTest
{ 
    public TrivialDoublingDiceLiteTest() {
        super(StartDice.class, R.id.start , R.id.link_text,"http://market.android.com/details?id=de.hopf.android.trivialdice.doubling");
    }   
}
