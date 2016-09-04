package scripts.tribotapi.painting.mouse;

import org.tribot.api.input.Mouse;
import scripts.tribotapi.color.Colors;

import java.awt.*;

/**
 * Created by Sphiinx on 2/15/2016.
 * Re-written by Sphiinx on 7/8/2016.
 */
public class MouseManager {

    /**
     * Draws the SPXMouse to the RS Screen.
     *
     * @param g Graphics.
     */
    public void drawMouse(Graphics g) {
        g.setColor(Colors.RED_COLOR.getCOLOR());
        g.drawRect(Mouse.getPos().x - 13, Mouse.getPos().y - 13, 27, 27); // Rectangle.
        g.fillRect(Mouse.getPos().x, Mouse.getPos().y - 512, 2, 500);     // Top y axis.
        g.fillRect(Mouse.getPos().x, Mouse.getPos().y + 13, 2, 500);      // Bottom y axis.
        g.fillRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 2);      // Right x axis.
        g.fillRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 2);     // left x axis.
        g.fillOval(Mouse.getPos().x - 2, Mouse.getPos().y - 2, 5, 5);     // Center dot.

        g.setColor(Colors.DARK_GRAY_COLOR.getCOLOR());
        g.drawRect(Mouse.getPos().x - 14, Mouse.getPos().y - 14, 29, 29); // Rectangle.
        g.drawRect(Mouse.getPos().x - 12, Mouse.getPos().y - 12, 25, 25); // Rectangle.
        g.drawRect(Mouse.getPos().x, Mouse.getPos().y - 512, 2, 500);     // Top y axis.
        g.drawRect(Mouse.getPos().x, Mouse.getPos().y + 13, 2, 500);      // Bottom y axis.
        g.drawRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 2);      // Right x axis.
        g.drawRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 2);     // left x axis.
        g.drawOval(Mouse.getPos().x - 2, Mouse.getPos().y - 2, 5, 5);     // Center dot.
    }

}