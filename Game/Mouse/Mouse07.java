package scripts.TribotAPI.game.mouse;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 6/11/2016
 */
public class Mouse07 {

    /**
     * If something is selected it will un-select it.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean fixSelected() {
        if (!Game.isUptext("->"))
            return false;

        if (!ChooseOption.isOpen())
            Mouse.click(3);

        if (Timing07.waitCondition(ChooseOption::isOpen, General.random(1000, 1200))) {
            if (ChooseOption.select("Cancel")) {
                return true;
            }
        }

        return false;
    }

}