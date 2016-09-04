package scripts.tribotapi;

import org.tribot.api.General;
import scripts.tribotapi.util.Logging;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Sphiinx on 7/27/2016.
 */
public class PostRequest {

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
        try {
            final Map<String, Object> PARAMETERS = new LinkedHashMap<>();
            final String DATE = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
            PARAMETERS.put("auth", "E6135CCC2FE8BE8C");
            PARAMETERS.put("username", General.getTRiBotUsername());
            PARAMETERS.put("script_name", script_name);
            PARAMETERS.put("date", DATE);
            PARAMETERS.put("version", version);
            PARAMETERS.put("bug_description", bug_description);
            PARAMETERS.put("bug_stacktrace", bug_stacktrace);
            PARAMETERS.put("bug_clientdebug", bug_clientdebug);
            PARAMETERS.put("bug_botdebug", bug_botdebug);

            return sendPostRequest("http://www.spxscripts.com/bugreports/input.php", PARAMETERS);
        } catch (Exception e) {
            General.println(e.getMessage());
        }
        return false;
    }

    /**
     * Sends a post request to the specified url with the hash map.
     * Converts the data from the hash map to a byte[] before sending it.
     *
     * @param url_name The url to send the post request to.
     * @param hash_map The hash map containing the parameters.
     *
     * @return True if the data was sent successfully; false otherwise.
     * */
    public static boolean sendPostRequest(String url_name, Map<String, Object> hash_map) {
        try {
            final StringBuilder POST_DATA = new StringBuilder();
            for (Map.Entry<String, Object> param : hash_map.entrySet()) {
                if (POST_DATA.length() != 0)
                    POST_DATA.append('&');

                POST_DATA.append('&');
                POST_DATA.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                POST_DATA.append('=');
                POST_DATA.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            final byte[] POST_DATA_BYTES = POST_DATA.toString().getBytes("UTF-8");

            final URL URL = new URL(url_name);
            final HttpURLConnection CONNECTION = (HttpURLConnection) URL.openConnection();
            CONNECTION.setRequestMethod("POST");
            CONNECTION.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            CONNECTION.setRequestProperty("Content-Length", String.valueOf(POST_DATA_BYTES.length));
            CONNECTION.setDoOutput(true);
            CONNECTION.getOutputStream().write(POST_DATA_BYTES);
            final Reader READER = new BufferedReader(new InputStreamReader(CONNECTION.getInputStream(), "UTF-8"));

            return true;
        } catch (Exception e) {
            Logging.error(e.getMessage());
        }

        return false;
    }

}

