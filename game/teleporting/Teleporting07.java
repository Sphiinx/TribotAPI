package scripts.TribotAPI.game.teleporting;


import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api2007.Player;
import org.tribot.api2007.Walking;
import org.tribot.api2007.WebWalking;
import org.tribot.api2007.types.RSObject;
import scripts.TribotAPI.game.objects.Objects07;
import scripts.TribotAPI.game.player.Player07;
import scripts.TribotAPI.game.teleporting.enums.Obelisk;
import scripts.TribotAPI.game.timing.Condition07;
import scripts.TribotAPI.game.timing.Timing07;

/**
 * Created by Sphiinx on 6/29/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Teleporting07 {

    /**
     * The obelisk in which the RSPLayer is using.
     */
    private static RSObject obelisk;

    /**
     * The mule_username model index for the obelisk teleporting animation.
     */
    private static final int obelisk_animation_id = 14825;

    /**
     * Travels to the specified wilderness obelisk. Uses the closest obelisk to the player.
     * Takes into account if you're already at the specified wilderness obelisk.
     *
     * @param end_obelisk The obelisk in which to travel.
     * @return True if successful; false otherwise.
     */
    public static boolean travelToObelisk(Obelisk end_obelisk) {
        if (Player07.getWildernessLevel() == end_obelisk.getWildernessLevel())
            return false;

        obelisk = Objects07.getObject(15, "Obelisk");
        if (obelisk == null) {
            WebWalking.walkTo(Obelisk.getClosestObeliskTile());
        } else {
            if (!obelisk.isOnScreen())
                Walking.walkTo(Obelisk.getClosestObeliskTile());

            if (obelisk.getID() == obelisk_animation_id)
                return false;

            if (Player.getAnimation() != -1)
                return false;

            if (Clicking.click("Activate", obelisk)) {
                if (Timing07.waitCondition(() -> {
                    RSObject object = Objects07.getObject(15, "Obelisk");
                    return object != null && object.getID() == obelisk_animation_id;

                }, General.random(1500, 2000))) {
                    walkToObeliskTile();
                    return Timing07.waitCondition(() -> Player07.getWildernessLevel() <= 30, General.random(6500, 7000));
                }
            }
        }

        return false;
    }

    /**
     * Travels to a teleport safe wilderness obelisk. Uses the closest obelisk to the player.
     * Takes into account if you're already at a teleport safe wilderness obelisk.
     *
     * @return True if successful; false otherwise.
     */
    public static boolean travelToTeleportSafeObelisk() {
        if (Player07.getWildernessLevel() <= 30)
            return false;

        obelisk = Objects07.getObject(15, "Obelisk");
        if (obelisk == null) {
            WebWalking.walkTo(Obelisk.getClosestObeliskTile());
        } else {
            if (!obelisk.isOnScreen())
                Walking.walkTo(Obelisk.getClosestObeliskTile());

            if (obelisk.getID() == obelisk_animation_id)
                return false;

            if (Player.getAnimation() != -1)
                return false;

            if (Clicking.click("Activate", obelisk)) {
                if (Timing07.waitCondition(() -> {
                    RSObject object = Objects07.getObject(15, "Obelisk");
                    return object != null && object.getID() == obelisk_animation_id;

                }, General.random(1500, 2000))) {
                    walkToObeliskTile();
                    return Timing07.waitCondition(() -> Player07.getWildernessLevel() <= 30, General.random(6000, 6500));
                }
            }
        }

        return false;
    }

    /**
     * Walks to the center obelisk cow_pen_tile for the obelisk the player is at.
     */
    private static void walkToObeliskTile() {
        switch (Player07.getWildernessLevel()) {
            case 13:
                Walking.walkTo(Obelisk.OBELISK_13.getTile());
                break;
            case 19:
                Walking.walkTo(Obelisk.OBELISK_19.getTile());
                break;
            case 27:
                Walking.walkTo(Obelisk.OBELISK_27.getTile());
                break;
            case 35:
                Walking.walkTo(Obelisk.OBELISK_35.getTile());
                break;
            case 44:
                Walking.walkTo(Obelisk.OBELISK_44.getTile());
                break;
            case 50:
                Walking.walkTo(Obelisk.OBELISK_50.getTile());
                break;
        }
    }

}

