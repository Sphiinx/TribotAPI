package TribotAPI.game.player;

import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.*;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Player07 {

    /**
     * The master index for the combat interface.
     */
    private static final int COMBAT_INTERFACE = 593;

    /**
     * The child index for each combat level text.
     */
    private static final int COMBAT_INTERFACE_TEXT_INTERFACE = 2;

    /**
     * The setting index to get the poisoned value.
     */
    private static final int POISONED_VALUE_SETTING = 102;

    /**
     * The master index for the wilderness interface.
     */
    private static final int WILDERNESS_INTERFACE = 90;

    /**
     * The child index for each combat wilderness level.
     */
    private static final int WILDERNESS_LEVEL_INTERFACE = 26;

    /**
     * Checks if the RSPlayer is poisoned.
     *
     * @return True if poisoned; false otherwise.
     */
    public static boolean isPoisoned() {
        return Game.getSetting(POISONED_VALUE_SETTING) > 0;
    }

    /**
     * Gets the RSPlayers combat level.
     *
     * @return The RSPlayers combat level.
     */
    public static int getCombatLevel() {
        RSInterface level = Interfaces.get(COMBAT_INTERFACE, COMBAT_INTERFACE_TEXT_INTERFACE);
        if (level == null)
            return -1;

        String text = level.getText();
        if (text == null)
            return -1;

        text = text.replace("Combat Lvl: ", "");
        int parse = Integer.parseInt(text);
        if (parse > 0)
            return parse;

        return -1;
    }

    /**
     * Gets the RSPlayers wilderness level.
     *
     * @return The RSPlayers wilderness level.
     */
    public static int getLevel() {
        RSInterface wilderness = Interfaces.get(WILDERNESS_INTERFACE);
        if (wilderness == null)
            return -1;

        RSInterfaceChild child = Interfaces.get(WILDERNESS_INTERFACE, WILDERNESS_LEVEL_INTERFACE);
        if (child == null) {
            return -1;
        }
        String text = child.getText();
        if (text == null || text.length() == 0) {
            return -1;
        }
        return Integer.parseInt(text.replace("Level: ", ""));
    }

    /**
     * Gets the nearest RSPlayer with the specified name.
     *
     * @param name The name of the RSPlayer.
     * @return The nearest RSPlayer. Null if no RSPlayer's were found.
     */
    public static RSPlayer getRSPlayer(String name) {
        if (name == null)
            return null;

        RSPlayer[] player = Players.find(Filters.Players.nameEquals(name));
        Sorting.sortByDistance(player, Player.getPosition(), true);
        return player.length > 0 ? player[0] : null;
    }

    /**
     * Gets the nearest RSPlayer that is accepted with the specified filter.
     *
     * @param filter The filter.
     * @return The nearest RSplayer. Null if no RSPlayers's were found.
     */
    public static RSPlayer getRSPlayer(Filter<RSPlayer> filter) {
        if (filter == null)
            return null;

        RSPlayer[] player = Players.find(filter);
        Sorting.sortByDistance(player, Player.getPosition(), true);
        return player.length > 0 ? player[0] : null;
    }

}