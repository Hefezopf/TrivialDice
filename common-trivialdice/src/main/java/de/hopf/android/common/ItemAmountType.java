package de.hopf.android.common;

public enum ItemAmountType {
    ONE(new Point(0, 0)), 
    TWO(new Point(0, -14), new Point(0, 9)), 
    THREE(new Point(0, -14), new Point(-12, 9), new Point(12, 9)),
    FOUR(new Point(-12, -14), new Point(-12, 9), new Point(12, 9), new Point(12, -14)),
    FIVE(new Point(0, -25), new Point(-12, -2), new Point(12, -2), new Point(-12, 21), new Point(12, 21)),
    SIX(new Point(-12, -25), new Point(-12, -2), new Point(-12, 21), new Point(12, -25), new Point(12, -2), new Point(12, 21));

    private Point pointOne;
    private Point pointTwo;
    private Point pointThree;
    private Point pointFour;
    private Point pointFive;
    private Point pointSix;

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

    private ItemAmountType(Point one, Point two, Point three, Point four, Point five) {
        this.pointOne = one;
        this.pointTwo = two;
        this.pointThree = three;
        this.pointFour = four;
        this.pointFive = five;
    }

    private ItemAmountType(Point one, Point two, Point three, Point four, Point five, Point six) {
        this.pointOne = one;
        this.pointTwo = two;
        this.pointThree = three;
        this.pointFour = four;
        this.pointFive = five;
        this.pointSix = six;
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
    
    public Point getPointFive() {
        return pointFive;
    }
    
    public Point getPointSix() {
        return pointSix;
    }
}
