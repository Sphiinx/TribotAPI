package scripts.TribotAPI.color;

import java.awt.*;

/**
 * Created by Sphiinx on 6/12/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public enum Colors {

    RED_COLOR(new Color(214, 39, 39, 240)),
    LIGHT_RED_COLOR(new Color(255, 64, 64, 250)),
    LIGHT_GRAY_COLOR(new Color(26, 24, 24, 45)),
    GRAY_COLOR(new Color(26, 24, 24, 75)),
    DARK_GRAY_COLOR(new Color(26, 24, 24, 160)),
    WHITE_COLOR(new Color(238,238,238, 240)),
    GREEN_COLOR(new Color(65, 161, 40, 240));

    private final Color COLOR;

    Colors(Color color) {
        this.COLOR = color;
    }

    /**
     * Returns the specified color.
     * */
    public Color getCOLOR() {
        return COLOR;
    }

}

