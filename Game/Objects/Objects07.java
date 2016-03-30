package scripts.API.Game.Objects;

import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSObject;
import org.tribot.api2007.types.RSObjectDefinition;
import scripts.SPXAIOMiner.antiban.AntiBan;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class Objects07 {

    /**
     * Gets the list of actions for the specified object.
     *
     * @return The list of actions.
     * */
    public static String[] getActions(RSObject object) {
        if (object != null) {
            RSObjectDefinition def = object.getDefinition();
            if (def != null) {
                String[] actions = def.getActions();
                if (actions != null) {
                    return actions;
                }
            }
        }
        return new String[0];
    }

    /**
     * Checks if the specified object is valid.
     *
     * @param object The object to be checked.
     * @param reach  If you can reach the object or not.
     * @return True if the object is valid; false otherwise.
     */
    public static boolean isValid(RSObject object, boolean reach) {
        if (reach) {
            if (!PathFinding.canReach(object, true)) {
                return false;
            }
        }
        return object != null && object.isOnScreen() && object.isClickable();
    }

    /**
     * Gets the nearest object with the specified ID.
     *
     * @param id The ID of the object.
     * @param distance The specified distance.
     * @return The nearest object; Null if no objects are found.
     */
    public static RSObject getObject(final int id, final int distance) {
        RSObject[] objs = Objects.find(distance, id);
        Sorting.sortByDistance(objs, Player.getPosition(), true);
        return AntiBan.selectNextTarget(objs);
    }

    /**
     * Gets the nearest object with the specified name.
     *
     * @param name The Name of the object.
     * @param distance The specified distance.
     * @return The nearest object; Null if no objects are found.
     */
    public static RSObject getObject(String name, int distance) {
        if (name != null) {
            RSObject[] objs = Objects.find(distance, name);
            Sorting.sortByDistance(objs, Player.getPosition(), true);
            return objs.length > 0 ? objs[0] : null;
        }
        return null;
    }

    /**
     * Gets the nearest object by the specified filter.
     *
     * @param filter The filter.
     * @param distance The specified distance.
     * @return The nearest object; Null if no objects were found.
     */
    public static RSObject getObject(Filter<RSObject> filter, int distance) {
        if (filter != null) {
            RSObject[] objs = Objects.find(distance, filter);
            Sorting.sortByDistance(objs, Player.getPosition(), true);
            return objs.length > 0 ? objs[0] : null;
        }
        return null;
    }

}

