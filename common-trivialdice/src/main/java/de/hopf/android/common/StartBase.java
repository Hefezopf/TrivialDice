package de.hopf.android.common;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public abstract class StartBase extends Activity implements Data {

    protected StartDelegate startDelegate;
    protected int kantenLaenge;
    protected int linkerWuerfelRand;
    protected int obererWürfelRand;
    
    public abstract void initDelegate();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        kantenLaenge = metrics.widthPixels / 3;
        linkerWuerfelRand = (metrics.widthPixels - kantenLaenge) / 2;
        obererWürfelRand = (metrics.heightPixels - kantenLaenge) / 2;
        
        initDelegate();
        startDelegate.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        startDelegate.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public Integer getNumber() {
        return startDelegate.getNumber();
    }

    @Override
    public void setNumber(Integer number) {
        startDelegate.setNumber(number);
    }

    @Override
    public Integer getNumber2() {
        return startDelegate.getNumber2();
    }

    @Override
    public void setNumber2(Integer number2) {
        startDelegate.setNumber2(number2);
    }

    @Override
    public Integer getNumber3() {
        return startDelegate.getNumber3();
    }

    @Override
    public void setNumber3(Integer number3) {
        startDelegate.setNumber3(number3);
    }

    @Override
    public Integer getNumber4() {
        return startDelegate.getNumber4();
    }

    @Override
    public void setNumber4(Integer number4) {
        startDelegate.setNumber4(number4);
    }

    @Override
    public Integer getNumber5() {
        return startDelegate.getNumber5();
    }

    @Override
    public void setNumber5(Integer number5) {
        startDelegate.setNumber5(number5);
    }

    @Override
    public Integer getNumber6() {
        return startDelegate.getNumber6();
    }

    @Override
    public void setNumber6(Integer number6) {
        startDelegate.setNumber6(number6);
    }

    @Override
    public Boolean hasInterrupted() {
        return startDelegate.hasInterrupted();
    }

    @Override
    public void setInterrupted(Boolean bInterrupted) {
        startDelegate.setInterrupted(bInterrupted);
    }

    @Override
    public Boolean hasRolled() {
        return startDelegate.hasRolled();
    }

    @Override
    public void setRolled(Boolean bRoll) {
        startDelegate.setRolled(bRoll);
    }

    @Override
    public int getCounter() {
        return startDelegate.getCounter();
    }
}