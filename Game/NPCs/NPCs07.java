package scripts.TribotAPI.game.npcs;

import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSNPC;
import org.tribot.api2007.types.RSNPCDefinition;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class NPCs07 {

    /**
     * Gets the list of actions for the specified RSNPC.
     *
     * @return The list of actions.
     */
    public static String[] getActions(RSNPC npc) {
        if (npc == null)
            return null;

        RSNPCDefinition def = npc.getDefinition();
        if (def == null)
            return null;

        String[] actions = def.getActions();
        if (actions != null)
            return actions;

        return null;
    }

    /**
     * Gets the nearest RSNPC with the specified id.
     *
     * @param npcID The ID of the RSNPC.
     * @return The nearest RSNPC. Null if no RSNPCs were found.
     */
    public static RSNPC getNPC(int npcID) {
        RSNPC[] npcs = NPCs.find(Filters.NPCs.idEquals(npcID));
        Sorting.sortByDistance(npcs, Player.getPosition(), true);
        return npcs.length > 0 ? npcs[0] : null;
    }

    /**
     * Gets the nearest RSNPC with the specified name.
     *
     * @param name The name of the RSNPC.
     * @return The nearest RSNPC. Null if no RSNPCs were found.
     */
    public static RSNPC getNPC(String name) {
        if (name == null)
            return null;

        RSNPC[] npcs = NPCs.find(Filters.NPCs.nameEquals(name));
        Sorting.sortByDistance(npcs, Player.getPosition(), true);
        return npcs.length > 0 ? npcs[0] : null;
    }

    /**
     * Gets the nearest RSNPC that is accepted with the specified filter.
     *
     * @param filter The filter.
     * @return The nearest RSNPC. Null if no RSNPCs were found.
     */
    public static RSNPC getNPC(Filter<RSNPC> filter) {
        if (filter == null)
            return null;

        RSNPC[] npcs = NPCs.find(filter);
        Sorting.sortByDistance(npcs, Player.getPosition(), true);
        return npcs.length > 0 ? npcs[0] : null;
    }

}