package scripts.tribotapi.painting.paint.paintables;

import scripts.tribotapi.color.Colors;
import scripts.tribotapi.painting.paint.PaintManager;
import scripts.tribotapi.painting.paint.Paintable;

import java.awt.*;

/**
 * Created by Sphiinx on 7/23/2016.
 */
public class SkillBackground extends PaintManager implements Paintable {

    /**
     * The master x position for the background.
     */
    private final int X = getX();

    /**
     * The master y position for the background.
     */
    private final int Y = getY() + 113;

    /**
     * The master width for the skill background.
     */
    private final int W = 503;

    /**
     * The master height for the skill background.
     */
    private final int H = 15;

    /**
     * Draws the skill background.
     *
     * @param g Graphics.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(X, Y, W, H);
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.fillRect(X, Y, W, H);
    }

    @Override
    public boolean isInClick(Point p) {
        return false;
    }

}

