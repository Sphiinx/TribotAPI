package scripts.API.Game.Inventory;

import org.tribot.api2007.Inventory;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class Inventory07 {

    /**
     * Gets the amount of items in the players inventory.
     * @return How many items are in the players inventory.
     */
    public static int getCount() {
        return Inventory.getAll().length;
    }

    /**
     * Gets the amount of free space in the players inventory.
     * @return How many free spaces is in the players inventory.
     */
    public static int getAmountOfSpace() {
        return 28 - Inventory.getAll().length;
    }

    /**
    * Checks if we have the item specified.
    * @return True if we have the item, false otherwise.
    * @param itemNames The names of the items to check.
    * */
    public static boolean hasItem(String... itemNames) {
        return Inventory.getCount(itemNames) > 0;
    }

    /**
    * Checks if we have the item specified.
    * @return True if we have the item; false otherwise.
    * @param itemIDs The IDs of the items to check.
    * */
    public static boolean hasItem(int... itemIDs) {
        return Inventory.getCount(itemIDs) > 0;
    }

    /**
     * Drops all specified items.
     * @return The amount of items dropped. Takes item stack into account.
     * @param itemNames The names of the items to drop.
     * */
    public static int drop(String... itemNames) {
        return Inventory.drop(itemNames);
    }

    /**
     * Drops all specified itemsIDs.
     * @return The amount of items dropped. Takes item stack into account.
     * @param itemIDs The itemIDs of the items to drop.
     * */
    public static int drop(int... itemIDs) {
        return Inventory.drop(itemIDs);
    }

    /**
     * Drops all items except the items specified.
     * @return The amount of items dropped. Takes item stack into account.
     * @param itemNames The names of the items to keep.
     * */
    public static int dropAllExcept(String... itemNames) {
        return Inventory.dropAllExcept(itemNames);
    }

    /**
     * Drops all items except the itemIDs specified.
     * @return The amount of items dropped. Takes item stack into account.
     * @param itemIDs The itemIDs of the items to keep.
     * */
    public static int dropAllExcept(int... itemIDs) {
        return Inventory.dropAllExcept(itemIDs);
    }

}

