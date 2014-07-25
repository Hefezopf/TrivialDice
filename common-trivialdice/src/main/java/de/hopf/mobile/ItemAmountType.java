package de.hopf.mobile;

public enum ItemAmountType {
    ONE(new Point(0, 0)), TWO(new Point(0, -13), new Point(0, 8));//, THREE(-13, 8);

    private Point pointOne;
    private Point pointTwo;

    private ItemAmountType(Point one) {
        this.pointOne = one;
    }

    private ItemAmountType(Point one, Point two) {
        this.pointOne = one;
        this.pointTwo = two;
    }

    public static int getFaktor() {
        return 20;
    }

    public Point getPointOne() {
        return pointOne;
    }

    public Point getPointTwo() {
        return pointTwo;
    }
}
