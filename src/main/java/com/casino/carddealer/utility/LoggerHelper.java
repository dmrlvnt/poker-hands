package com.casino.carddealer.utility;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Logger is used to print the error messages and the evaluation result. This helper class is used
 * to create a logger with a console handler which has only log message simple formatter.
 */
public class LoggerHelper {

  private static final String INPUT_VALIDATION_FORMAT = "%3$s %n";
  private static final Handler handler = new ConsoleHandler();

  private LoggerHelper() {
    throw new IllegalStateException("Utility class");
  }

  public static Logger createLogger(String className) {
    var mainLogger = Logger.getLogger("com.casino.carddealer");
    mainLogger.setUseParentHandlers(false);
    mainLogger.removeHandler(handler);
    handler.setFormatter(new SimpleFormatter() {
      @Override
      public synchronized String format(LogRecord lr) {
        return String.format(INPUT_VALIDATION_FORMAT,
            ZonedDateTime.ofInstant(Instant.ofEpochMilli(lr.getMillis()),
                ZoneId.systemDefault()),
            lr.getLevel().getLocalizedName(),
            lr.getMessage()
        );
      }
    });
    mainLogger.addHandler(handler);

    return Logger.getLogger(className);
  }

}
