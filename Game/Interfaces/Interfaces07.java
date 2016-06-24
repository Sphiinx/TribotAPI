package scripts.TribotAPI.game.interfaces;

import org.tribot.api.types.generic.Filter;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.types.RSInterface;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sphiinx on 1/10/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Interfaces07 {

    /**
     * Gets all of the RSInterfaces that match the specified filter.
     *
     * @param filter The filter.
     * @Return Array of RSInterfaces that match the filter.
     */
    public static RSInterface[] find(Filter<RSInterface> filter) {
        return matches(filter, Interfaces.getAll());
    }

    private static RSInterface[] matches(Filter<RSInterface> filter, RSInterface[] interfaces) {
        ArrayList<RSInterface> matches = new ArrayList<>();

        for (RSInterface i : interfaces) {
            if (i != null) {
                if (filter.accept(i)) {
                    matches.add(i);
                }

                RSInterface[] children = i.getChildren();
                if (children != null) {
                    matches.addAll(Arrays.asList(matches(filter, children)));
                }
            }
        }

        return matches.toArray(new RSInterface[matches.size()]);
    }

    /**
     * Checks to if the select option interface is open.
     *
     * @return True if is is; false otherwise.
     */
    public static boolean isSelectOptionOpen() {
        return NPCChat.getSelectOptionInterface() != null;
    }

    /**
     * Checks to if the enter amount menu is open.
     *
     * @return True if it is; false otherwise.
     */
    public static boolean isEnterAmountMenuUp() {
        RSInterface[] interfaces = find(new Filter<RSInterface>() {
            @Override
            public boolean accept(RSInterface rsInterface) {
                return rsInterface.getText().equals("Enter amount:");
            }
        });

        return interfaces.length > 0;
    }

}

