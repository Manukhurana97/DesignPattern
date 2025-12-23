package strategy;


import entities.LogMessage;
import formatter.LogFormat;
import formatter.SimpleLogFormatter;

public class ConsoleAdapter implements LogAdapter {

    private LogFormat formatter;

    public ConsoleAdapter() {
        formatter = new SimpleLogFormatter();
    }

    public void append(LogMessage logMessage) {
        System.out.println("Console: "+formatter.format(logMessage));
        System.out.println(logMessage.getDescription());
    }


}