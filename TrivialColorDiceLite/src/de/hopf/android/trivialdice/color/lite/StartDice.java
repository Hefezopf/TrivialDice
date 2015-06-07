package de.hopf.android.trivialdice.color.lite;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class StartDice extends Activity implements Data
{
	private DrawView drawView;
	private Integer number;
	private int counter;
	private Boolean bInterrupted = Boolean.FALSE;
	private Boolean bRoll = Boolean.FALSE;
	private static final String COUNT_KEY = "COUNT_KEY";
	private static final String INTERRUPTED_KEY = "INTERRUPTED_KEY";
	private int KANTEN_LAENGE;
	DisplayMetrics metrics;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
//		ping();
		
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
				
		// StartInfo nur beim ersten mal
		if(number == null){ 
			setContentView(R.layout.main);
			
//			DisplayMetrics metrics = new DisplayMetrics();
			if(metrics == null){
				metrics = new DisplayMetrics();
			}
			getWindowManager().getDefaultDisplay().getMetrics(metrics);
			KANTEN_LAENGE = metrics.widthPixels/2;	
			
			TextView title = (TextView)findViewById(R.id.title); 
			title.setTextSize(KANTEN_LAENGE/5);
			
			TextView link_text = (TextView)findViewById(R.id.link_text); 
			link_text.setTextSize(KANTEN_LAENGE/9);
			link_text.setMovementMethod(LinkMovementMethod.getInstance());        	
			
			Button startButton = (Button)findViewById(R.id.start); 
			startButton.setOnClickListener(mGetListener);
			startButton.setTextSize(KANTEN_LAENGE/7);
		}
	}

	@SuppressWarnings("unused")
    private void ping(){
		try {
//System.out.println("ping");
			// Linkverkürzer auf hopf-it.de
			// siehe: http://goo.gl/
			// http://hopf-it.de/download/TrivialDiceLite.apk
//			URL url = new URL("http://goo.gl/maV2c"); 	
			URL url = new URL("hopf-it.de/download/TrivialDiceLite.apk"); 	

			HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
			urlc.setRequestProperty("User-Agent", "Android Application:Trivial Dice Lite");
			urlc.setRequestProperty("Connection", "close");
			urlc.setConnectTimeout(1000 * 30); // mTimeout is in seconds
			urlc.connect();
			urlc.getContent();
			if (urlc.getResponseCode() == 200) {
//System.out.println("getResponseCode");				
				//Main.Log("getResponseCode == 200");
				//return new Boolean(true);
			}
		} catch (MalformedURLException e1) {
//System.out.println("MalformedURLException");				
			//Main.Log("getResponseCode =			
			//e1.printStackTrace();
		} catch (IOException e) {
//System.out.println("IOException");				
//e.printStackTrace();
		}
	}

	private final OnClickListener mGetListener = new OnClickListener() {
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

    public Integer getNumber() {
		return number;
	}

    public void setNumber(Integer number) {
		this.number = number;
		counter++;
		if((counter > 10) && number != null && number.intValue() == 5)
		{
			counter = 0;		
			setContentView(R.layout.main);
			Button startButton = (Button)findViewById(R.id.start); 
			startButton.setOnClickListener(mGetListener);
			startButton.setTextSize(KANTEN_LAENGE/7);
		}
	}

    public Boolean hasInterrupted() {
		return bInterrupted;
	}

    public void setInterrupted(Boolean bInterrupted) {
		this.bInterrupted = bInterrupted;
	}

    public Boolean hasRolled() {
		return bRoll;
	}

    public void setRolled(Boolean bRoll) {
		this.bRoll = bRoll;
	} 
	
	public int getCounter() {
		return counter;
	} 	
}