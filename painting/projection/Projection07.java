package scripts.TribotAPI.painting.projection;

import org.tribot.api2007.Interfaces;
import scripts.TribotAPI.game.combat.Combat07;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.*;
import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.game.entities.Entities07;
import scripts.TribotAPI.game.player.Player07;
import scripts.TribotAPI.util.Utility;

import java.awt.*;

/**
 * Created by Sphiinx on 1/28/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Projection07 {

    /**
     * The mule_username interface for the friends list.
     */
    private final int FRIENDS_LIST_INTERFACE = 429;

    /**
     * The mule_username child interface for the friends list.
     */
    private final int FRIENDS_LIST_INTERFACE_CHILD = 3;

    /**
     * The mule_username x position for the RSNPC information box.
     */
    private int info_box_x = 10;

    /**
     * The mule_username y position for the RSNPC information box.
     */
    private int info_box_y = 25;

    /**
     * The mule_username width for the RSNPC information box.
     */
    private int info_box_w = 0;

    /**
     * The mule_username height for the RSNPC information box.
     */
    private int info_box_h = 50;

    /**
     * Draws the enclosed area of the given RSObject.
     *
     * @param object The RSObject in which to draw.
     * @param g      Graphics.
     */
    public void drawObject(RSObject object, Graphics g) {
        if (object == null)
            return;

        if (!object.isOnScreen())
            return;

        final Polygon area = Entities07.getModelArea(object.getModel());
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillPolygon(area);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawPolygon(area);
    }

    /**
     * Draws the enclosed area of the given RSNPC.
     *
     * @param npc The RSNPC in which to draw.
     * @param g   Graphics.
     */
    public void drawNPC(RSNPC npc, Graphics g) {
        if (npc == null)
            return;

        if (!npc.isOnScreen())
            return;

        final Polygon area = Entities07.getModelArea(npc.getModel());
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillPolygon(area);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawPolygon(area);
    }

    /**
     * Draws the enclosed area of the given RSPlayer.
     *
     * @param player The RSPlayer in which to draw.
     * @param g      Graphics.
     */
    public void drawPlayer(RSPlayer player, Graphics g) {
        if (player == null)
            return;

        if (!player.isOnScreen())
            return;

        final Polygon area = Entities07.getModelArea(player.getModel());
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillPolygon(area);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawPolygon(area);
    }

    /**
     * Draws all the players names who are on our friends list in the area or on the mini-map.
     *
     * @param g Graphics.
     */
    public void drawFriendFinder(Graphics g) {
        RSInterface friends_list = Interfaces.get(FRIENDS_LIST_INTERFACE, FRIENDS_LIST_INTERFACE_CHILD);
        if (friends_list == null)
            return;

        for (int i = 0; i <= 401; i++) {
            RSInterface friends_list_component = friends_list.getChild(i);
            if (friends_list_component == null)
                return;

            if (!friends_list_component.getText().contains("World") && !friends_list_component.getText().contains("Offline")) {
                RSPlayer player = Player07.getPlayer(friends_list_component.getText());

                if (player != null) {
                    g.setColor(Color.GREEN);
                    Point player_body = player.getModel().getCentrePoint();
                    Point player_minimap = Projection.tileToMinimap(player);
                    if (Projection.isInMinimap(player_minimap))
                        g.drawString(player.getName(), player_minimap.x, player_minimap.y);

                    if (player.isOnScreen())
                        g.drawString(player.getName(), player_body.x, player_body.y);
                }
            }
        }
    }

    /**
     * Draws the enclosed area of the given RSGroundItem.
     *
     * @param item The RSGroundItem in which to draw.
     * @param g    Graphics.
     */
    public void drawGroundItem(RSGroundItem item, Graphics g) {
        if (item == null)
            return;

        if (!item.isOnScreen())
            return;

        final Polygon area = Entities07.getModelArea(item.getModel());
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillPolygon(area);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawPolygon(area);
    }

    /**
     * Draws the enclosed area of the given RSItem.
     *
     * @param item The RSItem in which to draw.
     * @param g    Graphics.
     */
    public void drawItem(RSItem item, Graphics g) {
        if (item == null)
            return;

        if (Inventory.getCount(item.getID()) <= 0)
            return;

        Rectangle r = item.getArea().getBounds();
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillRect(r.x, r.y, r.width, r.height);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawRect(r.x, r.y, r.width, r.height);
    }

    /**
     * Draws the enclosed area of the given RSTile.
     *
     * @param tile The RSTile in which to draw.
     * @param g    Graphics.
     */
    public void drawTile(RSTile tile, Graphics g) {
        if (tile == null)
            return;

        if (!tile.isOnScreen())
            return;

        Polygon p = Projection.getTileBoundsPoly(tile, 0);
        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillPolygon(p);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawPolygon(p);
    }

    /**
     * Draws the enclosed area of the given RSArea.
     *
     * @param area The RSArea in which to draw.
     * @param g    Graphics.
     */
    public void drawArea(RSArea area, Graphics g) {
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
    public void drawMinimapArea(RSTile tile, int r, Graphics g) {
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

        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillOval(x, y, r, r);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawOval(x, y, r, r);
    }

    /**
     * Draws the target information of the given RSplayer on the screen.
     *
     * @param player_name The RSPlayer's name in which to draw information.
     * @param g      Graphics.
     */
    public void drawTargetInfo(String player_name, Graphics g) {
        RSPlayer player = Player07.getPlayer(player_name);
        if (player == null)
            return;

        if (!player.isOnScreen())
            return;

        RSModel model = player.getModel();
        if (model == null)
            return;

        if (!Combat07.isInCombatWithMe(player))
            return;

        String playerHealth = player.getHealth() + " / " + player.getMaxHealth();
        String playerLevel = "Level: " + player.getCombatLevel();

        int playerTitleWidth = Utility.getTextLength(player.getName(), g);
        int playerHealthWidth = Utility.getTextLength(playerHealth, g);
        int playerLevelWidth = Utility.getTextLength(playerLevel, g);

        info_box_w = getBoxWidth(playerTitleWidth);

        if (info_box_w < 135)
            info_box_w = 135;

        int INFO_BOX_TITLE_X = getInfoBoxXCenter(info_box_w, playerTitleWidth, info_box_x);
        int INFO_BOX_LEVEL_X = getInfoBoxXCenter(info_box_w, playerLevelWidth, info_box_x);
        int INFO_BOX_HEALTH_X = getInfoBoxXCenter(info_box_w, playerHealthWidth, info_box_x);

        int INFO_BOX_HEALTH_Y = getInfoBoxYCenter(info_box_h, 2, g.getFontMetrics().getHeight(), info_box_y);
        int INFO_BOX_HEALTH_BAR_Y = getInfoBoxYCenter(info_box_h, 4, info_box_h / 3, info_box_y);

        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillRect(info_box_x, info_box_y, info_box_w, info_box_h);

        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawRect(info_box_x, info_box_y, info_box_w, info_box_h);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(player.getName(), INFO_BOX_TITLE_X, info_box_y + 12);
        g.drawString(playerLevel, INFO_BOX_LEVEL_X, info_box_y + info_box_h - 2);

        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.fillRect(info_box_x + 6, INFO_BOX_HEALTH_BAR_Y, info_box_w - 12, info_box_h / 3);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(playerHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

        g.setColor(Colors.GREEN_COLOR.getCOLOR());
        g.fillRect(info_box_x + 6, INFO_BOX_HEALTH_BAR_Y, (player.getHealth() * info_box_w) / player.getMaxHealth() - 12, info_box_h / 3);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(playerHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

    }

    /**
     * Draws the target information of the given RSNPC on the screen.
     *
     * @param npc The NPCs in which to draw information.
     * @param g   Graphics.
     */
    public void drawTargetInfo(RSNPC npc, Graphics g) {
        if (npc == null)
            return;

        if (!npc.isOnScreen())
            return;

        RSModel model = npc.getModel();
        if (model == null)
            return;

        if (!Combat07.isInCombatWithMe(npc))
            return;

        String npcHealth = npc.getHealth() + " / " + npc.getMaxHealth();
        String npcLevel = "Level: " + npc.getCombatLevel();

        int npcTitleWidth = Utility.getTextLength(npc.getName(), g);
        int npcHealthWidth = Utility.getTextLength(npcHealth, g);
        int npcLevelWidth = Utility.getTextLength(npcLevel, g);

        info_box_w = getBoxWidth(npcTitleWidth);

        if (info_box_w < 135)
            info_box_w = 135;

        int INFO_BOX_TITLE_X = getInfoBoxXCenter(info_box_w, npcTitleWidth, info_box_x);
        int INFO_BOX_LEVEL_X = getInfoBoxXCenter(info_box_w, npcLevelWidth, info_box_x);
        int INFO_BOX_HEALTH_X = getInfoBoxXCenter(info_box_w, npcHealthWidth, info_box_x);

        int INFO_BOX_HEALTH_Y = getInfoBoxYCenter(info_box_h, 2, g.getFontMetrics().getHeight(), info_box_y);
        int INFO_BOX_HEALTH_BAR_Y = getInfoBoxYCenter(info_box_h, 4, info_box_h / 3, info_box_y);

        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillRect(info_box_x, info_box_y, info_box_w, info_box_h);

        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawRect(info_box_x, info_box_y, info_box_w, info_box_h);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(npc.getName(), INFO_BOX_TITLE_X, info_box_y + 12);
        g.drawString(npcLevel, INFO_BOX_LEVEL_X, info_box_y + info_box_h - 2);

        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.fillRect(info_box_x + 6, INFO_BOX_HEALTH_BAR_Y, info_box_w - 12, info_box_h / 3);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(npcHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

        g.setColor(Colors.GREEN_COLOR.getCOLOR());
        g.fillRect(info_box_x + 6, INFO_BOX_HEALTH_BAR_Y, (npc.getHealth() * info_box_w) / npc.getMaxHealth() - 12, info_box_h / 3);

        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(npcHealth, INFO_BOX_HEALTH_X, INFO_BOX_HEALTH_Y);

    }

    /**
     * Sets the information mule_username x position and y position.
     *
     * @param x The x position.
     * @param y The y position.
     */
    public void setInfoBoxPosition(int x, int y) {
        info_box_x = x;
        info_box_y = y;
    }

    /**
     * Sets the information box mule_username width and height.
     *
     * @param w The width.
     * @param h The height.
     */
    public void setInfoBoxDimensions(int w, int h) {
        info_box_w = w;
        info_box_h = h;
    }

    /**
     * Gets the RSNPC info box width.
     *
     * @param npcTitleWidth The pixel width of the RSNPC info box title.
     * @return The RSNPC info box width.
     */
    private int getBoxWidth(int npcTitleWidth) {
        return npcTitleWidth * 3;
    }

    /**
     * Gets the center x position of the box width.
     *
     * @param boxWidth    The width of the box.
     * @param objectWidth The width of the object to be centered.
     * @param boxX        The x position of the box.
     * @return The center x position of the box width.
     */
    private int getInfoBoxXCenter(int boxWidth, int objectWidth, int boxX) {
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
    private int getInfoBoxYCenter(int boxHeight, int offset, int objectHeight, int boxY) {
        return (boxHeight / offset) + (objectHeight / 4) + boxY;
    }

}