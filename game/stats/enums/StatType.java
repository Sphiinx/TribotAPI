package scripts.tribotapi.game.stats.enums;

/**
 * Created by Sphiinx on 7/7/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public enum StatType {

    RANK(0),
    LEVEL(1),
    XP(2);

    private final int SKILL_TYPE;

    StatType(int skill_type) {
        this.SKILL_TYPE = skill_type;
    }

    /**
     * Gets the specified skill type.
     *
     * @return The specified skill type.
     */
    public int getSkillPosition() {
        return SKILL_TYPE;
    }

}
