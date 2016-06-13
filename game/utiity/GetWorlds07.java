package TribotAPI.game.utiity;

import org.tribot.api.General;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import TribotAPI.game.timing.Timing07;

import java.util.ArrayList;

/**
 * Created by Sphiinx on 4/5/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class GetWorlds07 {

    /**
     * The master index for the world switcher interface.
     */
    private static final int WORLD_SWITCHER_INTERFACE = 69;

    /**
     * The master index for the worlds interface.
     */
    private static final int WORLDS_INTERFACE = 7;

    /**
     * The master index for the logout interface.
     */
    private static final int LOGOUT_INTERFACE = 182;

    /**
     * The master index for the logout button interface.
     */
    private static final int WORLD_SWITCHER_BUTTON_INTERFACE = 5;

    /**
     * Opens the World Switcher interface.
     * Takes into account if the logout interface is not already open.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean openWorldSwitcher() {
        if (!GameTab.TABS.LOGOUT.isOpen())
            if (GameTab.TABS.LOGOUT.open())
                Timing07.waitCondition(GameTab.TABS.LOGOUT::isOpen, General.random(1000, 1200));

        RSInterface worldSwitcherButton = Interfaces.get(LOGOUT_INTERFACE, WORLD_SWITCHER_BUTTON_INTERFACE);
        if (worldSwitcherButton == null)
            return false;

        if (worldSwitcherButton.click("World Switcher"))
            return Timing07.waitCondition(GetWorlds07::isWorldSwitcherOpen, General.random(1000, 1200));

        return false;
    }

    /**
     * Checks if the World Switcher is open.
     *
     * @return True if it's open; false otherwise.
     */
    public static boolean isWorldSwitcherOpen() {
        RSInterface worldSwitcher = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE);
        return worldSwitcher != null;
    }

    /**
     * Gets the all of the worlds for the specified type if the World Switcher is open.
     *
     * @param TEXTURE_ID The texture ID for the specified type of world.
     * @return An Int Array with the worlds for the specified type.
     */
    public static int[] getWorlds(int TEXTURE_ID) {
        ArrayList<Integer> worlds = new ArrayList<>();
        if (!isWorldSwitcherOpen())
            return null;

        for (int i = 2; i < 419; i += 6) {
            RSInterface world = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE).getChild(i);
            if (world == null)
                return null;

            RSInterface worldTexture = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE).getChild(i - 1);
            if (worldTexture == null)
                return null;

            if (worldTexture.getTextureID() == TEXTURE_ID) {
                int worldNumber = Integer.parseInt(world.getText());
                worlds.add(worldNumber);
            }
        }
        return worlds.stream().mapToInt(i -> i).toArray();
    }

}