package scripts.tribotapi.painting.paint;

import java.awt.*;

/**
 * Created by Sphiinx on 7/22/2016.
 */
public interface Paintable {

    void draw(Graphics g);

    boolean isInClick(Point p);

}

