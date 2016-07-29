package scripts.TribotAPI;

import org.tribot.api.General;
import org.tribot.api.input.Keyboard;
import org.tribot.api2007.Walking;
import org.tribot.api2007.util.ThreadSettings;
import org.tribot.script.Script;
import org.tribot.script.ScriptManifest;
import scripts.TribotAPI.util.Logging;

/**
 * Created by Sphiinx on 7/23/2016.
 */
public class Client {

    /**
     * Gets the script manifest of the specified class.
     *
     * @param c The class.
     * @return The script manifest; null there is no manifest.
     */
    public static ScriptManifest getManifest(Class<?> c) {
        if (c == null || !c.isAnnotationPresent(ScriptManifest.class)) {
            return null;
        }
        return c.getAnnotation(ScriptManifest.class);
    }

    public static void printStackTrace(Script script) {
        Logging.status("Script Stack Trace:");
        General.println("-------------------------------");
        final StackTraceElement[] stack_traces = script.getScriptStackTrace();
        if (stack_traces == null)
            return;

        for (StackTraceElement stack_trace : stack_traces) {
            General.println(stack_trace);
        }
        General.println("-------------------------------");
    }

}

