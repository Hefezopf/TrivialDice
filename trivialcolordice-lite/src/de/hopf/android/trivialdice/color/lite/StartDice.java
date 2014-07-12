package de.hopf.android.trivialdice.color.lite;

import android.app.Activity;
import android.os.Bundle;
import de.hopf.mobile.Data;
import de.hopf.mobile.DiceType;
import de.hopf.mobile.StartDiceDelegate;

public class StartDice extends Activity implements Data
{
    private StartDiceDelegate startDiceDelegate;
   
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.startDiceDelegate = new StartDiceDelegate(this, R.raw.dice_sound, R.string.hit, R.layout.main, R.id.link_text, R.id.title, R.id.start, true, DiceType.DICE_COLOR);
		startDiceDelegate.onCreate(savedInstanceState);
	}
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);       
        startDiceDelegate.onSaveInstanceState(outState);
    }	
    
    @Override
    protected void onPause() {
        super.onPause();
    }

	public Integer getNumber() {
		return startDiceDelegate.getNumber();
	}

	public void setNumber(Integer number) {
	    startDiceDelegate.setNumber(number);	
	}

	public Boolean hasInterrupted() {
		return startDiceDelegate.hasInterrupted();
	}

	public void setInterrupted(Boolean bInterrupted) {
	    startDiceDelegate.setInterrupted(bInterrupted);
	}

	public Boolean hasRolled() {
		return startDiceDelegate.hasRolled();
	}

	public void setRolled(Boolean bRoll) {
	    startDiceDelegate.setRolled(bRoll);
	} 
	
	public int getCounter() {
		return startDiceDelegate.getCounter();
	}
}