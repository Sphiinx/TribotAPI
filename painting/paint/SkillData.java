package scripts.tribotapi.painting.paint;

import org.tribot.api2007.Skills;

/**
 * Created by Sphiinx on 6/23/2016.
 */
public enum SkillData {

    ATTACK(Skills.SKILLS.ATTACK),
    STRENGTH(Skills.SKILLS.STRENGTH),
    DEFENCE(Skills.SKILLS.DEFENCE),
    RANGED(Skills.SKILLS.RANGED),
    PRAYER(Skills.SKILLS.PRAYER),
    MAGIC(Skills.SKILLS.MAGIC),
    RUNECRAFTING(Skills.SKILLS.RUNECRAFTING),
    CONSTRUCTION(Skills.SKILLS.CONSTRUCTION),
    HITPOINTS(Skills.SKILLS.HITPOINTS),
    AGILITY(Skills.SKILLS.AGILITY),
    HERBLORE(Skills.SKILLS.HERBLORE),
    THIEVING(Skills.SKILLS.THIEVING),
    CRAFTING(Skills.SKILLS.CRAFTING),
    FLETCHING(Skills.SKILLS.FLETCHING),
    SLAYER(Skills.SKILLS.SLAYER),
    HUNTER(Skills.SKILLS.HUNTER),
    MINING(Skills.SKILLS.MINING),
    SMITHING(Skills.SKILLS.SMITHING),
    FISHING(Skills.SKILLS.FISHING),
    COOKING(Skills.SKILLS.COOKING),
    FIREMAKING(Skills.SKILLS.FIREMAKING),
    WOODCUTTING(Skills.SKILLS.WOODCUTTING),
    FARMING(Skills.SKILLS.FARMING);

    private final Skills.SKILLS SKILL;

    private int START_EXP;
    private int START_LEVEL;
    private int exp;
    private int level;
    private int exp_to_next_level;
    private int percent_to_next_level;

    SkillData(Skills.SKILLS skill) {
        this.SKILL = skill;
        this.START_EXP = 0;
        this.START_LEVEL = 0;
    }

    /**
     * Initialises the data of the skill.
     * */
    private void init() {
        this.START_EXP = Skills.getXP(SKILL);
        this.START_LEVEL = Skills.getActualLevel(SKILL);
    }

    /**
     * Resets the data of the skill.
     * */
    public void reset() {
        init();
        this.exp = 0;
        this.exp_to_next_level = 0;
        this.percent_to_next_level = 0;
    }

    /**
     * Gets the gained XP of the skill.
     *
     * @return The gained XP of the skill.
     * */
    public int getXPGained() {
        return this.exp;
    }

    /**
     * Gets the gained levels of the skill.
     *
     * @return The gained levels of the skill.
     * */
    public int getLevelsGained() {
        return this.level;
    }

    /**
     * Gets the experience to the next level of the skill.
     *
     * @return The experience to the next level;.
     * */
    public int getExperienceToNextLevel() {
        return this.exp_to_next_level;
    }

    /**
     * Gets the percent to the next level of the skill.
     *
     * @return The percent to the next level.
     * */
    public int getPercentToNextLevel() {
        return this.percent_to_next_level;
    }

    /**
     * Gets the actual level of the skill.
     *
     * @return The actual level of the skill.
     * */
    public int getActualLevel() {
        return SKILL.getActualLevel();
    }

    /**
     * Gets the current skill.
     *
     * @return The current skill.
     * */
    public Skills.SKILLS getSkill() {
        return this.SKILL;
    }

    /**
     * Initialises all of the skills.
     * */
    public static void initialiseAll() {
        for (SkillData x : values())
            x.init();
    }

    /**
     * Updates the data of the skill.
     * */
    public void update () {
        this.exp = SKILL.getXP() - this.START_EXP;
        this.level = SKILL.getActualLevel() - this.START_LEVEL;
        this.exp_to_next_level = Skills.getXPToNextLevel(SKILL);
        this.percent_to_next_level = Skills.getPercentToNextLevel(SKILL);
    }

    /**
     * Updates the data of all the skills.
     * */
    public static void updateAll() {
        for (SkillData x : values())
            x.update();
    }

    /**
     * Gets the total experience gained across all the skills.
     *
     * @return The total experience gained across all the skills.
     * */
    public static int getTotalExperienceGained() {
        int total = 0;
        for (SkillData x : values())
            total += x.getXPGained();

        return total;
    }

    /**
     * Gets the total levels gained across all the skills.
     *
     * @return The total levels gained across all the skills.
     * */
    public static int getTotalLevelsGained() {
        int total = 0;
        for (SkillData x : values())
            total += x.getLevelsGained();

        return total;
    }

}
