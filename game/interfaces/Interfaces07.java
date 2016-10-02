package scripts.tribotapi.game.interfaces;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.types.RSInterface;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Interfaces07 {

    /**
     * Gets all of the RSInterfaces that match the specified text.
     *
     * @param text The text.
     * @return Array of RSInterfaces that match the text.
     */
    public static RSInterface[] find(String text) {
        final Filter<RSInterface> filter = new Filter<RSInterface>() {
            @Override
            public boolean accept(RSInterface rs_interface) {
                return rs_interface != null && rs_interface.getText().contains(text);
            }
        };

        return matches(filter, Interfaces.getAll());
    }

    /**
     * Gets all of the RSInterfaces that match the specified filter.
     *
     * @param filter The filter.
     * @return Array of RSInterfaces that match the filter.
     */
    public static RSInterface[] find(Filter<RSInterface> filter) {
        return matches(filter, Interfaces.getAll());
    }

    /**
     * Gets all the matching interfaces of the filter.
     *
     * @param filter     The filter.
     * @param interfaces The array of interfaces.
     */
    private static RSInterface[] matches(Filter<RSInterface> filter, RSInterface[] interfaces) {
        final ArrayList<RSInterface> matches = new ArrayList<>();
        for (RSInterface i : interfaces) {
            if (i == null)
                continue;

            if (filter.accept(i))
                matches.add(i);

            final RSInterface[] children = i.getChildren();
            if (children != null)
                matches.addAll(Arrays.asList(matches(filter, children)));
        }

        return matches.toArray(new RSInterface[matches.size()]);
    }

    /**
     * Checks to if the chat select option interface is open.
     *
     * @return True if is is; false otherwise.
     */
    public static boolean isSelectOptionOpen() {
        return NPCChat.getSelectOptionInterface() != null;
    }

    /**
     * Checks to if the chat enter amount menu is open.
     *
     * @return True if it is; false otherwise.
     */
    public static boolean isEnterAmountMenuUp() {
        final RSInterface[] interfaces = find("Enter amount:");
        return interfaces.length > 0;
    }

}

