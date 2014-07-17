package de.hopf.mobile;

import android.app.Activity;
import android.os.Bundle;

public abstract class StartDiceBase extends Activity implements Data{
    
    protected StartDiceDelegate startDiceDelegate;

    public abstract void initDelegate();
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDelegate();
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

    @Override
    public Integer getNumber() {
        return startDiceDelegate.getNumber();
    }

    @Override
    public void setNumber(Integer number) {
        startDiceDelegate.setNumber(number);
    }

    @Override
    public Boolean hasInterrupted() {
        return startDiceDelegate.hasInterrupted();
    }

    @Override
    public void setInterrupted(Boolean bInterrupted) {
        startDiceDelegate.setInterrupted(bInterrupted);
    }

    @Override
    public Boolean hasRolled() {
        return startDiceDelegate.hasRolled();
    }

    @Override
    public void setRolled(Boolean bRoll) {
        startDiceDelegate.setRolled(bRoll);
    }

    @Override
    public int getCounter() {
        return startDiceDelegate.getCounter();
    }
}