package scripts.tribotapi.game.stats.enums;

/**
 * Created by Sphiinx on 7/7/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public enum Stat {

    OVERALL(0),
    ATTACK(1),
    DEFENCE(2),
    STRENGTH(3),
    HITPOINTS(4),
    RANGED(5),
    PRAYER(6),
    MAGIC(7),
    COOKING(8),
    WOODCUTTING(9),
    FLETCHING(10),
    FISHING(11),
    FIREMAKING(12),
    CRAFTING(13),
    SMITHING(14),
    MINING(15),
    HERBLORE(16),
    AGILITY(17),
    THIEVING(18),
    SLAYER(19),
    EMPTY_SKILL(20),
    RUNECRAFTING(21),
    HUNTER(22),
    CONSTRUCTION(23);

    private final int SKILL_POSITION;

    Stat(int skill_position) {
        this.SKILL_POSITION = skill_position;
    }

    /**
     * Gets the specified skill position.
     *
     * @return The specified skill position.
     */
    public int getSkillPosition() {
        return SKILL_POSITION;
    }

}
