package scripts.TribotAPI.font;

import java.awt.*;

/**
 * Created by Sphiinx on 6/22/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public enum Fonts {

    PAINT_TITLE_FONT(new Font("Verdana", 0, 20)),
    PAINT_VERSION_FONT(new Font("Verdana", Font.ITALIC, 15)),
    PAINT_INFO_FONT(new Font("Verdana", 0, 13)),
    PAINT_TABS_FONT(new Font("Verdana", Font.ITALIC, 13));

    private final Font FONT;

    Fonts(Font font) {
        this.FONT = font;
    }

    /**
     * Returns the specified font.
     * */
    public Font getFont() {
        return FONT;
    }

}
