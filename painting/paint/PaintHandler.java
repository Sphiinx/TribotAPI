package scripts.TribotAPI.painting.paint;

import org.tribot.api.General;
import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;
import scripts.TribotAPI.painting.paint.enums.Position;

import java.awt.*;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class PaintHandler {

    /**
     * The master x position for the background.
     */
    private int BACKGROUND_X = 8;

    /**
     * The master y position for the background.
     */
    private int BACKGROUND_Y = 345;

    /**
     * The master width for the background.
     */
    private final int BACKGROUND_W = 503;

    /**
     * The master height for the background.
     */
    private final int BACKGROUND_H = 112;

    /**
     * The master x position for the script title.
     */
    private int SCRIPT_TITLE_X = BACKGROUND_X + 7;

    /**
     * The master y position for the script title.
     */
    private int SCRIPT_TITLE_Y = BACKGROUND_Y + 22;

    /**
     * The master padding for the script version.
     */
    private final int SCRIPT_VERSION_X_PADDING = 5;

    /**
     * The master padding for the script version.
     */
    private final int SCRIPT_INFO_X_PADDING = 5;

    /**
     * The master padding for the script version.
     */
    private final int SCRIPT_INFO_Y_PADDING = 12;

    /**
     * The master padding for the info box.
     */
    private final int SCRIPT_INFO_BOX_PADDING = 5;

    /**
     * The master text for the script title.
     */
    private String scriptTitle = "";

    /**
     * The master text for the script version.
     */
    private String scriptVersion = "";

    public PaintHandler(String scriptTitle, String scriptVersion) {
        this.scriptTitle = scriptTitle;
        this.scriptVersion = scriptVersion;
    }

    /**
     * Draws the blank paint background.
     *
     * @param g Graphics.
     */
    public void drawPaintBackground(Graphics g) {
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.drawRect(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_W, BACKGROUND_H);
        g.fillRect(BACKGROUND_X, BACKGROUND_Y, BACKGROUND_W, BACKGROUND_H);
    }

    /**
     * Draws the paint script title.
     *
     * @param g Graphics.
     */
    public void drawScriptTitle(Graphics g) {
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_TITLE_FONT.getFont());
        g.drawString(scriptTitle, SCRIPT_TITLE_X, SCRIPT_TITLE_Y);
    }

    /**
     * Draws the paint script version.
     *
     * @param g Graphics.
     */
    public void drawScriptVersion(Graphics g) {
        final int VERSION_X = SCRIPT_TITLE_X + getTextLength(scriptTitle, g) + SCRIPT_VERSION_X_PADDING;
        g.setColor(Colors.LIGHT_RED_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_VERSION_FONT.getFont());
        g.drawString(scriptVersion, VERSION_X, SCRIPT_TITLE_Y);
    }

    /**
     * Draws the paint info boxes
     *
     * @param infoTitle The info title for the box.
     * @param calculation The calculation for the box.
     * @param pos       The position for the info box.
     * @param g         Graphics.
     */
    public void drawInfo(String infoTitle, String calculation, Position pos, Graphics g) {
        g.setFont(Fonts.PAINT_INFO_FONT.getFont());
        final int BOX_W = SCRIPT_INFO_X_PADDING + SCRIPT_INFO_BOX_PADDING + getTextLength(infoTitle + calculation, g);
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.drawRect(pos.getX() + BACKGROUND_X, pos.getY() + BACKGROUND_Y, BOX_W, pos.getHeight());
        g.setColor(Colors.LIGHT_GRAY_COLOR.getCOLOR());
        g.fillRect(pos.getX() + BACKGROUND_X, pos.getY() + BACKGROUND_Y, BOX_W, pos.getHeight());

        final int INFO_X = pos.getX() + BACKGROUND_X + SCRIPT_INFO_X_PADDING;
        final int INFO_Y = pos.getY() + BACKGROUND_Y + SCRIPT_INFO_Y_PADDING;
        g.setColor(Colors.LIGHT_RED_COLOR.getCOLOR());
        g.drawString(infoTitle, INFO_X, INFO_Y);
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(calculation, INFO_X + getTextLength(infoTitle, g), INFO_Y);
    }

    /**
     * Sets the paint master x position and y position.
     *
     * @param x The x position.
     * @param y The y position.
     * */
    public void setPaintPosition(int x, int y) {
        BACKGROUND_X = x;
        BACKGROUND_Y = y;
        SCRIPT_TITLE_X = BACKGROUND_X + 7;
        SCRIPT_TITLE_Y = BACKGROUND_Y + 22;
    }

    /**
     * Gets the pixel width of the specified text.
     *
     * @param text The text to get the width of.
     * @param g    Graphics.
     */
    private int getTextLength(String text, Graphics g) {
        return g.getFontMetrics().stringWidth(text);
    }

}
