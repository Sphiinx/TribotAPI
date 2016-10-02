package scripts.tribotapi.game.clanchat;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Keyboard;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.GameTab;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.types.RSInterface;
import scripts.tribotapi.game.clanchat.enums.ClanRank;
import scripts.tribotapi.game.timing.Timing07;
import scripts.tribotapi.util.Utility;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sphiinx on 7/31/2016.
 */
public class ClanChat07 {

    /**
     * The master interface for the clan chat interface.
     */
    private final static int CLAN_CHAT_INTERFACE = 589;

    /**
     * The master child for the clan chat players interface.
     */
    private final static int CLAN_CHAT_INTERFACE_PLAYERS = 5;

    /**
     * The master child for the clan chat scroll bar interface.
     */
    private final static int CLAN_CHAT_INTERFACE_SCROLLBAR = 6;

    /**
     * The master child for the clan chat title interface,
     */
    private final static int CLAN_CHAT_INTERFACE_TITLE = 0;

    /**
     * The master child interface for the clan chat owner interface.
     */
    private final static int CLAN_CHAT_INTERFACE_OWNER = 1;

    /**
     * The master interface for the clan chat interface.
     */
    private final static int CLAN_CHAT_INTERFACE_JOIN_LEAVE_BUTTON = 8;

    /**
     * The master interface for the chat interface.
     */
    private static final int CHAT_INTERFACE = 162;

    /**
     * The master child for the clan chat, chat join interface.
     */
    private static final int CHAT_INTERFACE_CHANNEL = 33;

    /**
     * The master child for the clan chat, chat last name join interface.
     */
    private static final int CHAT_INTERFACE_CHANNEL_LAST_NAME = 31;

    /**
     * The master component for the clan chat, chat last name join interface.
     */
    private static final int CHAT_INTERFACE_CHANNEL_LAST_NAME_COMPONENT = 0;

    /**
     * Checks if the RSPlayer is in a clan chat.
     *
     * @return True if the RSPlayer is in a clan chat; false otherwise.
     */
    public static boolean isInClanChat() {
        final RSInterface clan_chat_interface_owner = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_OWNER);
        if (clan_chat_interface_owner == null)
            return false;

