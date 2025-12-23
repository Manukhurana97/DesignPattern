package formatter;

import entities.LogMessage;

public class SimpleLogFormatter implements LogFormat {

    @Override
    public String format(LogMessage logMessage) {
        StringBuilder sb = new StringBuilder();

        sb.append("[").append(logMessage.getTimeStamp()).append("] ");
        sb.append("[").append(logMessage.getLevel()).append("] ");
        sb.append(logMessage.getDescription());

        return sb.toString();
    }
}
