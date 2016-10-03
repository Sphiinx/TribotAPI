package scripts.tribotapi.game.skills.woodcutting;

import org.tribot.api2007.Equipment;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Skills;
import scripts.tribotapi.game.banking.Banking07;
import scripts.tribotapi.game.skills.woodcutting.enums.Axe;

/**
 * Created by Sphiinx on 8/16/2016.
 */
public class Woodcutting07 {

    /**
     * Gets the best usable axe the RSPlayer has.
     *
     * @param is_checking_bank True if we are checking the bank for the best usable axe; false otherwise.
     * @return The best usable axe the RSPlayer has.
     */
    public static Axe getBestUsableAxe(boolean is_checking_bank) {
        final Axe[] axes = Axe.values();

        for (int i = axes.length - 1; i >= 0; i--) {
            Axe axe = axes[i];
            if (axe.getWoodcuttingLevel() <= Skills.getActualLevel(Skills.SKILLS.MINING) &&
                    ((Inventory.getCount(axe.getItemID()) > 0 || Equipment.getCount(axe.getItemID()) > 0)
                            || (is_checking_bank && Banking07.findItem(axe.getItemID()) != null)))
                return axe;
        }

        return null;
    }

    /**
     * Gets the highest level of axe the RSPlayer can use.
     *
     * @return The highest level pf axe the RSPlayer can use.
     */
    public static Axe currentAppropriateAxe() {
        final Axe[] axes = Axe.values();

        for (int i = axes.length - 1; i >= 0; i--) {
            if (axes[i].getWoodcuttingLevel() <= Skills.getActualLevel(Skills.SKILLS.MINING))
                return axes[i];
        }

        return Axe.IRON;
    }

}

