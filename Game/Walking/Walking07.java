package scripts.TribotAPI.game.walking;

import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.*;

import java.awt.*;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Walking07 {

    /**
     * Walks to the RSObject via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSObject(RSObject object) {
        final Point point = Projection.tileToMinimap(object);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(object);
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(object.getPosition());
            Walking.walkScreenPath(path);
        }
    }

    /**
     * Walks to the RSGroundItem via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSGroundItem(RSGroundItem item) {
        final Point point = Projection.tileToMinimap(item);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(item);
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(item.getPosition());
            Walking.walkScreenPath(path);
        }
    }

    /**
     * Walks to the RSNPC via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSNPC(RSNPC npc) {
        final Point point = Projection.tileToMinimap(npc);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(npc);
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(npc.getPosition());
            Walking.walkScreenPath(path);
        }
    }

    /**
     * Walks to the RSPlayer via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSPlayer(RSPlayer player) {
        final Point point = Projection.tileToMinimap(player);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(player);
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(player.getPosition());
            Walking.walkScreenPath(path);
        }
    }

}

