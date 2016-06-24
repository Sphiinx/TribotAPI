package scripts.TribotAPI.game.grounditems;

import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSGroundItem;
import org.tribot.api2007.types.RSItemDefinition;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class GroundItems07 {

    /**
     * Gets the nearest RSGroundItem with the specified ID.
     *
     * @param id The ID of the RSGroundItem.
     * @return The nearest RSGroundItem; Null if no RSGroundItems are found.
     */
    public static RSGroundItem getGroundItem(int id) {
        RSGroundItem[] item = GroundItems.find(Filters.GroundItems.idEquals(id));
        Sorting.sortByDistance(item, Player.getPosition(), true);
        return item.length > 0 ? item[0] : null;
    }

    /**
     * Gets the nearest RSGroundItem with the specified name.
     *
     * @param name     The Name of the RSGroundItem.
     * @return The nearest RSGroundItem; Null if no RSGroundItems are found.
     */
    public static RSGroundItem getGroundItem(String name) {
        if (name == null)
            return null;

        RSGroundItem[] item = GroundItems.find(Filters.GroundItems.nameEquals(name));
        Sorting.sortByDistance(item, Player.getPosition(), true);
        return item.length > 0 ? item[0] : null;
    }

    /**
     * Gets the nearest RSGroundItem with the specified filter.
     *
     * @param filter   The filter.
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
     * Pickups the specified RSGroundItem.
     * Takes into account if the players inventory is full unless the item is stack-able.
     *
     * @return True if the RSGroundItem was picked up; false otherwise.
     */
    public static boolean pickUpGroundItem(RSGroundItem item) {
        if (item == null)
            return false;

        final RSItemDefinition definition = item.getDefinition();
        if (definition == null)
            return false;

        String name = definition.getName();
        if (name == null)
            return false;

        if (Inventory.isFull() && !definition.isStackable())
            return false;

        if (item.isOnScreen()) {
            if (item.click("Take " + name)) {
                return Timing07.waitCondition(() -> Inventory.getCount(item.getID()) > 66, General.random(1000, 1200));
            }
        } else {
            WebWalking.walkTo(item);
        }

        return false;
    }

}