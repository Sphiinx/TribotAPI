package scripts.API.Game.Mouse;

import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class Mouse07 {

    /**
     * If something is selected it will unselect it.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean fixSelected() {
        if (Game.isUptext("->")) {
            if (!ChooseOption.isOpen()) {
                Mouse.click(3);
            }
            if (Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return ChooseOption.isOpen();
                }
            }, 250)) {
                if (ChooseOption.select("Cancel")) {
                    System.out.println("Fixed selected mouse.");
                    return true;
                }
            }
        }
        return true;
    }

}


