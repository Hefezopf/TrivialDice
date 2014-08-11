package de.hopf.android.common;

public enum ItemAmountType {
    ONE(new Point(0, 0)), 
    TWO(new Point(0, -13), new Point(0, 8)), 
    THREE(new Point(0, -13), new Point(-10, 8), new Point(10, 8)),
    FOUR(new Point(-10, -13), new Point(-10, 8), new Point(10, 8), new Point(10, -13));

    private Point pointOne;
    private Point pointTwo;
    private Point pointThree;
    private Point pointFour;

    private ItemAmountType(Point one) {
        this.pointOne = one;
    }

    private ItemAmountType(Point one, Point two) {
        this.pointOne = one;
        this.pointTwo = two;
    }

    private ItemAmountType(Point one, Point two, Point three) {
        this.pointOne = one;
        this.pointTwo = two;
        this.pointThree = three;
    }

    private ItemAmountType(Point one, Point two, Point three, Point four) {
        this.pointOne = one;
        this.pointTwo = two;
        this.pointThree = three;
        this.pointFour = four;
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
    
    public Point getPointThree() {
        return pointThree;
    }
    
    public Point getPointFour() {
        return pointFour;
    }
}
