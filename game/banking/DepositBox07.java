package scripts.tribotapi.game.banking;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSItemDefinition;
import org.tribot.api2007.types.RSObject;
import scripts.tribotapi.game.interfaces.Interfaces07;
import scripts.tribotapi.game.objects.Objects07;
import scripts.tribotapi.game.timing.Timing07;


/**
 * Created by Sphiinx on 2/16/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class DepositBox07 {

    /**
     * Checks if the RSPlayer is at a deposit box.
     *
     * @return True if the RSPlayer is at a deposit box; false otherwise.
     */
    public static boolean isAtDepositBox() {
        final String[] banks = new String[]{
                "Bank deposit box"
        };

        final RSObject bank = Objects07.getObject(15, banks);
        if (bank == null)
            return false;

        return bank.isOnScreen() && bank.isClickable();
    }

    /**
     * Opens the Bank deposit box; walks to it if it's not on the screen.
     * Takes into account if the Bank deposit box is already open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openDepositBox() {
        if (Banking.isDepositBoxOpen())
            return false;

        final RSObject bank = Objects07.getObject(15, "Bank deposit box");
        if (bank == null)
            return false;

        if (!bank.isOnScreen())
            WebWalking.walkTo(bank);

        return Clicking.click("Deposit", bank);
    }


    /**
     * Deposits the specified name and amount by using the item on the deposit box; walks to it if it's not on the screen.
     * Takes into account if you have the item.
     *
     * @param amount The amount of the RSItem to deposit.
     * @param items   The RSItem to deposit.
     * @return True if successful; false otherwise.
     */
    public static boolean deposit(int amount, RSItem... items) {
        if (items[0] == null)
            return false;

        if (items[0].getDefinition() == null)
            return false;

        final RSItemDefinition item_definition = items[0].getDefinition();
        final String name = item_definition.getName();
        if (name == null)
            return false;

        final RSObject deposit_box = Objects07.getObject(15, "Bank deposit box");
        if (deposit_box == null)
            return false;

        if (!deposit_box.isOnScreen())
            WebWalking.walkTo(deposit_box);

        if (Player.isMoving())
            return false;

        if (!Interfaces07.isSelectOptionOpen()) {
            if (Game.getUptext().equals("Use " + name + " ->")) {
                if (Clicking.click("Use", deposit_box))
                    return Timing07.waitCondition(() -> Interfaces07.isSelectOptionOpen() || Inventory.getCount(item_definition.getID()) <= 0, General.random(1500, 2000));
            } else {
                if (Clicking.click("Use", items))
                    Timing07.waitCondition(() -> Game.getUptext().equals("Use " + name + " ->"), General.random(1500, 2000));
            }
        }

        final String option = getOption(amount);
        if (NPCChat.selectOption(option, true)) {
            if (option.equals("X")) {
                if (Timing07.waitCondition(Interfaces07::isEnterAmountMenuUp, General.random(1500, 2000))) {
                    Keyboard.typeSend("" + amount);
                    return Timing07.waitCondition(() -> Inventory.getCount(item_definition.getID()) <= 0, General.random(1500, 2000));
                }
            } else {
                return Timing07.waitCondition(() -> Inventory.getCount(item_definition.getID()) <= 0, General.random(1500, 2000));
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