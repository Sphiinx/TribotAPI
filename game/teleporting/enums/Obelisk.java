package scripts.TribotAPI.game.teleporting.enums;

import org.tribot.api.util.Sorting;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSTile;

/**
 * Created by Sphiinx on 6/29/2016.
 * Re-written by Sphiinx on 7/28/2016.
 */
public enum Obelisk {

    OBELISK_13(new RSTile(3156, 3620, 0), 13),
    OBELISK_19(new RSTile(3227, 3667, 0), 19),
    OBELISK_27(new RSTile(3035, 3732, 0), 27),
    OBELISK_35(new RSTile(3106, 3794, 0), 35),
    OBELISK_44(new RSTile(2980, 3866, 0), 44),
    OBELISK_50(new RSTile(3307, 3916, 0), 50);

    private final RSTile TILE;
    private final int WILDERNESS_LEVEL;

    Obelisk(RSTile tile, int wilderness_level) {
        this.TILE = tile;
        this.WILDERNESS_LEVEL = wilderness_level;
    }

    public RSTile getTile() {
        return TILE;
    }

    public int getWildernessLevel() {
        return WILDERNESS_LEVEL;
    }

    public static RSTile getClosestObeliskTile() {
        final RSTile[] OBELISKS = new RSTile[] {
                OBELISK_13.getTile(),
                OBELISK_19.getTile(),
                OBELISK_27.getTile(),
                OBELISK_35.getTile(),
                OBELISK_44.getTile(),
                OBELISK_50.getTile()
        };
        Sorting.sortByDistance(OBELISKS, Player.getPosition(), true);
        return OBELISKS[0];
    }

}
