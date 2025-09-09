package service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class LogManager {

	private final Logger rootLogger;
	private static final LogManager instance = new LogManager();
	private final Map<String, Logger> loggers = new ConcurrentHashMap<>();
	private AsyncLogProcessor processor;


	public LogManager() {
		this.rootLogger = new Logger("root", null);
		loggers.put("root", rootLogger);
        this.processor = new AsyncLogProcessor();
	}


	public static LogManager getInstance() {
		return instance;
	}

	public Logger getRootLogger() {
		return rootLogger;
	}

	public Logger getLogger(String name) {
		return loggers.computeIfAbsent(name, this::createLogger);
	}

	private Logger createLogger(String name) {
		if(name.equals("root")) return rootLogger;

		int lastDot = name.lastIndexOf(".");
		String parentName = (lastDot == -1) ? "root" : name.substring(0, lastDot);
		Logger parent = getLogger(parentName);
		return new Logger(name, parent);	
	}

	public AsyncLogProcessor getProcessor() {
		return this.processor;
	}
}