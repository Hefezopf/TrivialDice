package de.hopf.android.common;

public class Point
{
	private final int x, y;

	public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
	public String toString() {
		return x + ", " + y;
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
