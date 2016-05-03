package api.Game.Area;

import org.tribot.api2007.Player;
import org.tribot.api2007.Players;
import org.tribot.api2007.ext.Filters;
import org.tribot.api2007.types.RSArea;

/**
 * Created by Sphiinx on 2/16/2016.
 */
public class Area07 {

    /**
     * Gets all the players within a given radius and discludes your own character.
     *
     * @return The amount of players within a radius.
     * */
    public static int getPlayersInArea(int radius) {
        RSArea radiusArea = new RSArea(Player.getPosition(), radius);
        return Players.getAll(Filters.Players.inArea(radiusArea)).length - 1;
    }

}

