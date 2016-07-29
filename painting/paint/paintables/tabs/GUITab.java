package scripts.TribotAPI.painting.paint.paintables.tabs;

import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;
import scripts.TribotAPI.painting.paint.PaintManager;
import scripts.TribotAPI.painting.paint.Paintable;

import java.awt.*;

/**
 * Created by Sphiinx on 7/24/2016.
 */
public class GUITab extends PaintManager implements Paintable {

    /**
     * The master X position for the show GUI button.
     */
    private final int X = getX() + 151;

    /**
     * The master Y position for the show GUI button.
     */
    private final int Y = getY() - 22;

    /**
     * The master width for the show GUI button.
     */
    private final int W = 50;

    /**
     * The master height for the show GUI button.
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
        final int X_DATA_PADDING = 12;
        g.drawString("GUI", X + X_DATA_PADDING, Y + Y_DATA_PADDING);
    }

    @Override
    public boolean isInClick(Point p) {
        Rectangle rectangle = new Rectangle(X, Y, W, H);
        return rectangle.contains(p);
    }

}

