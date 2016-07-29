package scripts.TribotAPI.painting.paint.paintables;

import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;
import scripts.TribotAPI.painting.paint.PaintManager;
import scripts.TribotAPI.painting.paint.Paintable;
import scripts.TribotAPI.util.Utility;

import java.awt.*;

/**
 * Created by Sphiinx on 7/23/2016.
 */
public class InteractivePaint extends PaintManager implements Paintable {

    /**
     * The master X position for the background.
     */
    private final int X = getX();

    /**
     * The master Y position for the background.
     */
    private final int Y = getY();

    /**
     * The master width for the background.
     */
    private final int W = 503;

    /**
     * The master height for the background.
     */
    private final int H = 112;

    /**
     * The master String for the script title.
     * */
    private final String TITLE;

    /**
     * The master double for the script version.
     * */
    private final Double VERSION;

    /**
     * Constructs the title of the paint.
     *
     * @param title The title of the paint.
     * */
    public InteractivePaint(String title, Double version) {
        this.TITLE = title;
        this.VERSION = version;
    }

    /**
     * Draws the script title and version to the paint.
     *
     * @param g Graphics.
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(X, Y, W, H);
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.fillRect(X, Y, W, H);

        final int title_x_padding = X + 8;
        final int title_y_padding = Y + 22;
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_TITLE_FONT.getFont());
        g.drawString(TITLE, title_x_padding, title_y_padding);

        final int version_x_padding = 5;
        final int VERSION_X = title_x_padding + version_x_padding + Utility.getTextLength(TITLE, g);
        g.setColor(Colors.LIGHT_RED_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_VERSION_FONT.getFont());
        g.drawString("v" + VERSION, VERSION_X, title_y_padding);
    }

    @Override
    public boolean isInClick(Point p) {
        return false;
    }

}

