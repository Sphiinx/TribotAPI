package api.Game.WorldHopper;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.input.Mouse;
import org.tribot.api.types.generic.Condition;
import org.tribot.api2007.*;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSItem;
import scripts.SPXAIOMiner.api.game.utiity.Utility07;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class WorldHopper07 {

    private static final HashMap<Integer, Integer> CACHE = new HashMap<>();
    private static final int WORLD_SWITCH_COLOR = -4351943;
    private static final int WORLD_RED_ARROW_COLOR = -5501948;
    private static final Rectangle WORLD_SELECT_AREA = new Rectangle(9, 466, 92, 28);

    private static final Point START_WORLD_POINT = new Point(199, 49);  //may need to change these if world menu updates
    private static final int WORLD_ROWS = 18, WORLD_COLUMNS = 4;        //may need to change these if world menu updates
    private static final int WORLD_END = 94;                            //may need to change these if world menu updates

    private static final int[] WORLDS_DO_NOT_EXIST = {7, 15, 23, 24, 31, 32, 39, 40, 47, 48, 55, 56, 63, 64, 71, 72, 79, 80, 87, 88, 89, 90, 91, 92};
    private static final Dimension WORLD_DIMENSION = new Dimension(88, 19);
    private static final int LEFT_RIGHT_WORLD_GAP = 5, TOP_DOWN_WORLD_GAP = 5;

    private static final int[] DMM_WORLDS = {45, 52, 57};

    private static boolean arrayContains(final int[] array, final int key) {
        return !(Arrays.binarySearch(array, key) < 0);
    }

    public static Rectangle getWorldClickArea(final int world) {
        if (arrayContains(WORLDS_DO_NOT_EXIST, world)) return null;

        int worldNum = -1, diff = 0, worldColumn = -1, worldRow = -1;
        for (int i = 1; i <= WORLD_END; i++) {
            if (arrayContains(WORLDS_DO_NOT_EXIST, i)) diff++;
            if (i == world) {
                worldNum = i - diff;  //finds the world sequentially skipping all the worldType that d.n.e.
                break;
            }
        }
        for (int i = 1; i <= WORLD_COLUMNS; i++) {
            if (worldNum <= i * WORLD_ROWS) {
                worldColumn = i;
                worldRow = WORLD_ROWS - (worldColumn * WORLD_ROWS - worldNum);
                break;
            }
        }

        return new Rectangle(START_WORLD_POINT.x + (worldColumn - 1) * (WORLD_DIMENSION.width + LEFT_RIGHT_WORLD_GAP),
                START_WORLD_POINT.y + (worldRow - 1) * (WORLD_DIMENSION.height + TOP_DOWN_WORLD_GAP),
                WORLD_DIMENSION.width, WORLD_DIMENSION.height);
    }

    public static boolean isAtWorldHopScreen() {
        return Screen.getGameImage().getRGB(11, 0) == WORLD_SWITCH_COLOR;
    }

    private static boolean hasMisconfiguredWorldSettings() {
        return Screen.getGameImage().getRGB(301, 9) == WORLD_RED_ARROW_COLOR;
    }

    private static void selectWorld(final int world) {
        final Rectangle worldRect = getWorldClickArea(world);
        if (worldRect == null) return;

        long timeout = Timing.currentTimeMillis();
        while (!worldRect.contains(Mouse.getPos()) && Timing.timeFromMark(timeout) < 5000) {
            Mouse.move(Utility07.getRandomPoint(worldRect));
            General.sleep(10, 30);
        }
        if (worldRect.contains(Mouse.getPos())) {
            General.sleep(50, 80);
            Mouse.click(1);
        }
    }

    private static boolean openWorldHopScreen() {
        Mouse.clickBox(WORLD_SELECT_AREA, 1);
        return Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                return isAtWorldHopScreen();
            }
        }, 5000);
    }

    private static boolean configureWorldSettings() {
        final Rectangle rectangle = new Rectangle(301, 9, 4, 4);
        final RSItem tmp = new RSItem(0, 0, 0, RSItem.TYPE.OTHER);
        tmp.setArea(rectangle);
        return Clicking.click(tmp) && Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                return !hasMisconfiguredWorldSettings();
            }
        }, 2000);
    }

    public static boolean switchWorld(int world) {
        if (Interfaces.isInterfaceValid(378)) {
            RSInterfaceChild child = Interfaces.get(378, 17);
            if (child != null) {
                if (Clicking.click(child)) {
                    Timing.waitCondition(new Condition() {
                        @Override
                        public boolean active() {
                            General.sleep(100, 300);
                            return !Interfaces.isInterfaceValid(378);
                        }
                    }, General.random(2000, 3000));
                }
            }
        }

        if (WorldHopper.getWorld() == world) {
            if (isInWorldSelect() && Login.getLoginState() == Login.STATE.LOGINSCREEN) {
                clickRectangle(new Rectangle(710, 7, 45, 9), 16, 3);    //cancel button
            }
            return true;
        }

        if (Login.getLoginState() == Login.STATE.LOGINSCREEN) {
            General.println("Login Screen Hopping to world: " + world);
            if (!isAtWorldHopScreen()) {
                openWorldHopScreen();
            } else if (hasMisconfiguredWorldSettings()) {
                configureWorldSettings();
            } else {
                selectWorld(world);
            }
            return WorldHopper.getWorld() == world;
        }

        General.println("Ingame Hopping to world: " + world);
        if (!GameTab.open(GameTab.TABS.LOGOUT) || !openWorldSwitchInterface() || !moveMouseInsideWorldSwitchInterface()) {
            return false;
        }

        RSInterface rsInterface = getWorldInterfaceChild(world);
        return rsInterface != null && scrollToWorldInterface(rsInterface) && worldSwitch(rsInterface, world) && waitUntilWorld(world);
    }

    private static boolean openWorldSwitchInterface() {
        if (Interfaces.isInterfaceValid(182)) {
            RSInterfaceChild child = Interfaces.get(182, 5);
            if (child == null || !child.click() || !Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return Interfaces.isInterfaceValid(69);
                }
            }, General.random(1200, 2200))) {
                return false;
            }
        }
        return Interfaces.isInterfaceValid(69);
    }

    private static boolean moveMouseInsideWorldSwitchInterface() {
        RSInterfaceChild worldSelectBox = Interfaces.get(69, 4);
        Rectangle bounds = null;
        if (worldSelectBox != null) {
            bounds = worldSelectBox.getAbsoluteBounds();
            if (bounds != null && !bounds.contains(Mouse.getPos())) {
                Mouse.moveBox(bounds);
            }
        }
        return bounds != null && bounds.contains(Mouse.getPos());
    }

    private static RSInterface getWorldInterfaceChild(int world) {
        if (CACHE.containsKey(world)) {
            RSInterfaceChild child = Interfaces.get(69, 7);
            RSInterfaceComponent[] components;
            if (child != null && (components = child.getChildren()) != null) {
                int index = CACHE.get(world);
                if (components.length > index) {
                    return components[index];
                }
            }
        }
        RSInterfaceChild worldInterface = Interfaces.get(69, 7);
        if (worldInterface != null) {
            RSInterfaceComponent[] components = worldInterface.getChildren();
            if (components != null) {
                for (int i = 2; i <= 416; i += 6) {
                    if (components.length > i) {
                        String text = components[i].getText();
                        try {
                            if (text != null && Integer.parseInt(text) == world) {
                                General.println(i);
                                CACHE.put(world, i - 2);
                                return components[i - 2];
                            }
                        } catch (NumberFormatException ignored) {

                        }
                    }
                }
            }
        }
        return null;
    }

    private static boolean scrollToWorldInterface(RSInterface desiredWorld) {
        final long timeout = System.currentTimeMillis() + 7000;
        Rectangle worldClickBox;
        while ((worldClickBox = desiredWorld.getAbsoluteBounds()) != null && (worldClickBox.y < 240 || worldClickBox.y > 417)) {
            Mouse.scroll(worldClickBox.y < 240);
            General.sleep(50, 100);
            if (System.currentTimeMillis() > timeout) {
                return false;
            }
        }
        General.sleep(100, 300);
        return worldClickBox != null && worldClickBox.y >= 240 && worldClickBox.y <= 417;
    }

    private static boolean worldSwitch(RSInterface desiredWorld, int world) {
        if (Clicking.click(desiredWorld)) {
            boolean isDMM = false;
            for (int i : DMM_WORLDS) {
                if (i == world) {
                    isDMM = true;
                    break;
                }
            }

            if (isDMM) {
                General.println("Waiting DMM additional hop time");
                General.sleep(11000, 13000);
            }

            return Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(100);
                    return NPCChat.getOptions() != null || WorldHopper.getWorld() == world;
                }
            }, General.random(2000, 3000)) && (WorldHopper.getWorld() == world || (NPCChat.getOptions() != null && NPCChat.selectOption("Yes.", true)));
        }

        return false;
    }

    private static boolean waitUntilWorld(int world) {
        return Timing.waitCondition(new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                if (WorldHopper.getWorld() == world && (Login.getLoginState() == Login.STATE.INGAME || Login.getLoginState() == Login.STATE.LOGINSCREEN)) {
                    General.println("We hopped to world: " + world);
                    return true;
                }
                return false;
            }
        }, General.random(4000, 6000));
    }

    public static boolean logOutSelectWorld(int world) {

        if (Game.getCurrentWorld() - 300 == world) {
            return true;
        }

        if (Login.getLoginState() == Login.STATE.INGAME) {

            if (!Timing.waitCondition(logoutCondition(), General.random(2000, 3000))) {
                return false;
            }
        }

        if (Login.getLoginState() == Login.STATE.LOGINSCREEN) {
            WorldHopper.changeWorld(world);
            return WorldHopper.getWorld() == world;
        }
        return false;
    }

    public static boolean fastLogout() {
        if (GameTab.TABS.LOGOUT.open()) {
            if (Interfaces.isInterfaceValid(69)) {
                RSInterfaceChild child = Interfaces.get(69, 3);
                if (child != null) {
                    clickRectangle(child.getAbsoluteBounds());
                }
            } else {
                RSInterfaceChild logOut = Interfaces.get(182, 10);
                if (logOut != null) {
                    clickRectangle(logOut.getAbsoluteBounds());
                }
            }
        }
        return Login.getLoginState() == Login.STATE.LOGINSCREEN;
    }

    private static void clickRectangle(Rectangle area) {
        if (area == null) {
            return;
        }
        Mouse.hop(new Point(area.x + General.random(0, area.width), area.y + General.random(0, area.height)));
        Mouse.click(1);
    }

    private static Condition logoutCondition() {
        return new Condition() {
            @Override
            public boolean active() {
                General.sleep(100);
                return fastLogout();
            }
        };
    }

    public static boolean isF2P(int world) {
        switch (world) {
            case 1:
            case 8:
            case 16:
            case 26:
            case 35:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 93:
            case 94:
                return true;
        }
        return false;
    }

    public static int getRandomWorld(boolean members) {
        int world = WorldHopper.getRandomWorld(members);
        return (world == 85 || world == 86 || world == 53) ? getRandomWorld(members) : world;
    }

    private static boolean isInWorldSelect() {
        return Screen.getColorAt(713, 10).equals(Color.BLACK);
    }

    public static void clickRectangle(Rectangle rectangle, int sdx, int sdy) {
        Mouse.hop(new Point(
                General.randomSD((rectangle.x + (rectangle.x + rectangle.width)) / 2, sdx),
                General.randomSD((rectangle.y + (rectangle.y + rectangle.height)) / 2, sdy)));
        Mouse.click(1);
    }

    public static int[] combine(int[]... arrays) {
        int totalLength = 0;
        int previous = 0;

        for (int[] array : arrays) {
            totalLength += array.length;
        }

        int[] newArray = new int[totalLength];
        for (int[] array : arrays) {
            int length = array.length;
            System.arraycopy(array, 0, newArray, previous, length);
            previous += length;
        }
        return newArray;
    }

}