package de.hopf.mobile;

public enum ItemAmountType {
    ONE(0, 0), TWO(-13, 8);

    private int offset1;
    private int offset2;

    private ItemAmountType(int offset1, int offset2) {
        this.offset1 = offset1;
        this.offset2 = offset2;
    }

    public int getOffset1() {
        return offset1;
    }

    public int getOffset2() {
        return offset2;
    }
    
    public static int getFaktor() {
        return 20;
    }
    
}
