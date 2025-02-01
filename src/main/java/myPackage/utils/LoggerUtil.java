package myPackage.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(LoggerUtil.class);

    // Log an info message
    public static void info(String message) {
        logger.info(message);
    }

    // Log an error message
    public static void error(String message) {
        logger.error(message);
    }

    // Log a debug message
    public static void debug(String message) {
        logger.debug(message);
    }

    // Log a warning message
    public static void warn(String message) {
        logger.warn(message);
    }
}

