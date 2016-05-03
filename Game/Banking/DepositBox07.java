package api.Game.Banking;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.Objects;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSObject;
import scripts.SPXAIOMiner.api.game.interfaces.Interfaces07;
import scripts.SPXAIOMiner.api.game.objects.Objects07;
import scripts.SPXAIOMiner.api.Waiting;
import scripts.SPXAIOMiner.antiban.AntiBan;


/**
 * Created by Sphiinx on 2/16/2016.
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
        RSObject[] bank = Objects.findNearest(15, banks);
        return bank.length > 0 && bank[0].isOnScreen() && bank[0].isClickable();
    }

    /**
     * Opens the Bank deposit box if there is one on screen the deposit box and the deposit box screen isn't open.
     * If you are not in the bank it will walk to one.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openDepositBox() {
        RSObject[] bank = Objects.findNearest(10, "Bank deposit box");
        if (bank.length > 0 && bank[0].isOnScreen()) {
            if (Clicking.click("Deposit", bank)) {
                Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return Banking.isDepositBoxOpen();
                    }
                }, General.random(750, 1000));
            }
        }
        return false;
    }

    /**
     * Deposits the specified item and amount.
     *
     * @param amount The amount of the item to deposit.
     * @param item The item to deposit.
     * @return True if successful; false otherwise.
     */
    public static final boolean deposit(final int amount, final RSItem item) {
        if (item == null || Inventory.find(item.getID()).length <= 0) {
            return false;
        }
        final int startCount = Inventory.getCount(item.getID());
        RSObject deposit_box = Objects07.getObject("Bank deposit box", 15);
        if (deposit_box != null && deposit_box.isOnScreen()) {
            boolean used = false;
            if (!Interfaces07.isSelectOptionUp()) {
                if (Clicking.click("Use", item)) {
                    if (Clicking.click("Use", deposit_box) && Timing.waitCrosshair(75) == 2) {
                        used = true;
                        if (Inventory.getCount(item.getID()) > 1) {
                            Waiting.waitMoveCondition(new Condition() {
                                @Override
                                public boolean active() {
                                    return Interfaces07.isSelectOptionUp();
                                }
                            }, 2000);
                        }
                    }
                }
            }
            if (Inventory.getCount(item.getID()) == 1 && used) {
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        return Inventory.find(item.getID()).length <= 0;
                    }
                }, 3000);
            }
            String op = getOption(amount);
            if (NPCChat.selectOption(op, true)) {
                if (op.equals("X")) {
                    if (Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            AntiBan.waitItemInteractionDelay();
                            return Interfaces07.isEnterAmountMenuUp();
                        }
                    }, 3000)) {
                        Keyboard.typeSend("" + amount);
                    }
                }
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        AntiBan.waitItemInteractionDelay();
                        return amount == 0 ? Inventory.find(item.getID()).length <= 0: Inventory.getCount(item.getID()) + amount == startCount;
                    }
                }, 3000);
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
        String op;
        if (amount == 0) {
            op = "All";
        } else if (amount == 5) {
            op = "Five";
        } else if (amount == 1) {
            op = "One";
        } else {
            op = "X";
        }
        return op;
    }

}

