package api.Game.Interfaces;

import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.NPCChat;
import org.tribot.api2007.types.RSInterface;
import org.tribot.api2007.types.RSInterfaceChild;
import org.tribot.api2007.types.RSInterfaceComponent;
import org.tribot.api2007.types.RSInterfaceMaster;
import scripts.SPXAIOMiner.api.util.ArrayUtil;


import java.awt.event.KeyEvent;
import java.util.HashMap;

/**
 * Created by Sphiinx on 1/10/2016.
 */
public class Interfaces07 {

    private static final int[] CLOSE_BUTTON_TEXTURE_IDS = {535, 539, 541, 831};

    private static final HashMap<String, Interface> interfaceCache = new HashMap<>();

    /**
     * Closes all interfaces that are not an allowed Interface.
     * @param allowedInterfaces All of the allowed interfaces.
     * @return True if successful; false otherwise.
     * */
    public boolean closeAllInterfaces(RSInterface... allowedInterfaces) {
        if (Interfaces.getAll() != allowedInterfaces) {
            Interfaces.closeAll();
            return true;
        }
        return false;
    }

    /**
     * Gets the texture ID of the specified interface.
     *
     * @param inter The interface.
     * @return The texture ID of the specified interface.
     * -1 is returned if the interface was null.
     */
    public static int getTextureId(RSInterface inter) {
        if (inter != null) {
            return inter.getTextureID();
        }
        return -1;
    }

    /**
     * Checks to see if the specified interface is a close button.
     *
     * @param inter The interface.
     * @return True if it is a close button, false otherwise.
     */
    public static boolean isCloseButton(RSInterface inter) {
        return ArrayUtil.contains(getTextureId(inter), CLOSE_BUTTON_TEXTURE_IDS);
    }

