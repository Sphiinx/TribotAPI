package scripts.tribotapi.game.objects;

import org.tribot.api.General;
import org.tribot.api.types.generic.Filter;
import org.tribot.api.util.Sorting;
import org.tribot.api2007.Objects;
import org.tribot.api2007.PathFinding;
import org.tribot.api2007.Player;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.*;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Objects07 {

    /**
     * Gets the list of actions for the specified RSObject.
     *
     * @return The list of actions.
     */
    public static String[] getActions(RSObject object) {
        if (object == null)
            return null;

        final RSObjectDefinition def = object.getDefinition();
        if (def == null)
            return null;

        final String[] actions = def.getActions();
        if (actions != null)
            return actions;

        return null;
    }

    /**
     * Checks if the specified RSObject is valid.
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
     * Gets the RSObject at the specified tile. Does not return "null" objects.
     *
     * @param tile The specified tile.
     * @return The object at the specified RSTile; Null if no RSObjects are found.
     */
    public static RSObject getObjectAt(RSTile tile) {
        final RSObject[] objects = Objects.getAt(tile, Filters.Objects.tileEquals(tile));
        if (objects.length <= 0)
            return null;

        for (RSObject object : objects) {
            if (object == null)
                continue;

            final RSObjectDefinition object_definition = object.getDefinition();
            if (object.getDefinition() == null)
                continue;

            final String name = object_definition.getName();
            if (name != null && !name.equals("null"))
                return object;
        }

        return null;
    }

    /**
     * Gets the nearest RSObject with the specified IDs.
     *
     * @param distance The specified distance.
     * @param id       The IDs of the RSObjects.
     * @return The nearest RSObject; Null if no RSObjects are found.
     */
    public static RSObject getObject(int distance, int... id) {
        final RSObject[] object = Objects.find(distance, Filters.Objects.idEquals(id));
        return object.length > 0 ? object[0] : null;
    }

    /**
     * Gets the nearest RSObject with the specified names.
     *
     * @param distance The specified distance.
     * @param name     The names of the RSObjects.
     * @return The nearest RSObject; Null if no RSObjects are found.
     */
    public static RSObject getObject(int distance, String... name) {
        if (name == null)
            return null;

        final RSObject[] object = Objects.find(distance, Filters.Objects.nameEquals(name));
        return object.length > 0 ? object[0] : null;
    }

    /**
     * Gets RSObjects with the specified filter; sortable by the nearest object.
     *
     * @param distance     The specified distance.
     * @param sort_objects True if we should sort the objects in ascending order by the closet object; false otherwise.
     * @param filter       The filter.
     * @return The nearest RSObject; Null if no RSObjects were found.
     */
    public static RSObject getObject(int distance, boolean sort_objects, Filter<RSObject> filter) {
        if (filter == null)
            return null;

        final RSObject[] object = Objects.find(distance, filter);
        if (sort_objects)
            Sorting.sortByDistance(object, Player.getPosition(), true);
        return object.length > 0 ? object[0] : null;
    }

    /**
     * Gets the nearest RSObject with the specified color.
     *
     * @param distance     The specified distance.
     * @param color        The color.
     * @param sort_objects True if we should sort the objects in ascending order by the closet object; false otherwise.
     * @return The nearest RSObject; Null if no RSObjects were found.
     */
    public static RSObject getObjectByColor(int distance, int color, boolean sort_objects) {
        return getObject(distance, sort_objects, new Filter<RSObject>() {
            @Override
            public boolean accept(RSObject object) {
                final RSObjectDefinition object_definition = object.getDefinition();
                if (object_definition == null)
                    return false;

                for (short object_color : object_definition.getModifiedColors()) {
                    if (object_color == color) {
                        return true;
                    }
                }

                return false;
            }
        });
    }

    /**
     * Gets the nearest RSObject object in the area with the specified color.
     *
     * @param distance     The specified distance.
     * @param color        The color.
     * @param sort_objects True if we should sort the objects in ascending order by the closet object; false otherwise.
     * @return The nearest RSObject; Null if no RSObjects were found.
     */
    public static RSObject getObjectByColorInArea(RSTile tile, int distance, int color, boolean sort_objects) {
        return getObject(distance, sort_objects, new Filter<RSObject>() {
            @Override
            public boolean accept(RSObject object) {
                final RSArea radius_area = new RSArea(tile, distance);
                if (!radius_area.contains(object))
                    return false;

                final RSObjectDefinition object_definition = object.getDefinition();
                if (object_definition == null)
                    return false;


                for (short object_color : object_definition.getModifiedColors()) {
                    if (object_color == color) {
                        return true;
                    }
                }

                return false;
            }
        });
    }

}