package api.Game.Entities;

import org.tribot.api.input.Mouse;
import org.tribot.api2007.types.RSModel;

import java.awt.*;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class Entities07 {

    /**
     * Gets the Polygon of the specified model.
     *
     * @param model The model.
     * @return The Polygon.
     */
    public static Polygon getModelArea(final RSModel model) {
        if (model == null) {
            return new Polygon();
        }
        return model.getEnclosedArea();
    }

    /**
     * Checks if the mouse is inside the specified model.
     *
     * @param model The model.
     * @return True if the bot mouse is inside the model; false otherwise.
     */
    public static boolean isHovering(final RSModel model) {
        final Polygon area = getModelArea(model);
        return area != null && area.contains(Mouse.getPos());
    }

}

