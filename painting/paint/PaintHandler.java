package TribotAPI.painting.paint;

import TribotAPI.color.Colors;

import java.awt.*;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class PaintHandler {

    private int BACKGROUND_X = 15;

    private int BACKGROUND_Y = 300;

    private int BACKGROUND_W = 500;

    private int BACKGROUND_H = 300;

    public PaintHandler() {
    }

    public void drawPaintBackground(Graphics g) {
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillRect(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_W, BACKGROUND_H);
    }

}
