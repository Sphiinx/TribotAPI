package scripts.tribotapi.game.entities;

import org.tribot.api.input.Mouse;
import org.tribot.api2007.types.RSModel;

import java.awt.*;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Entities07 {

    /**
     * Gets the Polygon of the specified model.
     *
     * @param model The model.
     * @return The Polygon.
     */
    public static Polygon getModelArea(RSModel model) {
        if (model == null)
            return new Polygon();

        return model.getEnclosedArea();
    }

    /**
     * Checks if the mouse is hovering the specified model.
     *
     * @param model The model.
     * @return True if the mouse is inside the model; false otherwise.
     */
    public static boolean isHovering(RSModel model) {
        if (model == null)
            return false;

        final Polygon area = getModelArea(model);
        return area.contains(Mouse.getPos());
    }

}