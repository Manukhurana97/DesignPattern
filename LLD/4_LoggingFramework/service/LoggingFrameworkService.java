package service;

import java.util.*;
import java.util.concurrent.*;

public class LoggingFrameworkService {
    private static final LoggingFrameworkService INSTANCE = new LoggingFrameworkService();
    private final Logger rootLogger;
    private final AsyncProcessor processor;
    private final Map<String, Logger> loggers = new ConcurrentHashMap<>();


    LoggingFrameworkService() {
        rootLogger = new Logger("root", null);
        loggers.put("root", rootLogger);
        this.processor = new AsyncProcessor();
    }


    public static LoggingFrameworkService getInstance() {
        return INSTANCE;
    }


    public Logger getRootLogger() {
        return rootLogger;
    }

    public Logger getLogger(String name) {
    	Logger logger = loggers.get(name);
        if(logger != null) return logger;

        synchronized(this) {
            logger = loggers.get(name);
            if(logger != null) return logger;

            Logger newLogger = this.createLogger(name);
            loggers.put(name, newLogger);

            return newLogger;
        }
    }

    private Logger createLogger(String name) {
        if (name.equals("root")) return rootLogger;
        
        int lastDot = name.lastIndexOf('.');
        String parentName = (lastDot == -1) ? "root" : name.substring(0, lastDot);

        Logger parent = getLogger(parentName);
        return new Logger(name, parent);
    }

    public AsyncProcessor getProcessor() {
        return processor;
    }

    public void shutdown() {
        processor.stop();
    }
}