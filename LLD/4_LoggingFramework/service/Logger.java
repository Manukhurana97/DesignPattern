package service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.LogManager;

import enums.*;
import strategy.*;
import entities.*;

public class Logger {
	private String name;
	private Logger parent;
	private LogLevel currentLevel;
	private List<LogAdapter> adaptors = new ArrayList<>();


	Logger(String name, Logger parent) {
		this.name = name;
		this.parent = parent;
	}

	public void setLevel(LogLevel currentLevel) {
		this.currentLevel = currentLevel;
	}

	public void addAppender(LogAdapter logAdaptor) {
		adaptors.add(logAdaptor);
	}


	public void debug(String description) { log(LogLevel.DEBUG, description); }
	public void info(String description) { log(LogLevel.INFO, description); }
	public void warn(String description) { log(LogLevel.WARN, description); }
	public void error(String description) { log(LogLevel.ERROR, description); }

	private void log(LogLevel level, String message) {
		if(level.isGreaterOrEqual(effectiveLevel())) {
			LogMessage logMessage = new LogMessage(level, this.name, message);
			callAppenders(logMessage);
		}
	}

	private LogLevel effectiveLevel() {
		for(Logger log = this; log!=null ; log = log.parent) {
			LogLevel currentLevel = log.currentLevel;

			if(currentLevel != null) return currentLevel;
		}

		return LogLevel.DEBUG;
	}

	private void callAppenders(LogMessage message) {
		if(!adaptors.isEmpty()) {
			LoggingFrameworkService.getInstance().getProcessor().process(message, this.adaptors);
		}

	}
}