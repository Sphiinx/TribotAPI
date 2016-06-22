package TribotAPI.color;

import java.awt.*;

/**
 * Created by Sphiinx on 6/12/2016.
 * Re-written by Sphiinx on 6/12/2016
 */
public enum Colors {

    RED_COLOR(new Color(214, 39, 39, 240)),
    GRAY_COLOR(new Color(26, 24, 24, 75)),
    DARK_GRAY_COLOR(new Color(26, 24, 24, 175)),
    WHITE_COLOR(new Color(238,238,238, 240)),
    GREEN_COLOR(new Color(65, 161, 40, 240));

    private final Color COLOR;

    Colors(Color color) {
        this.COLOR = color;
    }

    public Color getCOLOR() {
        return COLOR;
    }

}

