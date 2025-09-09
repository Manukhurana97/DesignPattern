package strategy;

import entity.LogMessage;

public class DatabaseAppender implements LogAppender {
    @Override
    public void append(LogMessage logMessage){
        System.out.println("DatabaseAppender LogMessage: " + logMessage.getTime() +" "+ logMessage.getLevel().name()+" "+ logMessage.getMessage());
    }
}