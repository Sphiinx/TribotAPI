package TribotAPI.game.items;

import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Items07 {

    /**
     * Gets the list of actions for the specified RSItem.
     *
     * @return The list of actions.
     */
    public static String[] getActions(RSItem item) {
        if (item == null)
            return null;

        RSItemDefinition definition = item.getDefinition();
        if (definition == null)
            return null;

        String[] actions = definition.getActions();
        if (actions != null)
            return actions;

        return null;
    }

}