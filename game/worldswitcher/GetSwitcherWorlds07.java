package scripts.tribotapi.game.worldswitcher;

import org.tribot.api.General;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import scripts.tribotapi.game.timing.Timing07;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sphiinx on 4/5/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class GetSwitcherWorlds07 {

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
                Timing07.waitCondition(GameTab.TABS.LOGOUT::isOpen, General.random(1500, 2000));

        final RSInterface world_switcher_button = Interfaces.get(LOGOUT_INTERFACE, WORLD_SWITCHER_BUTTON_INTERFACE);
        if (world_switcher_button == null)
            return false;

        return world_switcher_button.click("World Switcher");
    }

    /**
     * Checks if the World Switcher is open.
     *
     * @return True if it's open; false otherwise.
     */
    public static boolean isWorldSwitcherOpen() {
        final RSInterface world_switcher = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE);
        return world_switcher != null;
    }

    /**
     * Gets the all of the worlds for the specified type if the World Switcher is open.
     *
     * @param TEXTURE_ID The texture ID for the specified type of world.
     * @return An Int Array with the worlds for the specified type.
     */
    public static int[] getWorlds(int TEXTURE_ID) {
        final List<Integer> worlds = new ArrayList<>();
        if (!isWorldSwitcherOpen())
            return null;

        final RSInterface world_list = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE);
        if (world_list == null)
            return null;

        final RSInterface[] world_list_children = world_list.getChildren();
        if (world_list_children == null)
            return null;

        for (int i = 2; i < world_list_children.length; i += 6) {
            final RSInterface world = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE).getChild(i);
            if (world == null)
                return null;

            final RSInterface world_texture = Interfaces.get(WORLD_SWITCHER_INTERFACE, WORLDS_INTERFACE).getChild(i - 1);
            if (world_texture == null)
                return null;

            if (world_texture.getTextureID() == TEXTURE_ID) {
                final int world_number = Integer.parseInt(world.getText());
                worlds.add(world_number);
            }
        }

        return worlds.stream().mapToInt(i -> i).toArray();
    }

}