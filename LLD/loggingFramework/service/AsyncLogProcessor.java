package service;

import java.util.*;
import java.util.concurrent.*;

import strategy.*;
import entity.*;



public class AsyncLogProcessor {

	private final ExecutorService executor;

	public AsyncLogProcessor() {
		this.executor = Executors.newSingleThreadExecutor(runnable -> {
			Thread thread = new Thread(runnable, "service.AsyncLogProcessor");
			thread.setDaemon(true);
			return thread;
		});
	}

	public void process(LogMessage message, List<LogAppender> appenders) {
        for(LogAppender appender: appenders) {
            appender.append(message);
        }
	}
}