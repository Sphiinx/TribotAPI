package scripts.TribotAPI.game.combat;

import org.tribot.api.General;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSCharacter;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSPlayer;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Combat07 {

    /**
     * The mule_username index for the combat interface.
     */
    private static final int COMBAT_INTERFACE = 593;

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
     * Gets the RSPlayers special attack percent.
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
                Timing07.waitCondition(GameTab.TABS.COMBAT::isOpen, General.random(1500, 2000));

        final RSInterface special_attack = Interfaces.get(COMBAT_INTERFACE, SPECIAL_ATTACK_INTERFACE);
        if (special_attack == null)
            return false;

        final boolean selected = isSpecialAttackSelected();
        if (special_attack.click())
            return Timing07.waitCondition(() -> selected != isSpecialAttackSelected(), General.random(1500, 2000));

        return false;
    }

    /**
     * Checks to see if the specified RSPlayer is interacting with your RSPlayer.
     *
     * @param player The RSPlayer to check.
     * @return True if the specified RSPlayer is interacting with your RSPlayer; false otherwise.
     */
    public static boolean isInCombatWithMe(RSPlayer player) {
        if (player == null)
            return false;

        final RSCharacter interactingCharacter = player.getInteractingCharacter();
        if (interactingCharacter != null)
            return interactingCharacter.equals(Player.getRSPlayer());

        return false;
    }

    /**
     * Checks to see if the specified RSNPC is interacting with your RSPlayer.
     *
     * @param rsnpc The RSNPC to check.
     * @return True if the specified RSNPC is interacting with your RSPlayer; false otherwise.
     */
    public static boolean isInCombatWithMe(RSNPC rsnpc) {
        if (rsnpc == null)
            return false;

        final RSCharacter interactingCharacter = rsnpc.getInteractingCharacter();
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
        return Combat.getAttackingEntities().length > 0 || Combat.isUnderAttack();
    }

}