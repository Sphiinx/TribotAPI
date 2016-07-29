package scripts.TribotAPI.painting.paint;

import org.tribot.api.Timing;
import org.tribot.api2007.Skills;
import scripts.TribotAPI.game.utiity.Utility07;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class Calculations {

    /**
     * Gets the amount per hour with /hr formatting.
     *
     * @param amount The amount to get the /hr of.
     * @param run_time The script run time.
     * */
    public static String getPerHour(int amount, long run_time) {
        return " (" + Long.toString((int) ((3600000.0 / run_time) * amount)) + "/hr)";
    }

    /**
     * Gets the GP per hour with /hr formatting.
     *
     * @param amount The amount to get the /hr of.
     * @param run_time The script run time.
     * */
    public static String getGPPerHour(int amount, long run_time) {
        return " (" + Utility07.formatNumber((3600000.0 / run_time) * amount) + "/hr)";
    }

    /**
     * Gets the xp per hour with xp/hr formatting.
     *
     * @param amount The amount to get the /hr of.
     * @param run_time The script run time.
     * */
    public static String getXPPerHour(int amount, long run_time) {
        return Utility07.formatNumber((3600000.0 / run_time) * amount) + " xp/hr";
    }

    /**
     * Gets the time to next level with time formatting.
     *
     * @param skill The skill to get the time to next level.
     * @param xp_gained The amount of xp gained.
     * @param run_time The script run time.
     * */
    public static String getTimeToNextLevel(Skills.SKILLS skill, int xp_gained, long run_time) {
        if (skill.getActualLevel() >= 99 || xp_gained == 0)
            return "00:00:00";

        return Timing.msToString(((Skills.getXPToNextLevel(skill)) / xp_gained) * run_time);
    }

}
