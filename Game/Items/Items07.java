package api.Game.Items;

import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class Items07 {

    /**
     * Gets the list of actions for the specified item.
     *
     * @return The list of actions.
     * */
    public static String[] getActions(RSItem item) {
        if (item != null) {
            RSItemDefinition def = item.getDefinition();
            if (def != null) {
                String[] actions = def.getActions();
                if (actions != null) {
                    return actions;
                }
            }
        }
        return new String[0];
    }

}

