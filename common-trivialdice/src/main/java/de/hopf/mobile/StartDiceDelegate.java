package de.hopf.mobile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class StartDiceDelegate implements Data {
    private final Activity activity;
    private final int diceSoundKey;
    private final int hitMsgKey;
    private final int mainMsgKey;
    private final int linkMsgKey;
    private final int startMsgKey;
    private final boolean bLite;
    private final DiceType diceType;
    private int fullVersionLinkKey;

    private DrawView drawView;
    private Integer number;
    private Integer number2;
    private int counter;
    private Boolean bInterrupted = Boolean.FALSE;
    private Boolean bRoll = Boolean.FALSE;
    private static final String COUNT_KEY = "COUNT_KEY";
    private static final String INTERRUPTED_KEY = "INTERRUPTED_KEY";

    // Premium
    public StartDiceDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, DiceType diceType) {
        this.activity = activity;
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;
        this.mainMsgKey = mainMsgKey;
        this.diceType = diceType;
        
        this.linkMsgKey = 0;
        this.startMsgKey = 0;
        this.bLite = false;
    }

    // Lite
    public StartDiceDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, int linkMsgKey,
            int titleMsgKey, int startMsgKey, DiceType diceType, int fullVersionLinkKey) {
        this.activity = activity;
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;
        this.mainMsgKey = mainMsgKey;
        this.linkMsgKey = linkMsgKey;
        this.startMsgKey = startMsgKey;
        this.diceType = diceType;
        this.fullVersionLinkKey = fullVersionLinkKey;        
        
        this.bLite = true;
    }

    public void onCreate(Bundle savedInstanceState) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);

        if (savedInstanceState == null) {
            number = null;
        } else {
            number = (Integer) savedInstanceState.getSerializable(COUNT_KEY);
            bInterrupted = (Boolean) savedInstanceState.getSerializable(INTERRUPTED_KEY);
        }

        drawView = new DrawView(this.activity, activity.getWindowManager(), this.diceSoundKey, this.hitMsgKey, this.diceType);
        activity.setContentView(drawView);
        drawView.requestFocus();

        // StartInfo nur beim ersten mal
        if (bLite && number == null) {
            activity.setContentView(this.mainMsgKey);

            Button link_text = (Button) activity.findViewById(this.linkMsgKey);
            link_text.setOnClickListener(fullVersionListener);

            Button startButton = (Button) activity.findViewById(this.startMsgKey);
            startButton.setOnClickListener(continueListener);
        }
    }

    @SuppressWarnings("unused")
    private void ping() {
        try {
            // System.out.println("ping");
            // Linkverkuerzer auf hopf-it.de
            // siehe: http://goo.gl/
            // http://hopf-it.de/download/TrivialDiceLite.apk
            // URL url = new URL("http://goo.gl/maV2c");
            URL url = new URL("hopf-it.de/download/TrivialDiceLite.apk");

            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "Android Application:Trivial Dice Lite");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
            urlc.connect();
            urlc.getContent();
            if (urlc.getResponseCode() == 200) {
                // System.out.println("getResponseCode");
                // Main.Log("getResponseCode == 200");
                // return new Boolean(true);
            }
        } catch (MalformedURLException e1) {
            // System.out.println("MalformedURLException");
            // Main.Log("getResponseCode =
            // e1.printStackTrace();
        } catch (IOException e) {
            // System.out.println("IOException");
            // e.printStackTrace();
        }
    }

    private final OnClickListener continueListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            activity.setContentView(drawView);
            drawView.requestFocus();
        }
    };

    private final OnClickListener fullVersionListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent getWebPage = new Intent(Intent.ACTION_VIEW, Uri.parse(activity.getString(fullVersionLinkKey)));
            activity.startActivity(getWebPage);
        }
    };

    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(COUNT_KEY, number);
        outState.putSerializable(INTERRUPTED_KEY, Boolean.TRUE);
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    @Override
    public void setNumber(Integer number) {
        this.number = number;
        counter++;
        if (this.bLite) {
            if ((counter > 10) && number != null && number.intValue() == 5) {
                counter = 0;
                activity.setContentView(this.mainMsgKey);
                
                Button link_text = (Button) activity.findViewById(this.linkMsgKey);
                link_text.setOnClickListener(fullVersionListener);
                
                Button startButton = (Button) activity.findViewById(this.startMsgKey);
                startButton.setOnClickListener(continueListener);
            }
        }
    }

    @Override
    public Integer getNumber2() {
        return number2;
    }

    @Override
    public void setNumber2(Integer number2) {
        this.number2 = number2;
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

    @Override
    public int getCounter() {
        return counter;
    }
}