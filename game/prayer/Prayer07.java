package scripts.tribotapi.game.prayer;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Skills;
import org.tribot.api2007.types.RSInterface;
import scripts.tribotapi.game.prayer.enums.QuickPrayer;

/**
 * Created by Sphiinx on 7/6/2016.
 * Re-written by Sphiinx on 7/8/2017.
 */
public class Prayer07 {

    /**
     * The master interface for the minimap interface.
     * */
    private static final int MINIMAP_INTERFACE = 160;

    /**
     * The master child interface for the minimap prayer icon interface.
     * */
    private static final int MINIMAP_PRAYER_ICON_CHILD = 16;


    /**
     * Checks if quick prayers are enabled.
     *
     * @return True if quick prayers are enabled; false otherwise.
     * */
    public static boolean isQuickPrayersEnabled() {
        final RSInterface MINIMAP_PRAYER_ICON = Interfaces.get(MINIMAP_INTERFACE, MINIMAP_PRAYER_ICON_CHILD);
        if (MINIMAP_PRAYER_ICON == null)
            return false;

        return MINIMAP_PRAYER_ICON.getTextureID() == QuickPrayer.ENABLED_ICON.getTextureID();
    }

    /**
     * Activates quick prayers if they are disabled.
     * Takes into account if the player has prayer points.
     *
     * @return True if successful; false otherwise.
     * */
    public static boolean activateQuickPrayers(boolean b) {
        if (Skills.SKILLS.PRAYER.getCurrentLevel() <= 0)
            return false;

        final RSInterface MINIMAP_PRAYER_ICON = Interfaces.get(MINIMAP_INTERFACE, MINIMAP_PRAYER_ICON_CHILD);
        if (MINIMAP_PRAYER_ICON == null)
            return false;

        if (b) {
            if (MINIMAP_PRAYER_ICON.getTextureID() == QuickPrayer.ENABLED_ICON.getTextureID())
                return false;

            return MINIMAP_PRAYER_ICON.click("Activate Quick-prayers");
        } else {
            if (MINIMAP_PRAYER_ICON.getTextureID() == QuickPrayer.DISABLED_ICON.getTextureID())
                return false;

            return MINIMAP_PRAYER_ICON.click("Deactivate Quick-prayers");
        }
    }

}

