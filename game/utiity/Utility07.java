package scripts.TribotAPI.game.utiity;

import org.tribot.api.General;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

import java.awt.*;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Utility07 {

    /**
     * Formats a number by replacing zeros with 'k' or 'm' dependent on the value of the number.
     *
     * @param num The number to format.
     * @return A String of the formatted number.
     */
    public static String formatNumber(double num) {
        if (num < 1000.0)
            return Integer.toString((int) num);
        else if (Math.round(num) / 10000.0 < 100.0)
            return String.format("%.1fk", Math.round(num) / 1000.0);
        else
            return String.format("%.1fm", Math.round(num) / 1000000.0);
    }

    /**
     * Gets a random point within a rectangle.
     *
     * @param r Rectangle.
     * @return Random point in the rectangle.
     */
    public static Point getRandomPoint(Rectangle r) {
        if (r == null)
            return null;

        final int randomX = General.random(r.x, r.x + r.width);
        final int randomY = General.random(r.y, r.y + r.height);

        return new Point(randomX, randomY);
    }

    /**
     * Returns a number of periods depending on the remainder.
     * It will look somewhat like loading periods...
     *
     * @return ...
     */
    public static String getLoadingPeriods() {
        final long time = System.currentTimeMillis() % 3000;
        if (time < 600)
            return "";
        else if (time < 1300)
            return ".";
        else if (time < 2000)
            return "..";
        else
            return "...";
    }

}