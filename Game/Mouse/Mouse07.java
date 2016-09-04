package scripts.tribotapi.game.mouse;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.api2007.ChooseOption;
import org.tribot.api2007.Game;
import scripts.tribotapi.game.timing.Timing07;

/**
 * Created by Sphiinx on 2/14/2016.
 * Re-written by Sphiinx on 7/8/2016.
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

        if (Timing07.waitCondition(ChooseOption::isOpen, General.random(1500, 2000))) {
            return ChooseOption.select("Cancel");
        }

        return false;
    }

}