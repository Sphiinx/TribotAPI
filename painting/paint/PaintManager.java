package scripts.TribotAPI.painting.paint;

import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;
import scripts.TribotAPI.painting.paint.enums.Position;

import java.awt.*;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class PaintManager {

    /**
     * The rendering hints for antialiasing.
     */
    private final RenderingHints ANTIALIASING = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    /**
     * The master x position for the paint background.
     */
    private int paint_background_x = 8;

    /**
     * The master y position for the paint background.
     */
    private int paint_background_y = 345;

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
    private int script_title_x = paint_background_x + 7;

    /**
     * The master y position for the script title.
     */
    private int script_title_y = paint_background_y + 22;

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

    public PaintManager(String scriptTitle, String scriptVersion) {
        this.scriptTitle = scriptTitle;
        this.scriptVersion = scriptVersion;
    }

    /**
     * Draws the paint background, title, and version and sets the paint to use anti aliasing.
     *
     * @param g Graphics.
     */
    public void drawGeneralData(Graphics g) {
        useAntialiasing(g);
        drawPaintBackground(g);
        drawScriptTitle(g);
        drawScriptVersion(g);
    }

    /**
     * Sets the rendering hints to use antialiasing.
     *
     * @param g Graphics.
     */
    public void useAntialiasing(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHints(ANTIALIASING);
    }

    /**
     * Draws the blank paint background.
     *
     * @param g Graphics.
     */
    public void drawPaintBackground(Graphics g) {
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.drawRect(paint_background_x, paint_background_y, BACKGROUND_W, BACKGROUND_H);
        g.fillRect(paint_background_x, paint_background_y, BACKGROUND_W, BACKGROUND_H);
    }

    /**
     * Draws the paint script title.
     *
     * @param g Graphics.
     */
    public void drawScriptTitle(Graphics g) {
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_TITLE_FONT.getFont());
        g.drawString(scriptTitle, script_title_x, script_title_y);
    }

    /**
     * Draws the paint script version.
     *
     * @param g Graphics.
     */
    public void drawScriptVersion(Graphics g) {
        final int VERSION_X = script_title_x + getTextLength(scriptTitle, g) + SCRIPT_VERSION_X_PADDING;
        g.setColor(Colors.LIGHT_RED_COLOR.getCOLOR());
        g.setFont(Fonts.PAINT_VERSION_FONT.getFont());
        g.drawString(scriptVersion, VERSION_X, script_title_y);
    }

    /**
     * Draws the paint info boxes
     *
     * @param infoTitle   The info title for the box.
     * @param calculation The calculation for the box.
     * @param pos         The position for the info box.
     * @param g           Graphics.
     */
    public void drawInfo(String infoTitle, String calculation, Position pos, Graphics g) {
        g.setFont(Fonts.PAINT_INFO_FONT.getFont());
        final int BOX_W = SCRIPT_INFO_X_PADDING + SCRIPT_INFO_BOX_PADDING + getTextLength(infoTitle + calculation, g);
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.drawRect(pos.getX() + paint_background_x, pos.getY() + paint_background_y, BOX_W, pos.getHeight());
        g.setColor(Colors.LIGHT_GRAY_COLOR.getCOLOR());
        g.fillRect(pos.getX() + paint_background_x, pos.getY() + paint_background_y, BOX_W, pos.getHeight());

        final int INFO_X = pos.getX() + paint_background_x + SCRIPT_INFO_X_PADDING;
        final int INFO_Y = pos.getY() + paint_background_y + SCRIPT_INFO_Y_PADDING;
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
     */
    public void setPaintPosition(int x, int y) {
        paint_background_x = x;
        paint_background_y = y;
        script_title_x = paint_background_x + 7;
        script_title_y = paint_background_y + 2;
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
