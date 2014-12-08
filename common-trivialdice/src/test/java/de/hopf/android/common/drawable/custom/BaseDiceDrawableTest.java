package de.hopf.android.common.drawable.custom;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import android.graphics.Canvas;
import android.graphics.Paint;
import de.hopf.android.common.Point;
import de.hopf.android.common.drawable.base.BaseDrawable;

public abstract class BaseDiceDrawableTest {

	protected BaseDrawable drawable;

	protected List<List<Point>> initList() {
		List<List<Point>> list = new ArrayList<List<Point>>();
		List<Point> listInner = new ArrayList<Point>();
		listInner.add(new Point(2, 2));
		listInner.add(new Point(2, 2));
		listInner.add(new Point(2, 2));
		listInner.add(new Point(2, 2));
		listInner.add(new Point(2, 2));
		listInner.add(new Point(2, 2));
		list.add(listInner);
		list.add(listInner);
		list.add(listInner);
		list.add(listInner);
		list.add(listInner);
		list.add(listInner);

		return list;
	}

	@Test
	public void testDrawContent() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		Paint paint = Mockito.mock(Paint.class);
		Canvas canvas = Mockito.mock(Canvas.class);
		drawable.drawContent(list, paint, canvas, 20);
	}
}
