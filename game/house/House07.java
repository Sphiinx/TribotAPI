package scripts.TribotAPI.game.house;

import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;

/**
 * Created by Sphiinx on 7/3/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class House07 {

    /**
     * The mule_username index for the game options interface.
     */
    private static final int GAME_OPTIONS_INTERFACE = 261;

    /**
     * The mule_username child index for the game options house options button interface.
     */
    private static final int HOUSE_OPTIONS_BUTTON_CHILD = 68;

    /**
     * The mule_username index for the house options interface.
     */
    private static final int HOUSE_OPTIONS_INTERFACE = 370;

    /**
     * The mule_username child index for the house options expel guests interface.
     */
    private static final int EXPEL_GUESTS_CHILD = 12;

    /**
     * The mule_username child index for the house options leave house interface.
     */
    private static final int LEAVE_HOUSE_CHILD = 13;

    /**
     * The mule_username child index for the house options call servant interface.
     */
    private static final int CALL_SERVANT_CHILD = 14;

    /**
     * Opens the house settings options if they aren't open
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openHouseSettings() {
        final RSInterface house_options = Interfaces.get(HOUSE_OPTIONS_INTERFACE);
        if (house_options != null)
            return false;

        if (!GameTab.TABS.OPTIONS.isOpen())
            GameTab.TABS.OPTIONS.open();

        final RSInterface house_options_button = Interfaces.get(GAME_OPTIONS_INTERFACE, HOUSE_OPTIONS_BUTTON_CHILD);
        if (house_options_button == null)
            return false;

        return house_options_button.click();
    }

    /**
     * Expels the guests if the house settings options are open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean expelGuests() {
        final RSInterface house_options = Interfaces.get(HOUSE_OPTIONS_INTERFACE);
        if (house_options == null)
            return false;

        final RSInterface expel_guests = Interfaces.get(HOUSE_OPTIONS_INTERFACE, EXPEL_GUESTS_CHILD);
        if (expel_guests == null)
            return false;

        return expel_guests.click();
    }

    /**
     * Leaves the house if the house settings options are open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean leaveHouse() {
        final RSInterface house_options = Interfaces.get(HOUSE_OPTIONS_INTERFACE);
        if (house_options == null)
            return false;

        final RSInterface leave_house = Interfaces.get(HOUSE_OPTIONS_INTERFACE, LEAVE_HOUSE_CHILD);
        if (leave_house == null)
            return false;

        return leave_house.click();
    }

    /**
     * Calls the servant if the house settings options are open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean callServent() {
        RSInterface house_options = Interfaces.get(HOUSE_OPTIONS_INTERFACE);
        if (house_options == null)
            return false;

        RSInterface call_servent = Interfaces.get(HOUSE_OPTIONS_INTERFACE, CALL_SERVANT_CHILD);
        if (call_servent == null)
            return false;

        return call_servent.click();
    }

}

