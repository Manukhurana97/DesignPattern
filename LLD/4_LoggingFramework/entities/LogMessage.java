package entities;

import enums.*;

import java.time.LocalDateTime;

public class LogMessage {
	private final LogLevel level;
	private final String name;
	private final String description;
	public final LocalDateTime timeStamp;
	private final String thread;

	public LogMessage(LogLevel level, String name, String description) {
		this.level = level;
		this.name = name;
		this.description = description;
		this.timeStamp = LocalDateTime.now();
		this.thread = Thread.currentThread().getName();
	}

	public LogLevel getLevel() { return level; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public LocalDateTime getTimeStamp() { return timeStamp;}
	public String getThread() { return thread; }
}