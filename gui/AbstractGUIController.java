package scripts.tribotapi.gui;

/**
 * Created by Sphiinx on 7/12/2016.
 */
public class AbstractGUIController {

    private GUI gui = null;

    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    public GUI getGUI() {
        return this.gui;
    }

}

