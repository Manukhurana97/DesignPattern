package formatter;

import entities.LogMessage;

public interface LogFormat {
    String format(LogMessage message);
}