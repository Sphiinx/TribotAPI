package scripts.tribotapi.painting.projection;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Projection;
import org.tribot.api2007.types.*;
import scripts.tribotapi.color.Colors;
import scripts.tribotapi.game.entities.Entities07;
import scripts.tribotapi.game.player.Player07;

import java.awt.*;

/**
 * Created by Sphiinx on 1/28/2016.
 * Re-written by Sphiinx on 7/29/2016
 */
public class ProjectionManager {

    /**
     * The master interface for the friends list.
     */
    private final int FRIENDS_LIST_INTERFACE = 429;

    /**
     * The master child interface for the friends list.
     */
    private final int FRIENDS_LIST_INTERFACE_CHILD = 3;


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
        final RSInterface friends_list = Interfaces.get(FRIENDS_LIST_INTERFACE, FRIENDS_LIST_INTERFACE_CHILD);
        if (friends_list == null)
            return;

        final RSInterface[] friends_list_children = friends_list.getChildren();
        if (friends_list_children == null)
            return;

        for (int i = 0; i <= friends_list_children.length; i++) {
            final RSInterface friends_list_component = friends_list.getChild(i);
            if (friends_list_component == null)
                continue;

            final String friends_list_component_text = friends_list_component.getText();
            if (friends_list_component_text == null)
                continue;

            if (friends_list_component.getText().contains("World") && friends_list_component.getText().contains("Offline"))
                continue;

            final RSPlayer player = Player07.getPlayer(friends_list_component.getText());
            if (player == null)
                continue;

            g.setColor(Color.GREEN);
            final Point player_body = player.getModel().getCentrePoint();
            final Point player_minimap = Projection.tileToMinimap(player);
            if (Projection.isInMinimap(player_minimap))
                g.drawString(player.getName(), player_minimap.x, player_minimap.y);

            if (player.isOnScreen())
                g.drawString(player.getName(), player_body.x, player_body.y);
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

        final Rectangle area = item.getArea();
        if (area == null)
            return;

        final Rectangle r = area.getBounds();
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

        final Polygon p = Projection.getTileBoundsPoly(tile, 0);
        if (p == null)
            return;

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

        final Point point = Projection.tileToMinimap(tile);
        if (!Projection.isInMinimap(point))
            return;

        if (r > 15)
            r = 15;

        r *= 9;
        final int x = point.x - (r / 2);
        final int y = point.y - (r / 2);

        g.setColor(Colors.GRAY_COLOR.getCOLOR());
        g.fillOval(x, y, r, r);
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawOval(x, y, r, r);
    }

}