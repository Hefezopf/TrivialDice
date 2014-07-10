package de.hopf.mobile.trivialdice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import de.hopf.mobile.Data;
import de.hopf.mobile.DrawView;

public class StartDice extends Activity implements Data
{
	private DrawView drawView;
	private Integer number;
	private int counter;
	private Boolean bInterrupted = Boolean.FALSE;
	private Boolean bRoll = Boolean.FALSE;	
	private static final String COUNT_KEY = "COUNT_KEY";
	private static final String INTERRUPTED_KEY = "INTERRUPTED_KEY";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		if(savedInstanceState == null){
			number = null;
		}
		else{
			number = (Integer) savedInstanceState.getSerializable(COUNT_KEY);
			bInterrupted = (Boolean) savedInstanceState.getSerializable(INTERRUPTED_KEY);
		}
		   
		drawView = new DrawView(this, getWindowManager());
		setContentView(drawView);
		drawView.requestFocus();
	}

	@SuppressWarnings("unused")
    private final OnClickListener mGetListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
        	setContentView(drawView);
    		drawView.requestFocus();
        }
    };	
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(COUNT_KEY, number);
        outState.putSerializable(INTERRUPTED_KEY, Boolean.TRUE);
    }	
    
    @Override
    protected void onPause() {
        super.onPause();
    }
    
    @Override
	public Integer getNumber() {
		return number;
	}

    @Override
	public void setNumber(Integer number) {
		this.number = number;
		counter++;
	}

    @Override
	public Boolean hasInterrupted() {
		return bInterrupted;
	}

    @Override
	public void setInterrupted(Boolean bInterrupted) {
		this.bInterrupted = bInterrupted;
	}

    @Override
	public Boolean hasRolled() {
		return bRoll;
	}

    @Override
	public void setRolled(Boolean bRoll) {
		this.bRoll = bRoll;
	}   
	
	public int getCounter() {
		return counter;
	}    
}