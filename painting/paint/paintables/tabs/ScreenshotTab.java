package scripts.tribotapi.painting.paint.paintables.tabs;

import scripts.tribotapi.color.Colors;
import scripts.tribotapi.font.Fonts;
import scripts.tribotapi.painting.paint.PaintManager;
import scripts.tribotapi.painting.paint.Paintable;

import java.awt.*;

/**
 * Created by Sphiinx on 7/24/2016.
 */
public class ScreenshotTab extends PaintManager implements Paintable {

    /**
     * The master X position for the screenshot button.
     */
    private final int X = getX() + 395;

    /**
     * The master Y position for the screenshot button.
     */
    private final int Y = getY() + 135;

    /**
     * The master width for the screenshot button.
     */
    private final int W = 112;

    /**
     * The master height for the screenshot button.
     */
    private final int H = 15;

    /**
     * Draws the paint background.
     *
     * @param g Graphics.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(X, Y, W, H);
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.fillRect(X, Y, W, H);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_TABS_FONT.getFont());
        final int Y_DATA_PADDING = 12;
        final int X_DATA_PADDING = 0;
        g.drawString("Take Screenshot", X + X_DATA_PADDING, Y + Y_DATA_PADDING);
    }

    @Override
    public boolean isInClick(Point p) {
        Rectangle rectangle = new Rectangle(X, Y, W, H);
        return rectangle.contains(p);
    }

}