        return !clan_chat_interface_owner.getText().contains("None");
    }

    /**
     * Joins the specified clan chat. If the clan name is the recent clan, it will click that instead of typing out the name.
     *
     * @param clan_chat_name The name of the clan chat to join.
     * @return True if successful; false otherwise.
     */
    public static boolean join(String clan_chat_name) {
        if (!GameTab.TABS.CLAN.isOpen())
            return false;

        final RSInterface chat_interface_channel_last_name = Interfaces.get(CHAT_INTERFACE, CHAT_INTERFACE_CHANNEL_LAST_NAME);
        if (chat_interface_channel_last_name != null && !chat_interface_channel_last_name.isHidden() && chat_interface_channel_last_name.getChildren() != null) {

            final RSInterface chat_interface_channel_last_name_component = chat_interface_channel_last_name.getChild(CHAT_INTERFACE_CHANNEL_LAST_NAME_COMPONENT);
            if (chat_interface_channel_last_name_component == null)
                return false;

            final String last_name_text = chat_interface_channel_last_name_component.getText();
            if (last_name_text == null)
                return false;

            if (last_name_text.contains(clan_chat_name))
                return Clicking.click(chat_interface_channel_last_name_component);

        }

        final RSInterface chat_interface_channel = Interfaces.get(CHAT_INTERFACE, CHAT_INTERFACE_CHANNEL);
        if (chat_interface_channel != null && !chat_interface_channel.isHidden()) {
            Keyboard.typeSend(clan_chat_name);
            return true;
        } else {
            final RSInterface clan_chat_interface_join_button = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_JOIN_LEAVE_BUTTON);
            if (clan_chat_interface_join_button == null)
                return false;

            if (Clicking.click(clan_chat_interface_join_button))
                Timing07.waitCondition(() -> Interfaces.get(CHAT_INTERFACE, CHAT_INTERFACE_CHANNEL) != null, General.random(2000, 2500));
        }

        return false;
    }

    /**
     * Leaves the clan chat if you're in one.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean leave() {
        if (!GameTab.TABS.CLAN.isOpen())
            return false;

        final RSInterface clan_chat_interface_leave_button = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_JOIN_LEAVE_BUTTON);
        if (clan_chat_interface_leave_button == null)
            return false;

        return Clicking.click(clan_chat_interface_leave_button);
    }

    /**
     * Gets the title of the clan chat you're in.
     *
     * @return The title of the clan chat; null if the RSPlayer is not in a clan chat.
     */
    public static String getTitle() {
        final RSInterface clan_chat_title_interface = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_TITLE);
        if (clan_chat_title_interface == null)
            return null;

        final String clan_chat_title = clan_chat_title_interface.getText();
        if (clan_chat_title == null)
            return null;

        return clan_chat_title.replace("<col=ff9b00>Talking in: <col=ffff64>", "");
    }

    /**
     * Gets the clan chat owners name of the clan chat you're in.
     *
     * @return The name of the clan chat owner; null if the RSPlayer is not in a clan chat.
     */
    public static String getOwner() {
        final RSInterface clan_chat_title_interface = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_OWNER);
        if (clan_chat_title_interface == null)
            return null;

        final String clan_chat_title = clan_chat_title_interface.getText();
        if (clan_chat_title == null)
            return null;

        return clan_chat_title.replace("<col=ff9b00>Owner: <col=ffffff>", "");
    }

    /**
     * Gets all the players in the clan chat.
     *
     * @return A String array of all the players in the clan chat.
     */
    public static String[] getPlayerList() {
        List<String> player_list = new ArrayList<>();

        final RSInterface clan_chat_players_interface = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_PLAYERS);
        if (clan_chat_players_interface == null)
            return null;

        final RSInterface[] clan_chat_players_children_interface = clan_chat_players_interface.getChildren();
        for (int i = 0; i < clan_chat_players_children_interface.length; i += 3) {

            final RSInterface clan_chat_player_name = clan_chat_players_children_interface[i];
            if (clan_chat_player_name == null)
                return null;

            final String player_text = Utility.removeNullSpace(clan_chat_player_name.getText());
            if (player_text == null)
                continue;

            player_list.add(player_text);
        }

        return player_list.stream().toArray(String[]::new);
    }

    /**
     * Checks if the specified player is in the clan chat.
     *
     * @param player_name The player name to check.
     * @return True if the player is in the clan chat; false otherwise.
     */
    public static boolean hasPlayer(String player_name) {
        final String[] player_list = getPlayerList();
        if (player_list == null)
            return false;

        return Arrays.asList(player_list).contains(player_name);
    }

    /**
     * Gets the specified players world.
     *
     * @param player_name The player to get the world of.
     * @return The players world; -1 otherwise.
     */
    public static int getPlayerWorld(String player_name) {
        final RSInterface clan_chat_player_world = getPlayerInterface(player_name, 1);
        if (clan_chat_player_world == null)
            return -1;

        final String player_world_text = clan_chat_player_world.getText().replace("World ", "");
        if (player_world_text == null)
            return -1;

        return Integer.parseInt(player_world_text);
    }

    /**
     * Gets the specified players rank.
     *
     * @param player_name The player name to get the rank of.
     * @return The ClanRank of the player; null if no player was found.
     */
    public static ClanRank getPlayerRank(String player_name) {
        final RSInterface clan_chat_player_rank = getPlayerInterface(player_name, 2);
        if (clan_chat_player_rank == null)
            return null;

        return ClanRank.getRank(clan_chat_player_rank.getTextureID());
    }

    /**
     * Selects the player action on the specified player.
     *
     * @param player_name The name of the player to perform the action on.
     * @param action      The action to perform on the player.
     * @return True if successful; false otherwise.
     */
    public static boolean playerAction(String player_name, String action) {
        if (!GameTab.TABS.CLAN.isOpen())
            return false;

        if (!hasPlayer(player_name))
            return false;

        final RSInterface clan_chat_player = getPlayerInterface(player_name, 0);
        if (clan_chat_player == null)
            return false;

        final Rectangle player_name_rectangle = clan_chat_player.getAbsoluteBounds();
        if (player_name_rectangle == null)
            return false;

        if (player_name_rectangle.y > 410 || player_name_rectangle.y < 268) {
            scrollToPlayerRectangle(player_name_rectangle);
        } else {
            if (clan_chat_player.hover()) {
                if (Timing07.waitCondition(() -> player_name_rectangle.contains(Mouse.getPos().x, Mouse.getPos().y), General.random(2000, 2500))) {
                    Mouse.click(3);
                    return Timing.waitChooseOption(action + " " + player_name, General.random(2000, 2500));
                }
            }
        }

        return false;
    }

    /**
     * Scrolls to the player rectangle in the clan chat.
     *
     * @param player_rectangle The rectangle to scroll to.
     */
    private static void scrollToPlayerRectangle(Rectangle player_rectangle) {
        final RSInterface clan_chat_interface_scrollbar = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_SCROLLBAR);
        if (clan_chat_interface_scrollbar == null)
            return;

        final RSInterface clan_chat_interface_scrollbar_component = clan_chat_interface_scrollbar.getChild(1);
        if (clan_chat_interface_scrollbar_component == null)
            return;

        final Rectangle scrollbar_rectangle = clan_chat_interface_scrollbar_component.getAbsoluteBounds();
        if (scrollbar_rectangle == null)
            return;

        if (!scrollbar_rectangle.contains(Mouse.getPos().x, Mouse.getPos().y)) {
            if (clan_chat_interface_scrollbar_component.hover())
                Timing07.waitCondition(() -> scrollbar_rectangle.contains(Mouse.getPos().x, Mouse.getPos().y), General.random(2000, 2500));
        } else {
            if (player_rectangle.y > 410)
                Mouse.scroll(false);
            if (player_rectangle.y < 268)
                Mouse.scroll(true);
        }
    }

    /**
     * Gets the interface for the specified player
     *
     * @param player_name The player to get the interface for.
     * @param component   The component to get.
     * @return The RSInterface with the specified component.
     */
    private static RSInterface getPlayerInterface(String player_name, int component) {
        final RSInterface clan_chat_players_interface = Interfaces.get(CLAN_CHAT_INTERFACE, CLAN_CHAT_INTERFACE_PLAYERS);
        if (clan_chat_players_interface == null)
            return null;

        final RSInterface[] clan_chat_players_children_interface = clan_chat_players_interface.getChildren();
        if (clan_chat_players_children_interface == null)
            return null;

        for (int i = 0; i < clan_chat_players_children_interface.length; i += 3) {
            final RSInterface clan_chat_player_name_interface = clan_chat_players_children_interface[i];
            if (clan_chat_player_name_interface == null)
                return null;

            final String player_interface_text = Utility.removeNullSpace(clan_chat_player_name_interface.getText());
            if (player_interface_text == null)
                continue;

            if (!player_interface_text.equals(player_name))
                continue;

            final RSInterface clan_chat_player_interface = clan_chat_players_children_interface[i + component];
            if (clan_chat_player_interface == null)
                return null;

            return clan_chat_player_interface;
        }

        return null;
    }

}

