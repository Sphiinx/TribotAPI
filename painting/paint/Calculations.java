package scripts.TribotAPI.painting.paint;

import java.text.NumberFormat;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class Calculations {

    public static long getTimeRan(long startTime) {
        return System.currentTimeMillis() - startTime;
    }

    public static String getPerHour(int amount, long runTime) {
        return " (" + Long.toString((int) ((3600000.0 / runTime) * amount)) + "/hr)";
    }

    public static String formatNumber(long number) {
        return NumberFormat.getIntegerInstance().format(number);
    }

}
