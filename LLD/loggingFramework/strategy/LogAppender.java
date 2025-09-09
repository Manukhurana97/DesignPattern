package strategy;

import entity.*;

public interface LogAppender{
	void append(LogMessage logMessage);
}