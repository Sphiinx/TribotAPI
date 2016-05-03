package api;

import java.util.HashMap;

/**
 * Created by Sphiinx on 2/14/2016.
 */
public class Arguments {

    /**
     * Gets the script argument from specified Map.
     *
     * @param map The specified map.
     * @return The script arguments; null if there aren't any.
     * */
    public static String getArguments(HashMap<String, String> map) {
        if (map != null && !map.isEmpty()) {
            for (String key : map.keySet()) {
                String value = map.get(key);
                if (value != null) {
                    return value;
                }
            }
        }
        return null;
    }

}

