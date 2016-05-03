package api.Game.Walking;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSTile;

import java.awt.*;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class Walking07 {

    /**
     * Checks if a position can be randomized by an offset.
     * @author xCode, modified by Sphiinx.
     * @param area The area in which to check if the tile exists.
     * @param pos The position of the object to walk to.
     * @param offset The offset in which to randomise by.
     * @return RandomTile if successful; null otherwise.
     * */
    public static RSTile randomPosition(RSArea area, RSTile pos, int offset) {
        for (int i = 0; i < 10; i++) {
            RSTile randomTile = new RSTile(General.random(pos.getX() - offset , pos.getX() + offset), General.random(pos.getY() - offset, pos.getY() + offset));
            if (area.contains(randomTile)) {
                return randomTile;
            }
        }
        return null;
    }

    /**
     * Walks to the object via the screen if it's on the minimap, otherwise it walks with webwalking.
     *
     * @return True if the object is on the screen; false otherwise.
     * */
    public static void sceenWalkToObject(RSObject object) {
        Point obj = Projection.tileToMinimap(object);
        if (Projection.isInMinimap(obj)) {
            RSTile[] path = Walking.generateStraightScreenPath(object.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return object.isOnScreen();
                    }
                }, General.random(1200, 1500));
            }
        }
    }

    /**
     * Walks to the NPC via the screen if it's on the minimap, otherwise it walks with webwalking.
     *
     * @return True if the npc is on the screen; false otherwise.
     * */
    public static void sceenWalkToNPC(RSNPC npc) {
        Point ore = Projection.tileToMinimap(npc);
        if (Projection.isInMinimap(ore)) {
            RSTile[] path = Walking.generateStraightScreenPath(npc.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return npc.isOnScreen();
                    }
                }, General.random(1200, 1500));
            }
        } else {
            WebWalking.walkTo(npc);
        }
    }

}

