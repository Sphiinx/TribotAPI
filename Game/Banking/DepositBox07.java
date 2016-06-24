package scripts.TribotAPI.game.banking;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.*;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import scripts.TribotAPI.game.interfaces.Interfaces07;
import scripts.TribotAPI.antiban.AntiBan;
import scripts.TribotAPI.game.timing.Timing07;


/**
 * Created by Sphiinx on 2/16/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class DepositBox07 {

    /**
     * Checks if the player is at a deposit box.
     *
     * @return True if the player is at a deposit box; false otherwise.
     */
    public static boolean isAtDepositBox() {
        final String[] banks = new String[]{
                "Bank deposit box"
        };

        RSObject[] bank = Objects.findNearest(15, banks);
        return bank.length > 0 && bank[0].isOnScreen() && bank[0].isClickable();
    }

    /**
     * Opens the Bank deposit box.
     * Takes into account if the Bank deposit box is already open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openDepositBox() {
        RSObject[] bank = Objects.findNearest(10, Filters.Objects.nameEquals("Bank deposit box"));
        if (bank.length <= 0)
            return false;

        if (Banking.isDepositBoxOpen())
            return false;

        if (!bank[0].isOnScreen()) {
            WebWalking.walkTo(bank[0]);
        } else {
            if (Clicking.click("Deposit", bank)) {
                return Timing07.waitCondition(Banking::isDepositBoxOpen, General.random(1000, 1200));
            }
        }

        return false;
    }

    /**
     * Deposits the specified RSItem and amount by using the RSItem on the deposit box.
     * Takes into account if you have the item.
     *
     * @param amount The amount of the RSItem to deposit.
     * @param item   The RSItem to deposit.
     * @return True if successful; false otherwise.
     */
    public static boolean deposit(int amount, RSItem item) {
        if (item == null || Inventory.find(item.getID()).length <= 0)
            return false;

        RSObject[] deposit_box = Objects.findNearest(15, Filters.Objects.nameEquals("Bank deposit box"));
        if (deposit_box.length <= 0)
            return false;

        if (!deposit_box[0].isOnScreen()) {
            WebWalking.walkTo(deposit_box[0]);
        } else {
            if (!Interfaces07.isSelectOptionOpen()) {
                if (Game.getUptext() != null) {
                    if (Game.getUptext().equals("Use Logs ->")) {
                        if (Clicking.click("Use", deposit_box)) {
                            Timing07.waitCondition(Interfaces07::isSelectOptionOpen, General.random(1000, 1200));
                        }
                    } else {
                        if (Clicking.click("Use", item)) {
                            Timing07.waitCondition(() -> Game.getUptext() != null, General.random(1000, 1200));
                        }
                    }
                }
            }

            String option = getOption(amount);
            if (NPCChat.selectOption(option, true)) {
                if (option.equals("X")) {
                    if (Timing07.waitCondition(() -> {
                        AntiBan.waitItemInteractionDelay();
                        return Interfaces07.isEnterAmountMenuUp();
                    }, General.random(1000, 1200))) {
                        Keyboard.typeSend("" + amount);
                        return Timing07.waitCondition(() -> Inventory.getCount(item.getID()) <= 0, General.random(1000, 1200));
                    }
                } else {
                    return Timing07.waitCondition(() -> Inventory.getCount(item.getID()) <= 0, General.random(1000, 1200));
                }
            }
        }

        return false;
    }

    /**
     * Gets the option amount.
     *
     * @param amount The amount being deposited.
     * @return The option for depositing.
     */
    private static String getOption(int amount) {
        String option;
        switch (amount) {
            case 0:
                option = "All";
                break;
            case 1:
                option = "One";
                break;
            case 5:
                option = "Five";
                break;
            default:
                option = "X";
                break;
        }

        return option;
    }

}