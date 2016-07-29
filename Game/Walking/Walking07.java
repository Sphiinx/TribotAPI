package scripts.TribotAPI.game.walking;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Projection;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.*;
import scripts.TribotAPI.game.npcs.NPCs07;

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
        if (object == null)
            return;

        final Point point = Projection.tileToMinimap(object);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(object, new Condition() {
                @Override
                public boolean active() {
                    final Point point = Projection.tileToMinimap(object);
                    return Projection.isInMinimap(point);
                }
            }, General.random(250, 500));
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(object.getPosition());
            Walking.walkScreenPath(path, new Condition() {
                @Override
                public boolean active() {
                    return object.isOnScreen();
                }
            }, General.random(250, 500));
        }
    }

    /**
     * Walks to the RSGroundItem via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSGroundItem(RSGroundItem item) {
        if (item == null)
            return;

        final Point point = Projection.tileToMinimap(item);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(item, new Condition() {
                @Override
                public boolean active() {
                    final Point point = Projection.tileToMinimap(item);
                    return Projection.isInMinimap(point);
                }
            }, General.random(250, 500));
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(item.getPosition());
            Walking.walkScreenPath(path, new Condition() {
                @Override
                public boolean active() {
                    return item.isOnScreen();
                }
            }, General.random(250, 500));
        }
    }

    /**
     * Walks to the RSNPC via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSNPC(RSNPC npc) {
        if (npc == null)
            return;

        final Point point = Projection.tileToMinimap(npc);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(npc, new Condition() {
                @Override
                public boolean active() {
                    final Point point = Projection.tileToMinimap(npc);
                    return Projection.isInMinimap(point);
                }
            }, General.random(250, 500));
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(npc.getPosition());
            Walking.walkScreenPath(path, new Condition() {
                @Override
                public boolean active() {
                    return npc.isOnScreen();
                }
            }, General.random(250, 500));
        }
    }

    /**
     * Walks to the RSPlayer via the screen if it's on the mini-map; uses Webwalking if it's not.
     */
    public static void screenWalkToRSPlayer(RSPlayer player) {
        if (player == null)
            return;

        final Point point = Projection.tileToMinimap(player);

        if (!Projection.isInMinimap(point)) {
            WebWalking.walkTo(player, new Condition() {
                @Override
                public boolean active() {
                    final Point point = Projection.tileToMinimap(player);
                    return Projection.isInMinimap(point);
                }
            }, General.random(250, 500));
        } else {
            final RSTile[] path = Walking.generateStraightScreenPath(player.getPosition());
            Walking.walkScreenPath(path, new Condition() {
                @Override
                public boolean active() {
                    return player.isOnScreen();
                }
            }, General.random(250, 500));
        }
    }

}

