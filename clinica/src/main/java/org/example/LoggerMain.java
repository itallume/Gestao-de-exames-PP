package org.example;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerMain {

    private static Logger logger = Logger.getLogger("MinhaFachadaLogger");

    static {
        try {
            FileHandler fh = new FileHandler("logs.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void erro(String msg) {
        logger.severe(msg);
    }
}
