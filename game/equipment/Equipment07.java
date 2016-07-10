package scripts.TribotAPI.game.equipment;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Equipment;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;

/**
 * Created by Sphiinx on 6/28/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Equipment07 {

    /**
     * Finds the specified RSItems in the RSPlayers equipment.
     *
     * @param id The IDs of the RSItem.
     * @return The RSItem; Null if no RSItems are found.
     */
    public static RSItem getItem(int... id) {
        final RSItem[] items = Equipment.find(Filters.Items.idEquals(id));
        return items.length > 0 ? items[0] : null;
    }

    /**
     * Finds the specified RSItems in the RSPlayers equipment.
     *
     * @param name The name of the RSItems.
     * @return The RSItem; Null if no RSItems are found.
     */
    public static RSItem getItem(String... name) {
        if (name == null)
            return null;

        final RSItem[] items = Equipment.find(Filters.Items.nameEquals(name));
        return items.length > 0 ? items[0] : null;
    }

    /**
     * Finds the specified RSItems in the RSPlayers equipment.
     *
     * @param filter The filter.
     * @return The RSItem; Null if no RSItems were found.
     */
    public static RSItem getItem(Filter<RSItem> filter) {
        if (filter == null)
            return null;

        final RSItem[] items = Equipment.find(filter);
        return items.length > 0 ? items[0] : null;
    }

}

