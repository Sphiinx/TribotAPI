package scripts.tribotapi.game.mining.enums;

import java.util.Arrays;

/**
 * Created by Sphiinx on 8/16/2016.
 */
public enum Pickaxe {

    BRONZE(1, 1, 1265),
    IRON(1, 1, 1267),
    STEEL(5, 6, 1269),
    BLACK(10, 11, 12297),
    MITHRIL(20, 21, 1273),
    ADAMANT(30, 31, 1271),
    RUNE(40, 41, 1275),
    DRAGON(60, 61, 15259);

    private final int ATTACK_LEVEL;
    private final int MINING_LEVEL;
    private final int ITEM_ID;

    Pickaxe(int attack_level, int mining_level, int item_id) {
        this.ATTACK_LEVEL = attack_level;
        this.MINING_LEVEL = mining_level;
        this.ITEM_ID = item_id;
    }

    public int getAttackLevel() {
        return ATTACK_LEVEL;
    }

    public int getMiningLevel() {
        return MINING_LEVEL;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    /**
     * Gets all of the pickaxe item ID's in the enum.
     *
     * @return An integer array containing all of the pickaxe item ID's in the enum.
     * */
    public static int[] getItemIDs() {
        return Arrays.stream(Pickaxe.values()).mapToInt(Pickaxe::getItemID).toArray();
    }

}

