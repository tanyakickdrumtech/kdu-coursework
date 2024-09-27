package com.example.security.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDisplay{
    private static final Logger logger= LoggerFactory.getLogger(LoggerDisplay.class);

    private LoggerDisplay(){

    }

    /**
     * displaying info
     * @param message
     */
    public static void logInfo(String message){
        logger.info(message);
    }

    /**
     * for displaying error
     * @param message
     */
    public static void logError(String message){
        logger.info(message);
    }

}
