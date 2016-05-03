package api;

import org.tribot.api.General;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;
import scripts.SPXAIOMiner.api.util.Timer;

/**
 * Created by Sphiinx on 2/16/2016.
 */
public class Waiting {

    /**
     * Waits until the condition is met. The timeout is reset if the local player is moving.
     *
     * @param c The condition.
     * @param timeout The timeout in milliseconds.
     * @return True if the condition was met before the timeout.
     */
    public static boolean waitMoveCondition(Condition c, long timeout) {
        Timer timer = new Timer(timeout);
        timer.start();
        RSTile start = Player.getPosition();
        while (!timer.timedOut()) {
            if (c.active()) {
                return true;
            }
            RSTile player = Player.getPosition();
            if (player.distanceTo(start) > 0.0) {
                start = new RSTile(player.getX(), player.getY(), player.getPlane());
                timer.reset();
            }
            General.sleep(25);
        }
        return false;
    }

}

