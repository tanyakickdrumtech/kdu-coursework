package kdubackend.spring.handon4.logging;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerDisplay {

    private static final Logger logger = LoggerFactory.getLogger(LoggerDisplay.class);
    private LoggerDisplay(){

    }

    public static void logInfo(String message){
        logger.info(message);
    }
    public static void logError(String message){
        logger.info(message);
    }
}
