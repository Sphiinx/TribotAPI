package TribotAPI.game.banking;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.*;
import TribotAPI.game.inventory.Inventory07;
import TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Banking07 {

    /**
     * The master index for the banking Interface.
     */
    public static final int BANKING_INTERFACE = 12;
    /**
     * The child index for the bank space in the banking Interface.
     */
    public static final int BANK_AMOUNT_INTERFACE = 12;
    /**
     * The child index for the bank space in the banking Interface.
     */
    public static final int BANK_AMOUNT_CHILD_INTERFACE = 5;
    /**
     * The child index for the note button in the banking Interface.
     */
    public static final int NOTE_INTERFACE = 24;
    /**
     * The varbit index for the banking Interface.
     */
    public static final int NOTE_VARBIT = 115;

    /**
     * Deposits all items in the players inventory using the 'Deposit inventory' button.
     *
     * @return The amount of items deposited. Takes item stack into account.
     */
    public static boolean depositAll() {
        return Banking.depositAll() > 0;
    }

    /**
     * Opens the bank in the Grand Exchange.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openGrandExchangeBank() {
        RSNPC[] banker = NPCs.findNearest("Banker");
        if (banker.length <= 0)
            return false;

        if (banker[0].isOnScreen())
            return Clicking.click("Bank Banker", banker[0]);

        return false;
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

        final RSInterfaceChild noteInterface = Interfaces.get(BANKING_INTERFACE, NOTE_INTERFACE);
        return noteInterface != null && noteInterface.click();
    }

    /**
     * Checks if the player is in a bank.
     *
     * @return True if the player is in a bank; false otherwise.
     */
    public static boolean isInBank() {
        final String[] banks = new String[]{
                "Bank booth",
                "Banker",
                "Bank chest",
        };

        RSObject[] bank = Objects.findNearest(15, banks);
        return bank.length > 0 && bank[0].isOnScreen() && bank[0].isClickable();
    }

    /**
     * Gets the amount of space in the players bank.
     *
     * @return The amount of space in the players bank.
     */
    public static int getCurrentBankSpace() {
        RSInterface amount = Interfaces.get(BANK_AMOUNT_INTERFACE, BANK_AMOUNT_CHILD_INTERFACE);
        if (amount == null)
            return -1;

        String text = amount.getText();
        if (text == null)
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
        return getCurrentBankSpace() == Banking.getAll().length;
    }

    /**
     * Checks if any of the given RSItems are equal to the given ID.
     *
     * @param items The items to check the IDs of.
     * @param id    The ID to check the itemIDs of.
     * @return The RSItem with the matching ID.
     */
    private static RSItem getItem(int id, RSItem[] items) {
        if (items.length <= 0)
            return null;

        for (RSItem item : items) {
            if (item.getID() == id) {
                return item;
            }
        }

        return null;
    }

    /**
     * Withdraws the given ID with the given amount.
     * Caches the Bank to make sure it's completely loaded before checking if the given items are in the bank.
     *
     * @param id     The ID in which to withdraw.
     * @param amount The amount in which to withdraw.
     * @return True if the withdraw was successful; false otherwise.
     */
    public static boolean withdrawItem(int id, int amount) {
        RSItem[] itemCache;
        if (getCurrentBankSpace() == (itemCache = Banking.getAll()).length) {
            RSItem itemToWithdraw = getItem(id, itemCache);
            return itemToWithdraw != null && Banking.withdrawItem(itemToWithdraw, amount);
        }

        return false;
    }

    /**
     * Deposits the players entire inventory.
     * Takes into account if there are any items in the players inventory.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean depositInventory() {
        if (Inventory07.getAmountOfSpace() == 28)
            return false;

        if (Banking.depositAll() > 0)
            return Timing07.waitCondition(() -> Inventory07.getAmountOfSpace() == 28, General.random(1000, 1200));

        return false;
    }

    /**
     * Deposits the players entire inventory except the itemID specified.
     * Takes into account if there are any items in the players inventory.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean depositAllExcept(int itemID) {
        if (Inventory07.getAmountOfSpace() == 28)
            return false;

        final RSItem[] INVENTORY_CACHE = Inventory.getAll();
        if (Banking.depositAllExcept(itemID) > 0) {
            final RSItem[] INVENTORY = Inventory.getAll();
            return Timing07.waitCondition(() -> INVENTORY_CACHE != INVENTORY, General.random(1000, 1200));
        }

        return false;
    }

    /**
     * Deposits all equipment using the deposit equipment button.
     * Takes into account if the player is wearing any items.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean depositEquipment() {
        if (Equipment.getItems().length >= 0)
            return false;

        if (Banking.depositEquipment())
            return Timing07.waitCondition(() -> Equipment.getItem(Equipment.SLOTS.WEAPON) == null, General.random(1000, 1200));

        return false;
    }

    /**
     * Opens the bank if you're in a bank and the bank screen isn't open.
     * If you're not in the bank it will walk to one.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openBank() {
        if (!isInBank())
            walkToBank();

        if (Banking.isBankScreenOpen())
            return false;

        if (Banking.openBank()) {
            return Timing07.waitCondition(Banking::isBankScreenOpen, General.random(1000, 1200));
        }

        return false;
    }

    /**
     * Walks to the bank if you're not in one.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean walkToBank() {
        if (Banking07.isInBank())
            return false;

        if (WebWalking.walkToBank()) {
            return Timing07.waitCondition(Banking::isInBank, General.random(1000, 1200));
        }

        return false;
    }

    /**
     * Closes the bank if the bank screen is open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean closeBank() {
        if (!Banking.isBankScreenOpen())
            return false;

        if (Banking.close()) {
            return Timing07.waitCondition(() -> !Banking.isBankScreenOpen(), General.random(1000, 1200));
        }

        return false;
    }
}