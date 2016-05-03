package api.Game.Chat;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.NPCChat;

/**
 * Created by Sphiinx on 2/15/2016.
 */
public class NPCChat07 {

    /**
     * Clicks the Continue message for an NPC.
     * It will wait 2.5 seconds before it will return false.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean clickContinue() {
        return clickContinue(null);
    }

    /**
     * Clicks the Continue message for an NPC.
     * It will wait 2.5 seconds before it will return false. If the message is null or empty, this method will always click continue.
     *
     * @param message The message that must be present in order for the dialog to be clicked.
     * @return True if successful; false otherwise.
     */
    public static boolean clickContinue(final String message) {
        return Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                return isNPCChatOpen() && (message == null || NPCChat.getMessage().toLowerCase().contains(message.toLowerCase()));
            }
        }, 2500) && NPCChat.clickContinue(true);
    }

    /**
     * Clicks the specified NPC option.
     *
     * @param option The option being to be clicked.
     * @return True if successful; false otherwise.
     */
    public static boolean clickNPCOption(final String option) {
        if (Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                boolean contains = false;
                if (isNPCChatOpen()) {
                    for (String s : NPCChat.getOptions()) {
                        if (s.contains(option)) {
                            contains = true;
                        }
                    }
                } else {
                    contains = true;
                }
                return contains;
            }
        }, 2500)) {
            General.println("Selecting Option");
            return NPCChat.selectOption(option, true);
        } else {
            return false;
        }
    }

    /**
     * Checks to see if the NPC chat is open.
     *
     * @return True if the NPC chat is open; false otherwise.
     */
    public static boolean isNPCChatOpen() {
        return NPCChat.getClickContinueInterface() != null || NPCChat.getSelectOptionInterface() != null || NPCChat.getSelectOptionInterfaces() != null;
    }

}

