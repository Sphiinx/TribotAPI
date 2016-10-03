package scripts.tribotapi.game.skills.woodcutting.enums;

import java.util.Arrays;

/**
 * Created by Sphiinx on 8/16/2016.
 */
public enum Axe {

    BRONZE(1, 1, 1351),
    IRON(1, 1, 1349),
    STEEL(5, 6, 1353),
    BLACK(10, 11, 1361),
    MITHRIL(20, 21, 1355),
    ADAMANT(30, 31, 1357),
    RUNE(40, 41, 1359),
    DRAGON(60, 61, 6739);

    private final int ATTACK_LEVEL;
    private final int WOODCUTTING_LEVEL;
    private final int ITEM_ID;

    Axe(int attack_level, int woodcutting_level, int item_id) {
        this.ATTACK_LEVEL = attack_level;
        this.WOODCUTTING_LEVEL = woodcutting_level;
        this.ITEM_ID = item_id;
    }

    public int getAttackLevel() {
        return ATTACK_LEVEL;
    }

    public int getWoodcuttingLevel() {
        return WOODCUTTING_LEVEL;
    }

    public int getItemID() {
        return ITEM_ID;
    }

    /**
     * Gets all of the axe item ID's in the enum.
     *
     * @return An integer array containing all of the pickaxe item ID's in the enum.
     * */
    public static int[] getItemIDs() {
        return Arrays.stream(Axe.values()).mapToInt(Axe::getItemID).toArray();
    }

}

