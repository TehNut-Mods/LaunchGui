package tehnut.launchgui.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tehnut.launchgui.ConfigHandler;
import tehnut.launchgui.LaunchGui;

public class LogHelper {

    private static Logger logger = LogManager.getLogger(LaunchGui.NAME);

    public static void info(Object info) {
        if (ConfigHandler.enableLogging)
            logger.info(info);
    }

    public static void error(Object error) {
        if (ConfigHandler.enableLogging)
            logger.error(error);
    }

    public static void error(Object error, Throwable throwable) {
        if (ConfigHandler.enableLogging)
            logger.error(error, throwable);
    }

    public static void debug(Object debug) {
        if (ConfigHandler.enableLogging)
            logger.debug(debug);
    }
}
