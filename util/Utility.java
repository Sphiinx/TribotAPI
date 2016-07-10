package scripts.TribotAPI.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Sphiinx on 6/13/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class Utility {

    /**
     * Gets the image from the url.
     *
     * @param url The url of the image.
     * @return The image.
     */
    public static Image getImage(String url) {
        try {
            return ImageIO.read(new URL(url));
        } catch (IOException e) {
            Logging.error("Failed to load image: " + url);
            return null;
        }
    }

}