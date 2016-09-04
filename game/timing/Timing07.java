package scripts.tribotapi.game.timing;

import org.tribot.api.General;
import org.tribot.api.Timing;

/**
 * Created by Sphiinx on 6/9/2016.
 * Re-written by Sphiinx on 7/8/2016
 */
public class Timing07 {

    /**
     * Waits for the current condition to be active.
     *
     * @param condition The condition.
     * @param timeout   How many milliseconds to wait before giving up.
     * @return True if the condition was true before the timeout; false otherwise.
     */
    public static boolean waitCondition(Condition07 condition, long timeout) {
        final long time = Timing.currentTimeMillis() + timeout;
        while (!condition.active()) {
            if (Timing.currentTimeMillis() >= time)
                return false;
            General.sleep(100);
        }
        return true;
    }

}