package scripts.tribotapi.game.skills.mining;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import scripts.tribotapi.game.banking.Banking07;
import scripts.tribotapi.game.skills.mining.enums.Pickaxe;

/**
 * Created by Sphiinx on 8/16/2016.
 */
public class Mining07 {

    /**
     * Gets the best usable pickaxe the RSPlayer has.
     *
     * @param is_checking_bank True if we are checking the bank for the best usable pickaxe; false otherwise.
     * @return The best usable pickaxe the RSPlayer has.
     */
    public static Pickaxe getBestUsablePickaxe(boolean is_checking_bank) {
        final Pickaxe[] picks = Pickaxe.values();

        for (int i = picks.length - 1; i >= 0; i--) {
            Pickaxe pick = picks[i];
            if (pick.getMiningLevel() <= Skills.getActualLevel(Skills.SKILLS.MINING) &&
                    ((Inventory.getCount(pick.getItemID()) > 0 || Equipment.getCount(pick.getItemID()) > 0)
                            || (is_checking_bank && Banking07.findItem(pick.getItemID()) != null)))
                return pick;
        }

        return null;
    }

    /**
     * Gets the highest level of pickaxe the RSPlayer can use.
     *
     * @return The highest level pf pickaxe the RSPlayer can use.
     */
    public static Pickaxe currentAppropriatePickaxe() {
        final Pickaxe[] picks = Pickaxe.values();

        for (int i = picks.length - 1; i >= 0; i--) {
            if (picks[i].getMiningLevel() <= Skills.getActualLevel(Skills.SKILLS.MINING))
                return picks[i];
        }

        return Pickaxe.IRON;
    }

}

