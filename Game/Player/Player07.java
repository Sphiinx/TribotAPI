package scripts.API.Game.Player;

import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;


/**
 * Created by Sphiinx on 1/10/2016.
 */
public class Player07 {

    /**
     * Checks if the player is poisoned.
     *
     * @return True if poisoned; false otherwise.
     */
    public static boolean isPoisoned() {
        return Game.getSetting(102) > 0;
    }

    /**
     * Gets the players combat level. Note - I realised there was an API method for this after I wrote it. I'm just going to leave it here...
     *
     * @return The players combat level.
     */
    public static int getCombatLevel() {
        RSInterface level = Interfaces.get(593, 2);
        if (level != null) {
            String text = level.getText();
            if (text != null) {
                text = text.replace("Combat Lvl: ", "");
                int parse = Integer.parseInt(text);
                if (parse > 0) {
                    return parse;
                }
            }
        }
        return -1;
    }

    /**
     * Gets the players wilderness level.
     *
     * @return The players wilderness level.
     * */
    public static int getLevel() {
        if (!Interfaces.isInterfaceValid(90)) {
            return 0;
        }
        RSInterfaceChild child = Interfaces.get(90, 26);
        if (child == null) {
            return 0;
        }
        String text = child.getText();
        if (text == null || text.length() == 0) {
            return 0;
        }
        return Integer.parseInt(text.replace("Level: ", ""));
    }

}

