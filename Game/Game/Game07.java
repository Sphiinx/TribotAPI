package scripts.tribotapi.game.game;

import org.tribot.api2007.Game;
import org.tribot.api2007.Login;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Game07 {

    /**
     * Checks if the RSPlayer is in-game.
     * @return True if the RSPlayer is in-game; false otherwise.
     * */
    public static boolean isInGame() {
        return Login.getLoginState() == Login.STATE.INGAME && Game.getGameState() == 30;
    }

    /**
     * Checks if the RSPlayer is at the welcome screen.
     * @return True if the RSPLayer is at the welcome screen; false otherwise.
     * */
    public static boolean isAtWelcomeScreen() {
        return Login.getLoginState() == Login.STATE.WELCOMESCREEN;
    }

    /**
     * Checks if the RSPlayer is at the login screen.
     * @return True if the RSPlayer is at login screen; false otherwise.
     * */
    public static boolean isAtLoginScreen() {
        return Login.getLoginState() == Login.STATE.LOGINSCREEN && Game.getGameState() == 10;
    }

}

