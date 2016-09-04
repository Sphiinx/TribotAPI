package scripts.tribotapi.util;

import org.tribot.api.General;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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

    /**
     * Gets the pixel width of the specified text.
     *
     * @param text The text to get the width of.
     * @param g    Graphics.
     */
    public static int getTextLength(String text, Graphics g) {
        if (text == null)
            return -1;

        return g.getFontMetrics().stringWidth(text);
    }

    /**
     * Opens the specified URI.
     *
     * @param uri The URI to open.
     * @return True if successful; false otherwise.
     * */
    public static boolean openURL(String uri) {
        if (!Desktop.isDesktopSupported()) {
            General.println("Sorry! It seems your desktop instance does not support opening URL's.");
            return false;
        }

        final Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI(uri));
            return true;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Replaces the null fake spaces in text.
     *
     * @param text The text.
     * @return The text with the null fake space replaced with a real space.
     * */
    public static String removeNullSpace(String text) {
        if (text == null)
            return null;
        
        String fixed_text = "";
        for (int i = 0; i < text.length(); i++) {
            final char character = text.charAt(i);
            final int numValue = Character.getNumericValue(character);
            fixed_text += numValue == -1 ? " " : character;
        }

        return fixed_text;
    }

}