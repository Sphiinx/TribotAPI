package scripts.tribotapi.util;

import org.tribot.api.General;

/**
 * Created by Sphiinx on 2/15/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Logging {

    /**
     * The master string with the Scripter username.
     */
    private static final String scripter = "Sphiinx";

    /**
     * Prints the specified message via the Bot Debug.
     *
     * @param message The message that is being printed.
     */
    public static void botdebug(String message) {
        if (General.getTRiBotUsername().equals(scripter))
            System.out.println("[BOT]: " + message);
    }

    /**
     * Prints the specified message via the Client Debug with the header "Debug: ".
     *
     * @param message The message that is being printed.
     */
    public static void clientdebug(String message) {
        if (General.getTRiBotUsername().equals(scripter))
            General.println("[DEBUG]: " + message);
    }

    /**
     * Prints the specified message via the Client Debug with the header "ERROR: ".
     *
     * @param message The message that is being printed.
     */
    public static void error(String message) {
        General.println("[ERROR]: " + message);
    }

    /**
     * Prints the specified message via the Client Debug with the header "WARNING: "
     *
     * @param message The message that is being printed.
     */
    public static void warning(String message) {
        General.println("[WARNING]: " + message);
    }

    /**
     * Prints the specified message via the Client Debug with the header "Status: ".
     *
     * @param message The message that is being printed.
     */
    public static void status(String message) {
        General.println("[STATUS]: " + message);
    }

}