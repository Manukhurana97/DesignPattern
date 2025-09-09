package entity;

import enums.LogLevel;

import java.time.LocalDateTime;


public class LogMessage {
	private String message;
	private LocalDateTime time;
	private LogLevel level;
	private String name;

	public LogMessage(LogLevel level, String name, String message) {
	 	this.level = level;
	 	this.name = name;
	 	this.message = message;
         this.time = LocalDateTime.now();
	}


    public String getMessage() {
        return message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }
}