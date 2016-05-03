package api;

import org.tribot.api.General;

/**
 * Created by Sphiinx on 2/15/2016.
 */
public class Printing {

    public static boolean isDebugging;

    /**
     * Prints the specified text via the Client Debug with the header "Debug: ".
     *
     * @param text The text that is being printed.
     */
    public static void dev(Object text) {
        if (isDebugging) {
            General.println("Debug: " + text);
        }
    }

    /**
     * Prints the specified text via the Client Debug with the header "WARNING: "
     *
     * @param text The text that is being printed.
     */
    public static void warn(Object text) {
        General.println("WARNING: " + text);
    }

    /**
     * Prints the specified text via the Client Debug with the header "ERROR: ".
     *
     * @param text The text that is being printed.
     */
    public static void err(Object text) {
        if (isDebugging) {
            General.println("ERROR: " + text);
        }
    }

    /**
     * Prints the specified text via the Client Debug with the header "Status: ".
     *
     * @param text The text that is being printed.
     */
    public static void status(Object text) {
        General.println("STATUS: " + text);
    }

    /**
     * Prints the specified text via the Bot Debug.
     *
     * @param text The text that is being printed.
     */
    public static void bot(String text) {
        System.out.println("BOT: " + text);
    }

}

