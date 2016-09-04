package scripts.tribotapi;

import com.allatori.annotations.DoNotRename;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.util.Screenshots;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.interfaces.*;
import org.tribot.util.Util;
import scripts.task_framework.framework.Task;
import scripts.task_framework.framework.TaskManager;
import scripts.tribotapi.gui.GUI;
import scripts.tribotapi.painting.mouse.MouseManager;
import scripts.tribotapi.painting.paint.PaintManager;
import scripts.tribotapi.painting.paint.SkillData;
import scripts.tribotapi.painting.paint.enums.DataPosition;
import scripts.tribotapi.painting.paint.paintables.InteractivePaint;
import scripts.tribotapi.painting.paint.paintables.SkillBackground;
import scripts.tribotapi.painting.paint.paintables.tabs.*;
import scripts.tribotapi.util.Logging;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Sphiinx on 7/24/2016.
 */
@DoNotRename
public abstract class AbstractScript extends Script implements Painting, MousePainting, MouseSplinePainting, EventBlockingOverride, Ending {

    /**
     * The master object for the task manager.
     * */
    protected TaskManager task_manager = new TaskManager();

    /**
     * The master object for the paint manager.
     * */
    protected PaintManager paint_manager = new PaintManager(new InteractivePaint(Client.getManifest(this.getClass()).name(), Client.getManifest(this.getClass()).version()), new SkillBackground(), new GeneralTab(), new StatsTab(), new GUITab(), new ScreenshotTab(), new TogglePaintTab(), new StackTraceTab());

    /**
     * The master object for the mouse manager.
     * */
    protected MouseManager mouse_manager = new MouseManager();

    /**
     * The master object for the GUI.
     * */
    protected GUI gui = getGUI();

    /**
     * Gets the GUI object.
     * */
    protected abstract GUI getGUI();

    /**
     * Adds all of the given tasks to the task list.
     *
     * @param tasks The tasks to be added to the task list.
     * */
    public void addTasks(Task... tasks) {
        task_manager.addTask(tasks);
    }

    @Override
    public void run() {
        ThreadSettings.get().setClickingAPIUseDynamic(true);
        Walking.setWalkingTimeout(1000);
        SkillData.initialiseAll();

        if (FileManagment.createDirectory(Util.getWorkingDirectory().getAbsolutePath(), "SPXScripts"))
            FileManagment.createDirectory(Util.getWorkingDirectory().getAbsolutePath() + "\\SPXScripts", Client.getManifest(this.getClass()).name().replace(" ", "_"));

        General.sleep(150); // The GUI didn't seem to instantiate quick enough.
        if (this.gui != null) {
            this.gui.show();
            TaskManager.setStatus("Initializing");

            while (this.gui.isOpen())
                sleep(250);
        }

        addTasks();
        task_manager.loop(150, 200);
    }

    @Override
    public void onEnd() {
        Logging.status("Thank you for using " + Client.getManifest(this.getClass()).name() + ", " + General.getTRiBotUsername() + "!");

        if (this.gui != null)
            this.gui.close();
    }

    @Override
    public void onPaint(Graphics g) {
        SkillData.updateAll();
        paint_manager.drawInteractivePaint(g);
        paint_manager.drawGeneralData("Runtime: ", Timing.msToString(this.getRunningTime()), DataPosition.ONE, g);
        paint_manager.drawStatData(this, g);
    }

    @Override
    public OVERRIDE_RETURN overrideKeyEvent(KeyEvent keyEvent) {
        return OVERRIDE_RETURN.PROCESS;
    }

    @Override
    public OVERRIDE_RETURN overrideMouseEvent(MouseEvent mouse_event) {
        if (mouse_event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (paint_manager.togglePaint(mouse_event.getPoint())) {
                return OVERRIDE_RETURN.DISMISS;
            } else if (paint_manager.isInClick(StackTraceTab.class, mouse_event.getPoint())) {
                Client.printStackTrace(this);
                return OVERRIDE_RETURN.DISMISS;
            } else if (paint_manager.toggleTab(mouse_event.getPoint())) {
                return OVERRIDE_RETURN.DISMISS;
            } else if (paint_manager.isInClick(ScreenshotTab.class, mouse_event.getPoint())) {
                Logging.status("Screenshot saved to your .TRiBot folder.");
                Screenshots.take(true);
                return OVERRIDE_RETURN.DISMISS;
            } else if (paint_manager.isInClick(GUITab.class, mouse_event.getPoint())) {
                if (gui != null)
                    this.gui.show();
                return OVERRIDE_RETURN.DISMISS;
            }
        }

        return OVERRIDE_RETURN.PROCESS;
    }

    @Override
    public void paintMouse(Graphics g, Point point, Point point1) {
        mouse_manager.drawMouse(g);
    }

    @Override
    public void paintMouseSpline(Graphics graphics, ArrayList<Point> arrayList) {

    }

}