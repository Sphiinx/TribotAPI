package scripts.TribotAPI;

import org.tribot.api.General;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

/**
 * Created by Sphiinx on 7/27/2016.
 */
public class SendReportData {

    /**
     * The master time in which the last report was sent.
     */
    public static long LAST_SENT_TIME;

    /**
     * Sends the report data from the GUI to SPXScripts.
     *
     * @param script_name     The script name.
     * @param version         The script version.
     * @param bug_description The bug description.
     * @param bug_stacktrace  The stack trace text.
     * @param bug_clientdebug The client debug text.
     * @param bug_botdebug    The bot debug text.
     * @return True if the data was sent successfully; false otherwise.
     */
    public static boolean sendReportData(String script_name, Double version, String bug_description, String bug_stacktrace, String bug_clientdebug, String bug_botdebug) {
        final String initVector = "E6135CCC2FE8BE8C";
        try {
            final String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            final String data = initVector + "," + General.getTRiBotUsername() + "," + script_name + "," + date + "," + version + "," + bug_description + "," + bug_stacktrace + "," + bug_clientdebug + "," + bug_botdebug;
            String token = Base64.getEncoder().encodeToString(data.getBytes());

            final URL url = new URL("http://www.spxscripts.com/bugreports/input.php?token=" + token);
            final URLConnection conn = url.openConnection();

            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-GB; rv:1.9.2.13) Gecko/20101203 Firefox/3.6.13 (.NET CLR 3.5.30729)");

            final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            in.readLine();
            in.close();
            return true;

        } catch (Exception e) {
            General.println(e.getMessage());
        }
        return false;
    }

}

