package api.Game.Combat;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class Combat07 {

    /**
     * The child indexes for each style button.
     */
    private static final int[] COMBAT_STYLE_INDEXES = {3, 7, 11, 15};

    /**
     * The master index for the combat interface.
     */
    private static final int COMBAT_MASTER_INDEX = 593;

    /**
     * The setting index to get the selected style.
     */
    private static final int SELECTED_STYLE_SETTING_INDEX = 43;

    /**
     * The child index for the special attack button.
     */
    private static final int SPECIAL_ATTACK_INDEX = 30;

    /**
     * The setting index for the special attack percent value.
     */
    private static final int SPECIAL_ATTACK_PERCENT_SETTING_INDEX = 300;

    /**
     * The setting index for checking if special attack is enabled or disabled.
     */
    private static final int SPECIAL_ATTACK_SETTING_INDEX = 301;

    /**
     * The child index for the auto retaliate button.
     */
    private static final int AUTO_RETALIATE_INDEX = 27;

    /**
     * The setting index to check if auto retaliate is enabled or disabled.
     */
    private static final int AUTO_RETALIATE_SETTING_INDEX = 172;

    /**
     * Gets the selected combat style index.
     * This value will be 0-3.
     *
     * @return The selected combat style index.
     */
    public static int getSelectedStyleIndex() {
        return Game.getSetting(SELECTED_STYLE_SETTING_INDEX);
    }

    /**
     * Selects the combat style with the specified index.
     * The specified index must be 0-3.
     *
     * @param index The index.
     * @return True if the style was selected; false otherwise.
     */
    public static boolean selectCombatStyle(final int index) {
        if (index < 0 || index > 3) {
            throw new IllegalArgumentException("Index must be between 0 and 3.");
        }
        if (getSelectedStyleIndex() == index) {
            return true;
        }
        if (GameTab.TABS.COMBAT.open()) {
            RSInterface styleButton = Interfaces.get(COMBAT_MASTER_INDEX, COMBAT_STYLE_INDEXES[index]);
            if (styleButton != null && Clicking.click(styleButton)) {
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return getSelectedStyleIndex() == index;
                    }
                }, 2000);
            }
        }
        return false;
    }

    /**
     * Gets your players special attack percent.
     * This value will always be from 0 to 100 inclusive.
     *
     * @return The special attack percent.
     */
    public static int getSpecialAttackPercent() {
        return Game.getSetting(SPECIAL_ATTACK_PERCENT_SETTING_INDEX) / 10;
    }

    /**
     * Checks to see if the special attack option is selected.
     *
     * @return True if it is selected; false otherwise.
     */
    public static boolean isSpecialAttackSelected() {
        return Game.getSetting(SPECIAL_ATTACK_SETTING_INDEX) == 1;
    }

    /**
     * Selects the special attack option.
     * This method will select the option regardless of the current selection state.
     *
     * @return True if the option was selected or de-selected; false otherwise.
     */
    public static boolean selectSpecialAttack() {
        if (GameTab.TABS.COMBAT.open()) {
            boolean selected = isSpecialAttackSelected();
            if (Clicking.click(Interfaces.get(COMBAT_MASTER_INDEX, SPECIAL_ATTACK_INDEX))) {
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return selected != isSpecialAttackSelected();
                    }
                }, 2000);
            }
        }
        return false;
    }

    /**
     * Checks to see if auto retaliate is enabled.
     *
     * @return True if it is enabled; false otherwise.
     */
    public static boolean isAutoRetaliateEnabled() {
        return Game.getSetting(AUTO_RETALIATE_SETTING_INDEX) == 0;
    }

    /**
     * Selects the auto retaliate button.
     * This method will select the option regardless of the current selection state.
     *
     * @return True if auto retaliate was selected or de-selected; false otherwise.
     */
    public static boolean selectAutoRetaliate() {
        if (GameTab.TABS.COMBAT.open()) {
            boolean enabled = isAutoRetaliateEnabled();
            if (Clicking.click(Interfaces.get(COMBAT_MASTER_INDEX, AUTO_RETALIATE_INDEX))) {
                return Timing.waitCondition(new Condition() {
                    @Override
                    public boolean active() {
                        General.sleep(100);
                        return enabled != isAutoRetaliateEnabled();
                    }
                }, 2000);
            }
        }
        return false;
    }

    /**
     * Checks to see if the specified RSNPC is interacting with your player.
     *
     * @param rsnpc The RSNPC to check.
     * @return True if the specified RSNPC is interacting with your player; false otherwise.
     */
    public static boolean isInCombatWithMe(RSNPC rsnpc) {
        if (rsnpc != null) {
            RSCharacter interactingCharacter = rsnpc.getInteractingCharacter();
            if (interactingCharacter != null) {
                return interactingCharacter.equals(Player.getRSPlayer());
            }
        }
        return false;
    }

    /**
     * Checks if the player is in combat.
     *
     * @return True if in combat; false otherwise.
     * */
    public static boolean isInCombat() {
        return Combat.getAttackingEntities().length > 0 || Player.getRSPlayer().isInCombat() || Combat.isUnderAttack();
    }

}

