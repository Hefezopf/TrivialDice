package de.hopf.android.common;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public abstract class StartDiceBase extends Activity implements Data {

    protected StartDiceDelegate startDiceDelegate;
    protected int kantenLaenge;
    protected int linkerWuerfelRand;
    protected int obererWürfelRand;
    
    public abstract void initDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        kantenLaenge = metrics.widthPixels / 2;
        linkerWuerfelRand = (metrics.widthPixels - kantenLaenge) / 2;
        obererWürfelRand = (metrics.heightPixels - kantenLaenge) / 2;
        
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
    public Integer getNumber2() {
        return startDiceDelegate.getNumber2();
    }

    @Override
    public void setNumber2(Integer number2) {
        startDiceDelegate.setNumber2(number2);
    }

    @Override
    public Integer getNumber3() {
        return startDiceDelegate.getNumber3();
    }

    @Override
    public void setNumber3(Integer number3) {
        startDiceDelegate.setNumber3(number3);
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