package de.hopf.mobile;

public enum DiceAmountType {
    ONE (0, 0), TWO (-100, 100);
    
    private int offsetX;
    private int offsetY;
    
    private DiceAmountType(int offsetX, int offsetY) {
        this.setOffsetX(offsetX);
        this.setOffsetY(offsetY);
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }
}
