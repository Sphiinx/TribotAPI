package scripts.TribotAPI.game.grounditems;

import org.tribot.api.Clicking;
import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSItemDefinition;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class GroundItems07 {

    /**
     * Gets the nearest RSGroundItems with the specified ID.
     *
     * @param id The IDs of the RSGroundItems.
     * @return The nearest RSGroundItem; Null if no RSGroundItems are found.
     */
    public static RSGroundItem getGroundItem(int... id) {
        RSGroundItem[] item = GroundItems.find(Filters.GroundItems.idEquals(id));
        Sorting.sortByDistance(item, Player.getPosition(), true);
        return item.length > 0 ? item[0] : null;
    }

    /**
     * Gets the nearest RSGroundItems with the specified names.
     *
     * @param name The Names of the RSGroundItems.
     * @return The nearest RSGroundItem; Null if no RSGroundItems are found.
     */
    public static RSGroundItem getGroundItem(String... name) {
        if (name == null)
            return null;

        RSGroundItem[] item = GroundItems.find(Filters.GroundItems.nameEquals(name));
        Sorting.sortByDistance(item, Player.getPosition(), true);
        return item.length > 0 ? item[0] : null;
    }

    /**
     * Gets the nearest RSGroundItems with the specified filter.
     *
     * @param filter The filter.
     * @return The nearest RSGroundItem; Null if no RSGroundItems were found.
     */
    public static RSGroundItem getGroundItem(Filter<RSGroundItem> filter) {
        if (filter == null)
            return null;

        RSGroundItem[] item = GroundItems.find(filter);
        Sorting.sortByDistance(item, Player.getPosition(), true);
        return item.length > 0 ? item[0] : null;
    }

    /**
     * Pickups the specified RSGroundItems.
     * Takes into account if the players inventory is full unless the item is stack-able.
     *
     * @return True if the RSGroundItem was picked up; false otherwise.
     */
    public static boolean pickUpGroundItem(RSGroundItem... items) {
        if (items == null)
            return false;

        final RSItemDefinition item_definition = items[0].getDefinition();
        if (item_definition == null)
            return false;

        final String name = item_definition.getName();
        if (name == null)
            return false;

        if (Inventory.isFull() && !item_definition.isStackable())
            return false;

        if (!items[0].isOnScreen())
            WebWalking.walkTo(items[0]);
        else
            return Clicking.click("Take", items);

        return false;
    }

}