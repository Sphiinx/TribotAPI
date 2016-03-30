package scripts.API.Game.NPCs;

import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.NPCs;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSNPC;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class NPCs07 {

    /**
     * Gets the nearest RSNPC with the specified id.
     * <p>
     * @param id The ID of the RSNPC.
     * @return The nearest RSNPC. Null if no RSNPC's were found.
     */
    public static RSNPC getNPC(int id) {
        RSNPC[] npcs = NPCs.find(id);
        Sorting.sortByDistance(npcs, Player.getPosition(), true);
        return npcs.length > 0 ? npcs[0] : null;
    }

    /**
     * Gets the nearest RSNPC with the specified name.
     * <p>
     * @param name The name of the RSNPC.
     * @return The nearest RSNPC. Null if no RSNPC's were found.
     */
    public static RSNPC getNPC(String name) {
        RSNPC[] npcs = NPCs.sortByDistance(Player.getPosition(), NPCs.findNearest(name));
        return npcs.length > 0 ? npcs[0] : null;
    }

    /**
     * Gets the nearest RSNPC that is accepted by the specified filter.
     * <p>
     * @param filter The filter.
     * @return The nearest RSNPC. Null if no RSNPC's were found.
     */
    public static RSNPC getNPC(Filter<RSNPC> filter) {
        RSNPC[] npcs = NPCs.sortByDistance(Player.getPosition(), NPCs.findNearest(filter));
        return npcs.length > 0 ? npcs[0] : null;
    }

}

