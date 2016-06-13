package TribotAPI.game.combat;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;
import TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Combat07 {

    /**
     * The master index for the combat interface.
     */
    private static final int COMBAT_INTERFACE = 593;

    /**
     * The child indexes for each combat style button.
     */
    private static final int[] COMBAT_STYLE_INTERFACES = {3, 7, 11, 15};

    /**
     * The setting index to get the selected style.
     */
    private static final int SELECTED_STYLE_SETTING = 43;

    /**
     * The child index for the special attack button.
     */
    private static final int SPECIAL_ATTACK_INTERFACE = 30;

    /**
     * The varbit index for the special attack percent value.
     */
    private static final int SPECIAL_ATTACK_PERCENT_SETTING = 300;

    /**
     * The varbit index for checking if special attack is enabled or disabled.
     */
    private static final int SPECIAL_ATTACK_SETTING = 301;

    /**
     * The child index for the auto retaliate button.
     */
    private static final int AUTO_RETALIATE_INTERFACE = 27;

    /**
     * The varbit index to check if auto retaliate is enabled or disabled.
     */
    private static final int AUTO_RETALIATE_SETTING = 172;

    /**
     * Gets the selected combat style index.
     * This value will be from 0-3.
     *
     * @return The selected combat style index.
     */
    public static int getSelectedStyleIndex() {
        return Game.getSetting(SELECTED_STYLE_SETTING);
    }

    /**
     * Selects the combat style with the specified index. The specified index must be 0-3.
     * Takes into account if the style is already selected.
     * Opens the combat tab if it's not open.
     *
     * @param index The index.
     * @return True if the style was selected; false otherwise.
     */
    public static boolean selectCombatStyle(int index) {
        if (index < 0 || index > 3)
            return false;

        if (getSelectedStyleIndex() == index)
            return false;

        if (!GameTab.TABS.COMBAT.isOpen())
            if (GameTab.TABS.COMBAT.open())
                Timing07.waitCondition(GameTab.TABS.COMBAT::isOpen, General.random(1000, 1200));

        RSInterface styleButton = Interfaces.get(COMBAT_INTERFACE, COMBAT_STYLE_INTERFACES[index]);
        if (styleButton != null && Clicking.click(styleButton))
            return Timing07.waitCondition(() -> getSelectedStyleIndex() == index, General.random(1000, 1200));

        return false;
    }

    /**
     * Gets your players special attack percent.
     * This value will always be from 0-100.
     *
     * @return The special attack percent.
     */
    public static int getSpecialAttackPercent() {
        return Game.getSetting(SPECIAL_ATTACK_PERCENT_SETTING) / 10;
    }

    /**
     * Checks to see if the special attack button is selected.
     *
     * @return True if it is selected; false otherwise.
     */
    public static boolean isSpecialAttackSelected() {
        return Game.getSetting(SPECIAL_ATTACK_SETTING) == 1;
    }

    /**
     * Selects the special attack button whether it's selected or not.
     * Opens the combat tab if it's not open.
     *
     * @return True if the button was selected or de-selected; false otherwise.
     */
    public static boolean selectSpecialAttack() {
        if (!GameTab.TABS.COMBAT.isOpen())
            if (GameTab.TABS.COMBAT.open())
                Timing07.waitCondition(GameTab.TABS.COMBAT::isOpen, General.random(1000, 1200));

        boolean selected = isSpecialAttackSelected();
        if (Clicking.click(Interfaces.get(COMBAT_INTERFACE, SPECIAL_ATTACK_INTERFACE)))
            return Timing07.waitCondition(() -> selected != isSpecialAttackSelected(), General.random(1000, 1200));

        return false;
    }

    /**
     * Checks to see if auto retaliate is enabled.
     *
     * @return True if it is enabled; false otherwise.
     */
    public static boolean isAutoRetaliateEnabled() {
        return Game.getSetting(AUTO_RETALIATE_SETTING) == 0;
    }

    /**
     * Selects the auto retaliate button hwther it's selected or not.
     * Opens the combat tab if it's not open.
     *
     * @return True if auto retaliate was selected or de-selected; false otherwise.
     */
    public static boolean selectAutoRetaliate() {
        if (!GameTab.TABS.COMBAT.isOpen())
            if (GameTab.TABS.COMBAT.open())
                Timing07.waitCondition(GameTab.TABS.COMBAT::isOpen, General.random(1000, 1200));

        boolean enabled = isAutoRetaliateEnabled();
        if (Clicking.click(Interfaces.get(COMBAT_INTERFACE, AUTO_RETALIATE_INTERFACE)))
            return Timing07.waitCondition(() -> enabled != isAutoRetaliateEnabled(), General.random(1000, 1200));

        return false;
    }

    /**
     * Checks to see if the specified RSNPC is interacting with your player.
     *
     * @param rsnpc The RSNPC to check.
     * @return True if the specified RSNPC is interacting with your player; false otherwise.
     */
    public static boolean isInCombatWithMe(RSNPC rsnpc) {
        if (rsnpc == null)
            return false;

        RSCharacter interactingCharacter = rsnpc.getInteractingCharacter();
        if (interactingCharacter != null)
            return interactingCharacter.equals(Player.getRSPlayer());

        return false;
    }

    /**
     * Checks if the RSPlayer is in combat.
     *
     * @return True if in combat; false otherwise.
     */
    public static boolean isInCombat() {
        return Combat.getAttackingEntities().length > 0 || Player.getRSPlayer().isInCombat() || Combat.isUnderAttack();
    }

}