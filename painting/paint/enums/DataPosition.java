package scripts.TribotAPI.painting.paint.enums;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public enum DataPosition {

    ONE(8, 29, 15),
    TWO(8, 44, 15),
    THREE(8, 59, 15),
    FOUR(8, 74, 15),
    FIVE(8, 89, 15),
    SIX(258, 29, 15),
    SEVEN(258, 44, 15),
    EIGHT(258, 59, 15),
    NINE(258, 74, 15),
    TEN(258, 89, 15),
    SKILL(8, 113, 15);

    private final int X;
    private final int Y;
    private final int H;

    DataPosition(int x, int y, int h) {
        this.X = x;
        this.Y = y;
        this.H = h;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getHeight() {
        return H;
    }

}
