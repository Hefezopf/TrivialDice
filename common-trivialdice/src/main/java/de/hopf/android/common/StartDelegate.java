package de.hopf.android.common;

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
import de.hopf.android.common.drawable.Drawable;

public class StartDelegate implements Data {
    protected final Activity activity;
    protected final int diceSoundKey;
    protected final int hitMsgKey;
    protected final int mainMsgKey;
    protected final int linkMsgKey;
    protected final int startMsgKey;
    protected final boolean bLite;
    private int fullVersionLinkKey;
    protected DrawViewBase drawView;
    protected Integer number;
    private Integer number2;
    private Integer number3;
    private Integer number4;
    private Integer number5;
    private Integer number6;
    private int counter;
    protected Boolean bInterrupted = Boolean.FALSE;
    private Boolean bRoll = Boolean.FALSE;
    protected static final String COUNT_KEY = "COUNT_KEY";
    protected static final String INTERRUPTED_KEY = "INTERRUPTED_KEY";
    
    protected final Drawable drawable;
    
    // Premium
    protected StartDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, Drawable drawable, DrawViewBase drawView) {
        this.activity = activity;
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;
        this.mainMsgKey = mainMsgKey;      
        this.linkMsgKey = 0;
        this.startMsgKey = 0;
        this.bLite = false;
        this.drawable = drawable;
        this.drawView = drawView;
    }

    // Lite
    protected StartDelegate(Activity activity, int diceSoundKey, int hitMsgKey, int mainMsgKey, int linkMsgKey,
            int titleMsgKey, int startMsgKey, int fullVersionLinkKey, Drawable drawable, DrawViewBase drawView) {
        this.activity = activity;
        this.diceSoundKey = diceSoundKey;
        this.hitMsgKey = hitMsgKey;
        this.mainMsgKey = mainMsgKey;
        this.linkMsgKey = linkMsgKey;
        this.startMsgKey = startMsgKey;
        this.fullVersionLinkKey = fullVersionLinkKey;        
        this.bLite = true;
        this.drawable = drawable;
        this.drawView = drawView;
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

        activity.setContentView(drawView);
        drawView.requestFocus();

        // StartInfo nur beim ersten mal
        if (bLite && number == null) {
            showSplashScreen();
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
    }

    protected void showSplashScreen() {
        activity.setContentView(this.mainMsgKey);
        
        Button link_text = (Button) activity.findViewById(this.linkMsgKey);
        link_text.setOnClickListener(fullVersionListener);
        
        Button startButton = (Button) activity.findViewById(this.startMsgKey);
        startButton.setOnClickListener(continueListener);
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
    public Integer getNumber3() {
        return number3;
    }

    @Override
    public void setNumber3(Integer number3) {
        this.number3 = number3;
    }
    
    @Override
    public Integer getNumber4() {
        return number4;
    }

    @Override
    public void setNumber4(Integer number4) {
        this.number4 = number4;
    }
    
    @Override
    public Integer getNumber5() {
        return number5;
    }

    @Override
    public void setNumber5(Integer number5) {
        this.number5 = number5;
    }
    
    @Override
    public Integer getNumber6() {
        return number6;
    }

    @Override
    public void setNumber6(Integer number6) {
        this.number6 = number6;
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
    
//    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }
}