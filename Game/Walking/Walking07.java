package TribotAPI.game.walking;

import org.tribot.api.General;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.*;
import TribotAPI.game.timing.Timing07;

import java.awt.*;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Walking07 {

    /**
     * Walks to the RSObject via the screen if it's on the mini-map; uses web-walking if it's not.
     */
    public static void screenWalkToRSObject(RSObject object) {
        Point point = Projection.tileToMinimap(object);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(object);
        } else {
            RSTile[] path = Walking.generateStraightScreenPath(object.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing07.waitCondition(object::isOnScreen, General.random(1000, 1200));
            }
        }
    }

    /**
     * Walks to the RSGroundItem via the screen if it's on the mini-map; uses web-walking if it's not.
     */
    public static void screenWalkToRSGroundItem(RSGroundItem item) {
        Point point = Projection.tileToMinimap(item);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(item);
        } else {
            RSTile[] path = Walking.generateStraightScreenPath(item.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing07.waitCondition(item::isOnScreen, General.random(1000, 1200));
            }
        }
    }

    /**
     * Walks to the RSNPC via the screen if it's on the mini-map; uses web-walking if it's not.
     */
    public static void screenWalkToRSNPC(RSNPC npc) {
        Point point = Projection.tileToMinimap(npc);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(npc);
        } else {
            RSTile[] path = Walking.generateStraightScreenPath(npc.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing07.waitCondition(npc::isOnScreen, General.random(1000, 1200));
            }
        }
    }

    /**
     * Walks to the RSPlayer via the screen if it's on the mini-map; uses web-walking if it's not.
     */
    public static void screenWalkToRSPlayer(RSPlayer player) {
        Point point = Projection.tileToMinimap(player);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(player);
        } else {
            RSTile[] path = Walking.generateStraightScreenPath(player.getPosition());
            if (Walking.walkScreenPath(path)) {
                Timing07.waitCondition(player::isOnScreen, General.random(1000, 1200));
            }
        }
    }

}

