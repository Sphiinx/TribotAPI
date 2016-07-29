package scripts.TribotAPI.painting.paint;


import org.tribot.script.Script;
import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;
import scripts.TribotAPI.game.utiity.Utility07;
import scripts.TribotAPI.painting.paint.enums.DataPosition;
import scripts.TribotAPI.painting.paint.paintables.tabs.GeneralTab;
import scripts.TribotAPI.painting.paint.paintables.tabs.StatsTab;
import scripts.TribotAPI.painting.paint.paintables.tabs.TogglePaintTab;
import scripts.TribotAPI.util.Utility;

import java.awt.*;

/**
 * Created by Sphiinx on 6/22/2016.
 */
public class PaintManager {

    /**
     * The master x position for the paint.
     */
    private int x = 8;

    /**
     * The master y position for the paint.
     */
    private int y = 345;

    /**
     * The rendering hints for antialiasing.
     */
    private final RenderingHints ANTIALIASING = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    /**
     * The master array for the paintables.
     */
    private final Paintable[] PAINTABLES;

    /**
     * The master boolean for the show/hide paint.
     */
    private boolean toggle_paint;

    /**
     * The master boolean for the general toggling.
     */
    private boolean show_general = true;

    /**
     * The master boolean for the stats toggling.
     */
    private boolean show_stats = false;

    /**
     * Constructs the paintables.
     */
    public PaintManager(Paintable... paintables) {
        this.PAINTABLES = paintables;
    }

    /**
     * Draws the constructed paintables.
     *
     * @param g Graphics.
     */
    public void drawInteractivePaint(Graphics g) {
        useAntialiasing(g);
        for (Paintable paintable : PAINTABLES) {
            if (!toggle_paint)
                paintable.draw(g);
            else if (paintable.getClass() == TogglePaintTab.class)
                paintable.draw(g);
        }
    }

    /**
     * Sets the rendering hints to use antialiasing.
     *
     * @param g Graphics.
     */
    private void useAntialiasing(Graphics g) {
        Graphics2D g1 = (Graphics2D) g;
        g1.setRenderingHints(ANTIALIASING);
    }

    /**
     * Toggles showing/hiding the paint.
     *
     * @param p The click point.
     * @return True if successful; false otherwise.
     */
    public boolean togglePaint(Point p) {
        for (Paintable paintable : PAINTABLES) {
            if (paintable.getClass() == TogglePaintTab.class) {
                if (paintable.isInClick(p)) {
                    toggle_paint = !toggle_paint;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Toggles between the general/stat tab.
     *
     * @param p The click point.
     * @return True if successful; false otherwise.
     */
    public boolean toggleTab(Point p) {
        for (Paintable paintable : PAINTABLES) {
            if (paintable.isInClick(p)) {
                if (paintable.getClass() == GeneralTab.class) {
                    show_general = true;
                    show_stats = false;
                    return true;
                } else if (paintable.getClass() == StatsTab.class) {
                    show_stats = true;
                    show_general = false;
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks if the paintable tab contains the point.
     *
     * @param p The point.
     * @return True if does contain the point; false otherwise.
     */
    public boolean isInClick(Class c, Point p) {
        for (Paintable paintable : PAINTABLES) {
            if (paintable.getClass() == c) {
                if (paintable.isInClick(p)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Draws the paint info boxes with the specified information in them onto the general tab.
     *
     * @param data_title The info title for the box.
     * @param data       The data for the box.
     * @param pos        The position for the info box.
     * @param g          Graphics.
     */
    public void drawGeneralData(String data_title, String data, DataPosition pos, Graphics g) {
        if (toggle_paint || show_stats)
            return;

        getDataBoxes(data_title, data, pos, g);
    }

    /**
     * Draws the paint info boxes with the specified information in them onto the stats tab.
     *
     * @param script Instance of the script.
     * @param g      Graphics.
     */
    public void drawStatData(Script script, Graphics g) {
        if (toggle_paint || show_general)
            return;

        if (SkillData.ATTACK.getXPGained() > 0 || SkillData.STRENGTH.getXPGained() > 0 || SkillData.DEFENCE.getXPGained() > 0) {
            getDataBoxes("Attack: ", getSkillData(SkillData.ATTACK, script.getRunningTime()), DataPosition.ONE, g);
            getDataBoxes("Strength: ", getSkillData(SkillData.STRENGTH, script.getRunningTime()), DataPosition.TWO, g);
            getDataBoxes("Defence: ", getSkillData(SkillData.DEFENCE, script.getRunningTime()), DataPosition.THREE, g);
            getDataBoxes("Hitpoints: ", getSkillData(SkillData.HITPOINTS, script.getRunningTime()), DataPosition.FOUR, g);
        } else if (SkillData.RANGED.getXPGained() > 0) {
            getDataBoxes("Ranged: ", getSkillData(SkillData.RANGED, script.getRunningTime()), DataPosition.ONE, g);
            getDataBoxes("Hitpoints: ", getSkillData(SkillData.HITPOINTS, script.getRunningTime()), DataPosition.TWO, g);
        } else if (SkillData.MAGIC.getXPGained() > 0) {
            getDataBoxes("Magic: ", getSkillData(SkillData.MAGIC, script.getRunningTime()), DataPosition.ONE, g);
            getDataBoxes("Hitpoints: ", getSkillData(SkillData.DEFENCE, script.getRunningTime()), DataPosition.TWO, g);
        }
    }

    /**
     * Draws the paint info boxes.
     *
     * @param data_title The info title for the box.
     * @param data       The data for the box.
     * @param pos        The position for the info box.
     * @param g          Graphics.
     */
    private void getDataBoxes(String data_title, String data, DataPosition pos, Graphics g) {
        final int DATA_BOX_X_PADDING = 10;
        final int BOX_W = DATA_BOX_X_PADDING + Utility.getTextLength(data_title + data, g);
        g.setColor(Color.black);
        g.drawRect(pos.getX() + x, pos.getY() + y, BOX_W, pos.getHeight());
        g.setColor(Colors.LIGHT_GRAY_COLOR.getCOLOR());
        g.fillRect(pos.getX() + x, pos.getY() + y, BOX_W, pos.getHeight());

        final int DATA_X_PADDING = 5;
        final int DATA_Y_PADDING = 12;
        final int INFO_X = pos.getX() + x + DATA_X_PADDING;
        final int INFO_Y = pos.getY() + y + DATA_Y_PADDING;
        g.setFont(Fonts.PAINT_INFO_FONT.getFont());
        g.setColor(Colors.LIGHT_RED_COLOR.getCOLOR());
        g.drawString(data_title, INFO_X, INFO_Y);
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString(data, INFO_X + Utility.getTextLength(data_title, g), INFO_Y);
    }

    /**
     * Gets the skill data for the specified skill.
     * Format: current_level [0% to next_level] [0 xp | 0 xp/hr | TTL: 00:00:00]
     *
     * @param skill    The skill to get the data for.
     * @param run_time The running time.
     */
    public String getSkillData(SkillData skill, long run_time) {
        skill.update();
        return skill.getActualLevel() + " " +
                "[" + Integer.toString(skill.getPercentToNextLevel()) + "% to " + (skill.getActualLevel() + 1) + "]" + " " +
                "[" + Utility07.formatNumber(skill.getXPGained()) + " xp" + " | " + Calculations.getXPPerHour(skill.getXPGained(), run_time) + " | " + "TTL: " + Calculations.getTimeToNextLevel(skill.getSkill(), skill.getXPGained(), run_time) + "]";
    }

    /**
     * Gets master the x position.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the master y position.
     */
    public int getY() {
        return this.y;
    }

}
