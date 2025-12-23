package strategy;

import entities.LogMessage;
import formatter.LogFormat;
import formatter.SimpleLogFormatter;

public class FileAdapter implements LogAdapter {

    private LogFormat formatter;

    public FileAdapter() {
        formatter = new SimpleLogFormatter();
    }

    public void append(LogMessage logMessage) {
        System.out.println("Console: "+formatter.format(logMessage));
    }
}