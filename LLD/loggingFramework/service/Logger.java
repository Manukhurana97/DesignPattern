package service;


import entity.*;
import enums.LogLevel;
import strategy.*;

import java.util.*;
import java.util.concurrent.*;

public class Logger {
	private String name;
	private Logger parent;
	private LogLevel logLevel;
	private List<LogAppender> appenders;


	public Logger(String name, Logger parent) {
		this.name = name;
		this.parent = parent;
		this.appenders = new CopyOnWriteArrayList<>();
	}

	public void setLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
        System.out.println("Changing root log level to "+logLevel.name()+"...");
	}

	public void addAppender(LogAppender appender) {
		appenders.add(appender);
	}

	public void removeAppender(LogAppender appender) {
		appenders.remove(appender);
	}

    public LogLevel getLogLevel() {
        for (Logger logger = this; logger != null; logger = logger.parent) {
            LogLevel currentLevel = logger.logLevel;
            if (currentLevel != null) {
                return currentLevel;
            }
        }
        return LogLevel.DEBUG;
    }

	public void info(String message) {
		log(LogLevel.INFO, message);
	}

	public void debug(String message) {
		log(LogLevel.DEBUG, message);
	}

	public void warn(String message) {
		log(LogLevel.WARN, message);
	}

	private void log(LogLevel level, String message) {
		if(level.isGreaterOrEqual(getLogLevel())) {
			LogMessage logMessage = new LogMessage(level, this.name, message);
			callAppender(logMessage);
		}
	}

	private void callAppender(LogMessage logMessage) {
		if(!appenders.isEmpty()) {
			LogManager.getInstance().getProcessor().process(logMessage, this.appenders);
		}

		if(parent != null) {
			parent.callAppender(logMessage);
		}
	}
}