    /**
     * Closes any "closeable" interfaces that are open.
     * An interface is considered to be closable if it has a close button within it.
     *
     * @return True if an interface was closed, false otherwise.
     */
    public static boolean closeAll() {
        RSInterfaceMaster[] all = getAll();
        if (all.length > 0) {
            for (RSInterfaceMaster master : all) {
                RSInterfaceChild[] children = master.getChildren();
                if (children != null && children.length > 0) {
                    for (RSInterfaceChild child : children) {
                        if (child != null) {
                            if (isCloseButton(child)) {
                                Keyboard.pressKeys(KeyEvent.VK_ESCAPE);
                                return true;
                            } else {
                                RSInterfaceComponent[] components = child.getChildren();
                                if (components != null && components.length > 0) {
                                    for (RSInterfaceComponent component : components) {
                                        if (component != null) {
                                            if (isCloseButton(component)) {
                                                Keyboard.pressKeys(KeyEvent.VK_ESCAPE);
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @author Encoded
     * Gets the interface for the specified component index of the specified child index of the specified parent index.
     *
     * @param parent The parent index.
     * @param child The child index.
     * @param component The component index.
     * @return The component of the child of the parent, or null.
     */
    public static RSInterface getInterface(int parent, int child, int component)  {
        RSInterface i = Interfaces.get(parent, child);
        if (i != null) {
            RSInterface c = i.getChild(component);
            return c;
        }
        return null;
    }

    /**
     * Gets the text of the specified interface.
     *
     * @param inter The interface.
     * @return The text on the specified interface.
     * An empty String is returned if the interface has no text or was null.
     */
    public static String getText(RSInterface inter) {
        if (inter != null) {
            String text = inter.getText();
            if (text != null) {
                return text;
            }
        }
        return "";
    }

    /**
     * Checks to see if the specified interface's text equals the specified text.
     * This method strips all formatting from the interface text in HTML.
     *
     * @param inter The interface being tested.
     * @param txt   The text to check for.
     * @return True if the specified interface's text equals the specified text, false otherwise.
     */
    public static boolean equalsText(RSInterface inter, String txt) {
        return inter != null && General.stripFormatting(getText(inter).replaceAll("<col=00ff00>", "")).equals(txt);
    }

    /**
     * Gets the interface cache.
     *
     * @return The interface cache.
     */
    public static HashMap<String, Interface> getInterfaceCache() {
        return interfaceCache;
    }

    /**
     * Gets all of the master interfaces that are currently valid in an array.
     *
     * @return The master interfaces.
     */
    public static RSInterfaceMaster[] getAll() {
        return Interfaces.getAll();
    }

    /**
     * Gets the interface at the specified master -> child -> component indexes.
     *
     * @param masterIndex    The master index.
     * @param childIndex     The child index.
     * @param componentIndex The component index.
     * @return The interface.
     * Null if no interface was found.
     */
    public static RSInterfaceComponent get(int masterIndex, int childIndex, int componentIndex) {
        RSInterfaceChild child = get(masterIndex, childIndex);
        if (child != null) {
            return child.getChild(componentIndex);
        }
        return null;
    }

    /**
     * Gets the interface at the specified master -> child indexes.
     *
     * @param masterIndex The master index.
     * @param childIndex  The child index.
     * @return The interface.
     * Null if no interface was found.
     */
    public static RSInterfaceChild get(int masterIndex, int childIndex) {
        return Interfaces.get(masterIndex, childIndex);
    }

    /**
     * Gets the interface at the specified master indexes.
     *
     * @param masterIndex The master index.
     * @return The interface.
     * Null if no interface was found.
     */
    public static RSInterfaceMaster get(int masterIndex) {
        return Interfaces.get(masterIndex);
    }

    /**
     * Gets the first interface found that contains the specified text.
     *
     * @param text The text to search for.
     * @return The first interface found that contains the specified text. Null if no interface was found.
     */
    public static RSInterface get(String text) {
        if (getInterfaceCache().containsKey(text)) {
            Interface i = getInterfaceCache().get(text);
            if (i.getComponentIndex() != -1) {
                return get(i.getMasterIndex(), i.getChildIndex(), i.getComponentIndex());
            } else if (i.getChildIndex() != -1) {
                return get(i.getMasterIndex(), i.getChildIndex());
            } else {
                return get(i.getMasterIndex());
            }
        }
        RSInterfaceMaster[] all = getAll();
        if (all != null && all.length > 0) {
            for (RSInterfaceMaster master : all) {
                if (master != null) {
                    if (equalsText(master, text)) {
                        getInterfaceCache().put(text, new Interface(master.getIndex(), -1, -1));
                        return master;
                    }
                    RSInterfaceChild[] children = master.getChildren();
                    if (children != null && children.length > 0) {
                        for (RSInterfaceChild child : children) {
                            if (child != null) {
                                if (equalsText(child, text)) {
                                    getInterfaceCache().put(text, new Interface(master.getIndex(), child.getIndex(), -1));
                                    return child;
                                }
                                RSInterfaceComponent[] components = child.getChildren();
                                if (components != null && components.length > 0) {
                                    for (RSInterfaceComponent component : components) {
                                        if (component != null) {
                                            if (equalsText(component, text)) {
                                                getInterfaceCache().put(text, new Interface(master.getIndex(), child.getIndex(), component.getIndex()));
                                                return component;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Checks to see if the select option interface is up.
     *
     * @return True if is is; false otherwise.
     */
    public static boolean isSelectOptionUp() {
        return NPCChat.getSelectOptionInterface() != null;
    }

    /**
     * Checks to see if the specified interface is hidden.
     *
     * @param inter The interface to check.
     * @return True if the interface is hidden, false otherwise.
     */
    public static boolean isHidden(RSInterface inter) {
        return inter == null || inter.isHidden();
    }

    /**
     * Checks to see if the enter amount menu is visible.
     *
     * @return True if it is visible, false otherwise.
     */
    public static boolean isEnterAmountMenuUp() {
        return !isHidden(get("Enter amount:"));
        //return Screen.getColorAt(new Point(260, 428)).equals(new Color(0, 0, 128));
    }

    protected static class Interface {

        private final int masterIndex;
        private final int childIndex;
        private final int componentIndex;

        /**
         * Constructs a new Interface.
         *
         * @param masterIndex    The master index.
         * @param childIndex     The child index.
         * @param componentIndex The component index.
         */
        public Interface(int masterIndex, int childIndex, int componentIndex) {
            this.masterIndex = masterIndex;
            this.childIndex = childIndex;
            this.componentIndex = componentIndex;
        }

        /**
         * Gets the master index for the interface.
         *
         * @return The master index.
         */
        public int getMasterIndex() {
            return masterIndex;
        }

        /**
         * Gets the child index for the interface.
         *
         * @return The child index.
         */
        public int getChildIndex() {
            return childIndex;
        }

        /**
         * Gets the component index for the interface.
         *
         * @return The component index.
         */
        public int getComponentIndex() {
            return componentIndex;
        }
    }

}

