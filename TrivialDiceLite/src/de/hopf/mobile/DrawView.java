package de.hopf.mobile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;

public class DrawView extends View implements OnTouchListener, Serializable
{
	private static final long serialVersionUID = 1L;
	List<Point> pointsONE = new ArrayList<Point>();
	List<Point> pointsTWO = new ArrayList<Point>();
	List<Point> pointsTHREE = new ArrayList<Point>();
	List<Point> pointsFOUR = new ArrayList<Point>();
	List<Point> pointsFIVE = new ArrayList<Point>();
	List<Point> pointsSIX = new ArrayList<Point>();
	WindowManager wm;
	Paint paint;
	MediaPlayer mp;
	DisplayMetrics metrics;
	
	public DrawView(Context context, WindowManager wm) {
		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		this.setOnTouchListener(this);
			
		if(mp == null){
			mp = MediaPlayer.create(this.getContext(), R.raw.dice_sound);
		}		
//		paint = new Paint();
		if(paint == null){
			paint = new Paint();
		}		
		this.wm = wm;
//		DisplayMetrics metrics = new DisplayMetrics();
		if(metrics == null){
			metrics = new DisplayMetrics();
		}		
		this.wm.getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;	
		final int KANTEN_LAENGE = width/2;
		
		int linkerRand = (width - KANTEN_LAENGE)/2;
		int obererRand = (height - KANTEN_LAENGE)/2;
		
		paint.setColor(Color.WHITE);
		paint.setTextSize(KANTEN_LAENGE/10);
		paint.setAntiAlias(true);
		paint.setTextAlign(Align.LEFT);
		
		addPoint(linkerRand+(KANTEN_LAENGE/2), obererRand+(KANTEN_LAENGE/2), pointsONE);
		
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/4), pointsTWO);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE*3/4), pointsTWO);			

		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/4), pointsTHREE);			
		addPoint(linkerRand+(KANTEN_LAENGE/2), obererRand+(KANTEN_LAENGE/2), pointsTHREE);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE*3/4), pointsTHREE);						
		
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/4), pointsFOUR);			
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE*3/4), pointsFOUR);
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE/4), pointsFOUR);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE*3/4), pointsFOUR);			
		
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/4), pointsFIVE);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE*3/4), pointsFIVE);		
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE*3/4), pointsFIVE);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE/4), pointsFIVE);			
		addPoint(linkerRand+(KANTEN_LAENGE/2), obererRand+(KANTEN_LAENGE/2), pointsFIVE);
		
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE*3/4), pointsSIX);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE/4), pointsSIX);			
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE*3/4), pointsSIX);			
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/4), pointsSIX);			
		addPoint(linkerRand+(KANTEN_LAENGE/4), obererRand+(KANTEN_LAENGE/2), pointsSIX);			
		addPoint(linkerRand+(KANTEN_LAENGE*3/4), obererRand+(KANTEN_LAENGE/2), pointsSIX);		
	}

	private void addPoint(int x, int y, List<Point> points) {
		Point point = new Point();
		point.x = x;
		point.y = y;
		points.add(point);
	}

	@Override	
	public void onDraw(Canvas canvas)
	{		
//		DisplayMetrics metrics = new DisplayMetrics();
		if(metrics == null){
			metrics = new DisplayMetrics();
		}
		this.wm.getDefaultDisplay().getMetrics(metrics);
		int width = metrics.widthPixels;
		int height = metrics.heightPixels;	
		final int KANTEN_LAENGE = width/2;
		
		int linkerRand = (width - KANTEN_LAENGE)/2;
		int obererRand = (height - KANTEN_LAENGE)/2;
		
		paint.setTextAlign(Align.LEFT);
		canvas.drawText("\u00A9" + " hopf-it.de", 10, KANTEN_LAENGE/10, paint);
		paint.setTextAlign(Align.CENTER);
		
		String hit_text = this.getContext().getString(R.string.hit);	
		canvas.drawText(hit_text, linkerRand+(KANTEN_LAENGE/2), obererRand+KANTEN_LAENGE+(KANTEN_LAENGE/10), paint);
	
		canvas.drawLine(linkerRand, obererRand, linkerRand+KANTEN_LAENGE, obererRand, paint);
		canvas.drawLine(linkerRand, obererRand, linkerRand, obererRand+KANTEN_LAENGE, paint);
		canvas.drawLine(linkerRand+KANTEN_LAENGE, obererRand, linkerRand+KANTEN_LAENGE, obererRand+KANTEN_LAENGE, paint);
		canvas.drawLine(linkerRand, obererRand+KANTEN_LAENGE, linkerRand+KANTEN_LAENGE, obererRand+KANTEN_LAENGE, paint);

		// Play sound
		if(((Data)this.getContext()).getbRoll().equals(Boolean.TRUE))
		{			
//			MediaPlayer mp = MediaPlayer.create(this.getContext(), R.raw.dice_sound);
			if(mp == null){
				mp = MediaPlayer.create(this.getContext(), R.raw.dice_sound);
			}
			else if(mp != null){ // Reported Bug v1.5 Dec 23, 2010 2:59:25 PM
				mp.setVolume(2f, 2f);
				mp.start();
			}
		    
			((Data)this.getContext()).setbRoll(Boolean.FALSE);
			invalidate();
	
			return;
		}
		else
		{
			// Nicht beim Ersten mal!
			if(((Data)this.getContext()).getNumber() != null){
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		}
		
		// Rechnen!
		if(!((Data)this.getContext()).getbInterrupted().booleanValue()){
			((Data)this.getContext()).setNumber(new Integer((int) (Math.random()*6)));
		}
		
		List<Point> points = null;
		
		// NPE nach dem Start, manchmal beim drehen!
		if(((Data)this.getContext()).getNumber() == null){
			((Data)this.getContext()).setNumber(0);
		}
		
		switch(((Data)this.getContext()).getNumber().intValue()){
			case 0: points = pointsONE;
			break;
			case 1: points = pointsTWO;
			break;
			case 2: points = pointsTHREE;
			break;
			case 3: points = pointsFOUR;
			break;
			case 4: points = pointsFIVE;
			break;
			case 5: points = pointsSIX;
			break;
			default:
		}
		for (Point point : points) {
			canvas.drawCircle(point.x, point.y, KANTEN_LAENGE/10, paint);
		}
	}

	public boolean onTouch(View view, MotionEvent event) {
		if(event.getAction() != MotionEvent.ACTION_DOWN ){
			return false;
		}
		
		((Data)this.getContext()).setbRoll(Boolean.TRUE);
		((Data)this.getContext()).setbInterrupted(Boolean.FALSE);
		invalidate();

		return true;
	}	
}