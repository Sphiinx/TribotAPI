package scripts.TribotAPI.painting.paint.enums;

import scripts.TribotAPI.painting.paint.PaintHandler;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public enum Position {

    ONE(7, 29, 15),
    TWO(7, 44, 15),
    THREE(7, 59, 15),
    FOUR(7, 74, 15),
    FIVE(7, 89, 15);

    private final int X;
    private final int Y;
    private final int H;

    Position(int x, int y, int h) {
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
