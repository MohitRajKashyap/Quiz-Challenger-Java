

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple console logger utility.
 */
public class LoggerUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void info(String message) {
        System.out.println("INFO [" + sdf.format(new Date()) + "]: " + message);
    }

    public static void error(String message, Throwable t) {
        System.err.println("ERROR [" + sdf.format(new Date()) + "]: " + message);
        if (t != null) {
            t.printStackTrace(System.err);
        }
    }
}
