package TribotAPI.game.projection;

import org.tribot.api2007.Inventory;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.*;
import TribotAPI.color.Colors;

import java.awt.*;

/**
 * Created by Sphiinx on 1/28/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Projection07 {

    /**
     * The master x axis for the RSNPC information box.
     */
    public static final int INFO_BOX_X = 10;

    /**
     * The master y axis for the RSNPC information box.
     */
    public static final int INFO_BOX_Y = 25;

    /**
     * The master width for the RSNPC information box.
     */
    public static int INFO_BOX_W = 0;

    /**
     * The master height for the RSNPC information box.
     */
    public static final int INFO_BOX_H = 50;

    /**
     * Draws the enclosed area of the given RSObject.
     *
     * @param object The RSObject in which to draw.
     * @param g      Graphics.
     */
    public static void drawObject(RSObject object, Graphics g) {
        if (object == null)
            return;

        if (!object.isOnScreen())
            return;

        Polygon p = object.getModel().getEnclosedArea();
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSNPC.
     *
     * @param npc The RSNPC in which to draw.
     * @param g   Graphics.
     */
    public static void drawNPC(RSNPC npc, Graphics g) {
        if (npc == null)
            return;

        if (!npc.isOnScreen())
            return;

        Polygon p = npc.getModel().getEnclosedArea();
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSPlayer.
     *
     * @param player The RSPlayer in which to draw.
     * @param g      Graphics.
     */
    public static void drawRSPlayer(RSPlayer player, Graphics g) {
        if (player == null)
            return;

        if (!player.isOnScreen())
            return;

        Polygon p = player.getModel().getEnclosedArea();
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSGroundItem.
     *
     * @param item The RSGroundItem in which to draw.
     * @param g    Graphics.
     */
    public static void drawRSGroundItem(RSGroundItem item, Graphics g) {
        if (item == null)
            return;

        if (!item.isOnScreen())
            return;

        Polygon p = item.getModel().getEnclosedArea();
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSItem.
     *
     * @param item The RSItem in which to draw.
     * @param g    Graphics.
     */
    public static void drawRSItem(RSItem item, Graphics g) {
        if (item == null)
            return;

        if (Inventory.getCount(item.getID()) <= 0)
            return;

        Rectangle r = item.getArea().getBounds();
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    /**
     * Draws the enclosed area of the given RSTile.
     *
     * @param tile The RSTile in which to draw.
     * @param g    Graphics.
     */
    public static void drawTile(RSTile tile, Graphics g) {
        if (tile == null)
            return;

        if (!tile.isOnScreen())
            return;

        Polygon p = Projection.getTileBoundsPoly(tile, 0);
        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSArea.
     *
     * @param area The RSArea in which to draw.
     * @param g    Graphics.
     */
    public static void drawArea(RSArea area, Graphics g) {
        if (area == null)
            return;

        for (RSTile tile : area.getAllTiles()) {
            if (tile.isOnScreen()) {
                drawTile(tile, g);
            }
        }
    }

    /**
     * Draws the enclosed area of the given RSTile to the mini-map.
     *
     * @param tile The center RSTile.
     * @param r    The radius of the circle.
     * @param g    Graphics.
     */
    public static void drawMinimapArea(RSTile tile, int r, Graphics g) {
        if (tile == null)
            return;

        Point point = Projection.tileToMinimap(tile);
        if (!Projection.isInMinimap(point))
            return;

        if (r > 15)
            r = 15;

        r *= 9;
        int x = point.x - (r / 2);
        int y = point.y - (r / 2);

        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillOval(x, y, r, r);
        g.setColor(Colors.RED_COLOR.getColor());
        g.drawOval(x, y, r, r);
    }

    /**
     * Draws the target information of the given RSNPC on the screen.
     *
     * @param npc The NPCs in which to draw information.
     * @param g   Graphics.
     */
    public static void drawTargetInfo(RSNPC npc, Graphics g) {
        if (npc == null)
            return;

        if (!npc.isOnScreen())
            return;

        RSModel model = npc.getModel();
        if (model == null)
            return;

        if (!npc.isInteractingWithMe())
            return;

        String npcHealth = npc.getHealth() + " / " + npc.getMaxHealth();
        String npcLevel = "Level: " + npc.getCombatLevel();

        int npcTitleWidth = getStringWidth(npc.getName(), g);
        int npcHealthWidth = getStringWidth(npcHealth, g);
        int npcLevelWidth = getStringWidth(npcLevel, g);

        INFO_BOX_W = getBoxWidth(npcTitleWidth);

        if (INFO_BOX_W < 135)
            INFO_BOX_W = 135;

        int INFO_BOX_TITLE_X = getInfoBoxXCenter(INFO_BOX_W, npcTitleWidth, INFO_BOX_X);
        int INFO_BOX_LEVEL_X = getInfoBoxXCenter(INFO_BOX_W, npcLevelWidth, INFO_BOX_X);
        int INFO_BOX_HEALTH_X = getInfoBoxXCenter(INFO_BOX_W, npcHealthWidth, INFO_BOX_X);

        int INFO_BOX_HEALTH_Y = getInfoBoxYCenter(INFO_BOX_H, 2, g.getFontMetrics().getHeight(), INFO_BOX_Y);
        int INFO_BOX_HEALTH_BAR_Y = getInfoBoxYCenter(INFO_BOX_H, 4, INFO_BOX_H / 3, INFO_BOX_Y);

        g.setColor(Colors.GRAY_COLOR.getColor());
        g.fillRect(INFO_BOX_X, INFO_BOX_Y, INFO_BOX_W, INFO_BOX_H);

        g.setColor(Colors.RED_COLOR.getColor());
        g.drawRect(INFO_BOX_X, INFO_BOX_Y, INFO_BOX_W, INFO_BOX_H);

        g.setColor(Color.WHITE);
        g.drawString(npc.getName(), INFO_BOX_TITLE_X, INFO_BOX_Y + 12);
        g.drawString(npcLevel, INFO_BOX_LEVEL_X, INFO_BOX_Y + INFO_BOX_H - 2);

        g.setColor(Colors.RED_COLOR.getColor());
        g.fillRect(INFO_BOX_X + 6, INFO_BOX_HEALTH_BAR_Y, INFO_BOX_W - 12, INFO_BOX_H / 3);

        g.setColor(Color.WHITE);
        g.drawString(npcHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

        g.setColor(Colors.GREEN_COLOR.getColor());
        g.fillRect(INFO_BOX_X + 6, INFO_BOX_HEALTH_BAR_Y, (npc.getHealth() * INFO_BOX_W) / npc.getMaxHealth() - 12, INFO_BOX_H / 3);

        g.setColor(Color.WHITE);
        g.drawString(npcHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

    }

    /**
     * Gets the RSNPC info box width.
     *
     * @param npcTitleWidth The pixel width of the RSNPC info box title.
     * @return The RSNPC info box width.
     */
    private static int getBoxWidth(int npcTitleWidth) {
        return npcTitleWidth * 3;
    }

    /**
     * Gets the width of a String.
     *
     * @param text The text to get the width of.
     * @param g    Graphics.
     * @return The width of the string.
     */
    private static int getStringWidth(String text, Graphics g) {
        return g.getFontMetrics().stringWidth(text);
    }

    /**
     * Gets the center x position of the box width.
     *
     * @param boxWidth    The width of the box.
     * @param objectWidth The width of the object to be centered.
     * @param boxX        The x position of the box.
     * @return The center x position of the box width.
     */
    private static int getInfoBoxXCenter(int boxWidth, int objectWidth, int boxX) {
        return (boxWidth - objectWidth) / 2 + boxX;
    }

    /**
     * Gets the center y position of the box width.
     *
     * @param boxHeight    The height of the box.
     * @param offset       The offset of the object to be centered.
     * @param objectHeight The height of the object to be centered.
     * @param boxY         The y position of the box.
     * @return The center y position of the box height.
     */
    private static int getInfoBoxYCenter(int boxHeight, int offset, int objectHeight, int boxY) {
        return (boxHeight / offset) + (objectHeight / 4) + boxY;
    }

}