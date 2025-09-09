package strategy;

import entity.LogMessage;

public class ConsoleAppender implements LogAppender{
    @Override
    public void append(LogMessage logMessage){
        System.out.println("ConsoleAppender LogMessage: " + logMessage.getTime() +" "+ logMessage.getLevel().name()+" "+ logMessage.getMessage());
    }
}