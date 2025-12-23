package strategy;

import entities.*;

public interface LogAdapter {
    void append(LogMessage logMessage);
}