package scripts.TribotAPI.painting.projection;

import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import scripts.TribotAPI.color.Colors;
import scripts.TribotAPI.font.Fonts;

import java.awt.*;

/**
 * Created by Sphiinx on 7/7/2016.
 */
public class DuelArena07 {

    /**
     * The master interface for the duel arena interface;
     */
    private final int DUEL_INTERFACE = 107;

    /**
     * The master child interface for the duel arena challenger name interface;
     */
    private final int DUEL_INTERFACE_CHALLGENGER_NAME_CHILD = 99;

    /**
     * The master x position for the duel stats background.
     */
    private int duel_stats_background_x = 363;

    /**
     * The master y position for the duel stats background.
     */
    private int duel_stats_background_y = 345;

    /**
     * The master width for the duel stats background.
     */
    private final int DUEL_STATS_BACKGROUND_W = 150;

    /**
     * The master height for the duel stats background.
     */
    private final int DUEL_STATS_BACKGROUND_H = 125;

    /**
     * The master y position for the attack text and levels.
     * */
    private final int ATTACK_TEXT_Y = duel_stats_background_y + 45;

    /**
     * The master y position for the strength text and levels.
     * */
    private final int STRENGTH_TEXT_Y = duel_stats_background_y + 60;

    /**
     * The master y position for the defence text and levels.
     * */
    private final int DEFENCE_TEXT_Y = duel_stats_background_y + 75;

    /**
     * The master y position for the hitpoints text and levels.
     * */
    private final int HITPOINTS_TEXT_Y = duel_stats_background_y + 90;

    /**
     * The master y position for the winrate text and %.
     * */
    private final int WINRATE_TEXT_Y = duel_stats_background_y + 105;

    /**
     * The master y position for the x text and int.
     * */
    private final int STAKE_X_TEXT_Y = duel_stats_background_y + 120;

    /**
     * The master x position for the players level text buffer.
     * */
    private final int PLAYER_LEVEL_TEXT_X = duel_stats_background_x + 80;

    /**
     * The master x position for the challengers level text buffer.
     * */
    private final int CHALLENGER_LEVEL_TEXT_X = duel_stats_background_x + 120;

    /**
     * The master y position for the challenger title.
     * */
    private final int CHALLENGER_TITLE_Y = duel_stats_background_y + 12;

    /**
     * The master x position for the level text.
     * */
    private final int STAT_TEXT_X = duel_stats_background_x + 4;

    /**
     * The master x position for the YOU  THEM text.
     * */
    private final int YOU_THEM_X = duel_stats_background_x + 75;

    /**
     * The master y position for the YOU  THEM text.
     * */
    private final int YOU_THEM_Y = duel_stats_background_y + 30;

    /**
     * Draws the paint background, title, and version and sets the paint to use anti aliasing.
     *
     * @param g Graphics.
     */
    public void drawGeneralData(Graphics g) {
        drawBackground(g);
        drawChallengerName(g);

    }

    /**
     * Draws the duel arena info background.
     * Takes into account if the duel arena duel screen is open.
     *
     * @param g Graphics.
     * */
    public void drawBackground(Graphics g) {
        RSInterface duel_interface = Interfaces.get(DUEL_INTERFACE);
        if (duel_interface == null)
            return;

        g.setFont(Fonts.PAINT_INFO_FONT.getFont());
        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.fillRect(duel_stats_background_x, duel_stats_background_y, DUEL_STATS_BACKGROUND_W, DUEL_STATS_BACKGROUND_H);

    }

    /**
     * Draws the duel arena challenger name.
     * Takes into account if the duel arena duel screen is open.
     *
     * @param g Graphics.
     * */
    public void drawChallengerName(Graphics g) {
        RSInterface duel_challenger_name_interface = Interfaces.get(DUEL_INTERFACE, DUEL_INTERFACE_CHALLGENGER_NAME_CHILD);
        if (duel_challenger_name_interface == null)
            return;

        final int DUAL_BOX_TITLE_X = getInfoBoxXCenter(DUEL_STATS_BACKGROUND_W, getTextLength(duel_challenger_name_interface.getText(), g), duel_stats_background_x);
        g.setColor(Color.ORANGE);
        g.drawString(duel_challenger_name_interface.getText(), DUAL_BOX_TITLE_X, CHALLENGER_TITLE_Y);
        g.drawString("You  Them", YOU_THEM_X, YOU_THEM_Y);
    }

