package scripts.TribotAPI.game.banking;

import org.tribot.api.Clicking;
import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.*;
import scripts.TribotAPI.game.npcs.NPCs07;
import scripts.TribotAPI.game.objects.Objects07;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Banking07 {

    /**
     * The master index for the banking Interface.
     */
    private static final int BANKING_INTERFACE = 12;

    /**
     * The child index for the bank space in the banking Interface.
     */
    private static final int BANK_AMOUNT_CHILD_INTERFACE = 5;

    /**
     * The child index for the note button in the banking Interface.
     */
    private static final int NOTE_INTERFACE = 24;

    /**
     * The varbit index for the banking Interface.
     */
    private static final int NOTE_VARBIT = 115;

    /**
     * Opens the Grand Exchange bank if it's not open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openGrandExchangeBank() {
        if (Banking07.isBankItemsLoaded())
            return false;

        final RSNPC banker = NPCs07.getNPC("Banker");
        if (banker == null)
            return false;

        if (banker.isOnScreen())
            return Clicking.click("Bank Banker", banker);

        return false;
    }

    /**
     * Checks if the RSPlayer is in a bank.
     *
     * @return True if the RSPlayer is in a bank; false otherwise.
     */
    public static boolean isInBank() {
        final String[] banks = new String[]{
                "Bank booth",
                "Banker",
                "Bank chest",
        };

        final RSObject bank = Objects07.getObject(15, banks);
        if (bank == null)
            return false;

        return bank.isOnScreen() && bank.isClickable();
    }

    /**
     * Checks if the note option in the banking screen is selected.
     *
     * @return True if note is selected; false otherwise.
     */
    public static boolean isNotedSelected() {
        return Game.getSetting(NOTE_VARBIT) == 1;
    }

    /**
     * Turns item noting on in the banking screen.
     * Takes into account if the bank screen is open.
     *
     * @return True if the note interface was selected; false otherwise;
     */
    public static boolean selectNote() {
        if (!Banking.isBankScreenOpen())
            return false;

        if (Interfaces.get(BANKING_INTERFACE) == null)
            return false;

        final RSInterfaceChild note_interface = Interfaces.get(BANKING_INTERFACE, NOTE_INTERFACE);
        if (note_interface == null)
            return false;

        return note_interface.click();
    }

    /**
     * Gets the amount of space in the RSPlayers bank.
     *
     * @return The amount of space in the RSPlayers bank.
     */
    public static int getCurrentUsedBankSpace() {
        final RSInterface amount = Interfaces.get(BANKING_INTERFACE, BANK_AMOUNT_CHILD_INTERFACE);
        if (amount == null)
            return -1;

        String text = amount.getText();
        if (text.length() <= 0)
            return -1;

        int parse = Integer.parseInt(text);
        if (parse > 0)
            return parse;

        return -1;
    }

    /**
     * Checks if the bank is loaded.
     *
     * @return True if the bank is loaded; false otherwise.
     */
    public static boolean isBankItemsLoaded() {
        return getCurrentUsedBankSpace() == Banking.getAll().length;
    }

    /**
     * Finds the specified RSItems in the RSPlayers bank.
     * Takes into account if the bank is loaded.
     *
     * @param id The IDs of the RSItems.
     * @return The RSItem; Null if no RSItems are found.
     */
    public static RSItem findItem(int... id) {
        if (!Banking07.isBankItemsLoaded())
            return null;

        final RSItem[] items = Banking.find(Filters.Items.idEquals(id));
        return items.length > 0 ? items[0] : null;
    }

    /**
     * Finds the specified RSItems in the RSPlayers bank.
     * Takes into account if the bank is loaded.
     *
     * @param name The name of the RSItems.
     * @return The RSItem; Null if no RSItems are found.
     */
    public static RSItem findItem(String... name) {
        if (name == null)
            return null;

        if (!isBankItemsLoaded())
            return null;

        final RSItem[] items = Banking.find(Filters.Items.nameEquals(name));
        return items.length > 0 ? items[0] : null;
    }

    /**
     * Finds the specified RSItems in the RSPlayers bank.
     * Takes into account if the bank is loaded.
     *
     * @param filter The filter.
     * @return The RSItem; Null if no RSItems were found.
     */
    public static RSItem findItem(Filter<RSItem> filter) {
        if (filter == null)
            return null;

        if (!isBankItemsLoaded())
            return null;

        final RSItem[] items = Banking.find(filter);
        return items.length > 0 ? items[0] : null;
    }

    /**
     * Withdraws the given names with the given amount.
     * Takes into account if the bank is loaded.
     *
     * @param names  The names in which to withdraw.
     * @param amount The amount in which to withdraw.
     * @return True if successful; false otherwise.
     */
    public static boolean withdrawItem(int amount, String... names) {
        if (!isBankItemsLoaded())
            return false;

        final RSItem itemToWithdraw = findItem(names);
        if (itemToWithdraw == null)
            return false;

        return Banking.withdrawItem(itemToWithdraw, amount);
    }

    /**
     * Withdraws the given IDs with the given amount.
     * Takes into account if the bank is loaded.
     *
     * @param ids    The IDs in which to withdraw.
     * @param amount The amount in which to withdraw.
     * @return True if successful; false otherwise.
     */
    public static boolean withdrawItem(int amount, int... ids) {
        if (!isBankItemsLoaded())
            return false;

        final RSItem itemToWithdraw = findItem(ids);
        if (itemToWithdraw == null)
            return false;

        return Banking.withdrawItem(itemToWithdraw, amount);
    }

}