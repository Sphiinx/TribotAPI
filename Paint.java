package scripts.API;

import org.tribot.api.input.Mouse;

import java.awt.*;

/**
 * Created by Sphiinx on 2/15/2016.
 */
public class Paint {

    public static final Color RED_COLOR = new Color(214, 39, 39, 240);

    public static void drawMouse(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.drawRect(Mouse.getPos().x - 13, Mouse.getPos().y - 13, 27, 27); // Rectangle stroke.
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y - 512, 1, 500);     // Top y axis stroke.
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y + 13, 1, 500);      // Bottom y axis stroke.
        graphics.drawRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 1);      // Right x axis stroke.
        graphics.drawRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 1);     // left x axis stroke.
        graphics.fillOval(Mouse.getPos().x - 3, Mouse.getPos().y - 3, 7, 7);     // Center dot stroke.
        graphics.setColor(RED_COLOR);
        graphics.drawRect(Mouse.getPos().x - 12, Mouse.getPos().y - 12, 25, 25); // Rectangle.
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y - 512, 0, 500);     // Top y axis line.
        graphics.drawRect(Mouse.getPos().x, Mouse.getPos().y + 13, 0, 500);      // Bottom y axis line.
        graphics.drawRect(Mouse.getPos().x + 13, Mouse.getPos().y, 800, 0);      // Right x axis line.
        graphics.drawRect(Mouse.getPos().x - 812, Mouse.getPos().y, 800, 0);     // left x axis line.
        graphics.fillOval(Mouse.getPos().x - 2, Mouse.getPos().y - 2, 5, 5);     // Center dot.
    }

}