    /**
     * Draws yours and the challengers stats when in the duel challenge screen.
     * Takes into account if the duel arena duel screen is open.
     *
     * @param g Graphics.
     */
    public void drawStats(int our_attack_level, int our_strength_level, int our_defence_level, int our_hitpoints_level, int their_attack_level, int their_strength_level, int their_defence_level, int their_hitpoints_level, Graphics g) {
        RSInterface duel_interface = Interfaces.get(DUEL_INTERFACE);
        if (duel_interface == null)
            return;

        double win_rate = Math.floor(getWinRate(our_attack_level, our_strength_level, our_defence_level, our_hitpoints_level, their_attack_level, their_strength_level, their_defence_level, their_hitpoints_level) * 100) / 100;
        double x_rate = Math.floor(((50 - win_rate) / 2.5 + 2) * 100) / 100;
        g.setColor(Colors.WHITE_COLOR.getCOLOR());
        g.drawString("Attack: ", STAT_TEXT_X, ATTACK_TEXT_Y);
        g.drawString("Strength: ", STAT_TEXT_X, STRENGTH_TEXT_Y);
        g.drawString("Defence: ", STAT_TEXT_X, DEFENCE_TEXT_Y);
        g.drawString("Hitpoints: ", STAT_TEXT_X, HITPOINTS_TEXT_Y);
        g.drawString("Winrate: " + Double.toString(win_rate) + "%", STAT_TEXT_X, WINRATE_TEXT_Y);
        g.drawString("Stake X: " + Double.toString(x_rate), STAT_TEXT_X, STAKE_X_TEXT_Y);
        drawLevels(our_attack_level, their_attack_level, ATTACK_TEXT_Y, g);
        drawLevels(our_strength_level, their_strength_level, STRENGTH_TEXT_Y, g);
        drawLevels(our_defence_level, their_defence_level, DEFENCE_TEXT_Y, g);
        drawLevels(our_hitpoints_level, their_hitpoints_level, HITPOINTS_TEXT_Y, g);
    }

    /**
     * Draws the players and challengers levels with the colors corresponding to the greater.
     *
     * @param first_level The first players level to draw.
     * @param second_level The second players level to draw.
     * @param y_offset The offsert for each level.
     * @param g Graphics.
     * */
    private void drawLevels(int first_level, int second_level, int y_offset, Graphics g) {
        if (first_level > second_level) {
            g.setColor(Color.GREEN);
            g.drawString(Integer.toString(first_level), PLAYER_LEVEL_TEXT_X, y_offset);
            g.setColor(Color.RED);
            g.drawString(Integer.toString(second_level), CHALLENGER_LEVEL_TEXT_X, y_offset);
        } else if (first_level < second_level) {
            g.setColor(Color.RED);
            g.drawString(Integer.toString(first_level), PLAYER_LEVEL_TEXT_X, y_offset);
            g.setColor(Color.GREEN);
            g.drawString(Integer.toString(second_level), CHALLENGER_LEVEL_TEXT_X, y_offset);
        } else {
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(first_level), PLAYER_LEVEL_TEXT_X, y_offset);
            g.drawString(Integer.toString(second_level), CHALLENGER_LEVEL_TEXT_X, y_offset);
        }
    }

    private double getWinRate(int our_attack_level, int our_strength_level, int our_defence_level, int our_hitpoints_level, int their_attack_level, int their_strength_level, int their_defence_level, int their_hitpoints_level) {
        double attack_difference = our_attack_level - their_attack_level;
        double defence_difference = our_defence_level - their_defence_level;
        double attack_difference_plus_defence_difference = attack_difference + defence_difference;
        double division_off_attack_plus_defence = attack_difference_plus_defence_difference / 3;
        double hitpoints_difference = our_hitpoints_level - their_hitpoints_level;
        double division_plus_hitpoints = division_off_attack_plus_defence + hitpoints_difference;
        double max_hit_difference = getMaxHit(our_strength_level) - getMaxHit(their_strength_level);
        double max_hit_multiplication = max_hit_difference * 2.5;
        return division_plus_hitpoints + max_hit_multiplication + 50;
    }

    private int getMaxHit(int strength_level) {
        if (strength_level >= 96) {
            return 25;
        } else if (strength_level >= 92 && strength_level <= 95) {
            return 24;
        } else if (strength_level >= 87 && strength_level <= 91) {
            return 23;
        } else if (strength_level >= 83 && strength_level <= 86) {
            return 22;
        } else if (strength_level >= 79 && strength_level <= 82) {
            return 21;
        } else if (strength_level >= 75 && strength_level <= 78) {
            return 20;
        } else if (strength_level >= 66 && strength_level <= 69) {
            return 19;
        } else {
            return 18;
        }
    }

    /**
     * Gets the center x position of the box width.
     *
     * @param boxWidth    The width of the box.
     * @param objectWidth The width of the object to be centered.
     * @param boxX        The x position of the box.
     * @return The center x position of the box width.
     */
    private int getInfoBoxXCenter(int boxWidth, int objectWidth, int boxX) {
        return (boxWidth - objectWidth) / 2 + boxX;
    }

    /**
     * Gets the pixel width of the specified text.
     *
     * @param text The text to get the width of.
     * @param g    Graphics.
     */
    private int getTextLength(String text, Graphics g) {
        return g.getFontMetrics().stringWidth(text);
    }

}

