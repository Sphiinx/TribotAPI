package TribotAPI;

import org.tribot.api.General;

/**
 * Created by Sphiinx on 2/15/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Printing {

    public static boolean isDebugging;

    /**
     * Prints the specified text via the Client Debug with the header "Debug: ".
     *
     * @param text The text that is being printed.
     */
    public static void dev(String text) {
        if (General.getTRiBotUsername().equals("Sphiinx"))
            General.println("Debug: " + text);
    }

    /**
     * Prints the specified text via the Client Debug with the header "ERROR: ".
     *
     * @param text The text that is being printed.
     */
    public static void err(String text) {
        if (General.getTRiBotUsername().equals("Sphiinx"))
            General.println("ERROR: " + text);
    }

    /**
     * Prints the specified text via the Client Debug with the header "WARNING: "
     *
     * @param text The text that is being printed.
     */
    public static void warn(String text) {
        General.println("WARNING: " + text);
    }

    /**
     * Prints the specified text via the Client Debug with the header "Status: ".
     *
     * @param text The text that is being printed.
     */
    public static void status(String text) {
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