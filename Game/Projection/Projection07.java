package scripts.API.Game.Projection;

import org.tribot.api2007.Projection;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import java.awt.*;

/**
 * Created by Sphiinx on 1/28/2016.
 */
public class Projection07 {

    public static final Color WHITE_COLOR = new Color(255, 255, 255, 25);
    public static final Color BOLD_WHITE_COLOR = new Color(255, 255, 255, 75);
    public static final Color BOLDEST_WHITE_COLOR = new Color(255, 255, 255, 125);
    public static final Color YELLOW_COLOR = new Color(255, 247, 0, 25);
    public static final Color BOLD_YELLOW_COLOR = new Color(255, 247, 0, 75);
    public static final Color BOLDEST_YELLOW_COLOR = new Color(255, 247, 0, 125);

    /**
     * Attempts to draw the polygons of the given RSObjects.
     *
     * @param objects The objects in which to draw.
     * @param g      Graphics parameter.
     */
    public static void drawObjects(final RSObject[] objects, Graphics g) {
        if (objects.length > 0) {
            for (RSObject object : objects) {
                if (object.isOnScreen()) {
                    for (Polygon p : object.getModel().getTriangles()) {
                        g.setColor(WHITE_COLOR);
                        g.fillPolygon(p);
                        g.setColor(BOLD_YELLOW_COLOR);
                        g.drawPolygon(p);
                    }
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSObjects.
     *
     * @param objects The objects in which to draw.
     * @param g      Graphics parameter.
     */
    public static void drawMinimapObjects(final RSObject[] objects, Graphics g) {
        if (objects.length > 0) {
            for (RSObject object : objects) {
                Point p = Projection.tileToMinimap(object);
                if (Projection.isInMinimap(p)) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(p.x, p.y, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawOval(p.x, p.y, 10, 10);
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSObject.
     *
     * @param object The object in which to draw.
     * @param g      Graphics parameter.
     */
    public static void drawObject(final RSObject[] object, Graphics g) {
        if (object.length > 0) {
            if (object[0].isOnScreen()) {
                for (Polygon p : object[0].getModel().getTriangles()) {
                    g.setColor(WHITE_COLOR);
                    g.fillPolygon(p);
                    g.setColor(BOLD_YELLOW_COLOR);
                    g.drawPolygon(p);
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSObject.
     *
     * @param object The object in which to draw.
     * @param g      Graphics parameter.
     */
    public static void drawMinimapObject(final RSObject[] object, Graphics g) {
        if (object.length > 0) {
            Point p = Projection.tileToMinimap(object[0]);
            if (Projection.isInMinimap(p)) {
                g.setColor(Color.YELLOW);
                g.fillOval(p.x, p.y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(p.x, p.y, 10, 10);
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSNPCs.
     *
     * @param npcs The NPCs in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawNPCs(final RSNPC[] npcs, Graphics g) {
        if (npcs.length > 0) {
            for (RSNPC npc : npcs) {
                if (npc.isOnScreen()) {
                    for (Polygon p : npc.getModel().getTriangles()) {
                        g.setColor(BOLD_WHITE_COLOR);
                        g.fillPolygon(p);
                        g.setColor(YELLOW_COLOR);
                        g.drawPolygon(p);
                    }
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSNPCs.
     *
     * @param npcs The NPCs in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawMinimapNPCs(final RSNPC[] npcs, Graphics g) {
        if (npcs.length > 0) {
            for (RSNPC npc : npcs) {
                Point p = Projection.tileToMinimap(npc);
                if (Projection.isInMinimap(p)) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(p.x, p.y, 10, 10);
                    g.setColor(Color.BLACK);
                    g.drawOval(p.x, p.y, 10, 10);
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSNPC.
     *
     * @param npc The NPC in which to draw.
     * @param g   Graphics parameter.
     */
    public static void drawNPC(final RSNPC[] npc, Graphics g) {
        if (npc.length > 0) {
            if (npc[0].isOnScreen()) {
                for (Polygon p : npc[0].getModel().getTriangles()) {
                    g.setColor(BOLD_WHITE_COLOR);
                    g.fillPolygon(p);
                    g.setColor(YELLOW_COLOR);
                    g.drawPolygon(p);
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given RSNPC.
     *
     * @param npc The NPC in which to draw.
     * @param g   Graphics parameter.
     */
    public static void drawMinimapNPC(final RSNPC[] npc, Graphics g) {
        if (npc.length > 0) {
            Point p = Projection.tileToMinimap(npc[0]);
            if (Projection.isInMinimap(p)) {
                g.setColor(Color.YELLOW);
                g.fillOval(p.x, p.y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(p.x, p.y, 10, 10);
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given Tile.
     *
     * @param tile The Tile in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawTile(final RSTile tile, Graphics g) {
        if (tile != null) {
            if (tile.isOnScreen()) {
                Polygon p = Projection.getTileBoundsPoly(tile, 0);
                g.setColor(BOLDEST_WHITE_COLOR);
                g.fillPolygon(p);
                g.setColor(BOLDEST_YELLOW_COLOR);
                g.drawPolygon(p);
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given Tile.
     *
     * @param tile The Tile in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawMinimapTile(final RSTile tile, Graphics g) {
        if (tile != null) {
            Point p = Projection.tileToMinimap(tile);
            if (Projection.isInMinimap(p)) {
                g.setColor(Color.YELLOW);
                g.fillOval(p.x, p.y, 10, 10);
                g.setColor(Color.BLACK);
                g.drawOval(p.x, p.y, 10, 10);
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given Area.
     *
     * @param area The Area in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawArea(final RSArea area, Graphics g) {
        if (area != null) {
            if (area.getAllTiles().length > 0) {
                for (RSTile t : area.getAllTiles()) {
                    if (t.isOnScreen()) {
                        Polygon p = Projection.getTileBoundsPoly(t, 0);
                        g.setColor(BOLDEST_WHITE_COLOR);
                        g.fillPolygon(p);
                        g.setColor(BOLDEST_YELLOW_COLOR);
                        g.drawPolygon(p);
                    }
                }
            }
        }
    }

    /**
     * Attempts to draw the polygons of the given Area.
     *
     * @param area The Area in which to draw.
     * @param g    Graphics parameter.
     */
    public static void drawMinimapArea(final RSArea area, Graphics g) {
        if (area != null) {
            if (area.getAllTiles().length > 0) {
                for (RSTile t : area.getAllTiles()) {
                    Point p = Projection.tileToMinimap(t);
                    if (Projection.isInMinimap(p)) {
                        g.setColor(BOLDEST_WHITE_COLOR);
                        g.fillOval(p.x, p.y, 5, 5);
                        g.setColor(BOLDEST_YELLOW_COLOR);
                        g.drawOval(p.x, p.y, 5, 5);
                    }
                }
            }
        }
    }

}

