import enums.LogLevel;
import service.LogManager;
import service.Logger;
import strategy.*;

public class Main {
	public static void main(String[] args) {
		LogManager manager = LogManager.getInstance();
		Logger rootLogger = manager.getRootLogger();

		rootLogger.setLevel(LogLevel.INFO);
		rootLogger.addAppender(new ConsoleAppender());

	
		Logger mainLogger = manager.getLogger("com.main");
		mainLogger.info("Application is starting up");
		mainLogger.debug("his is a debug message, it should NOT appear.");
		mainLogger.warn("This is a warning message.");

        Logger dbLogger = manager.getLogger("com.example.db");
        // dbLogger inherits level and appenders from root
        dbLogger.info("Database connection pool initializing.");

        Logger serviceLogger = manager.getLogger("com.example.service.UserService");
        serviceLogger.setLevel(LogLevel.DEBUG); // More verbose logging for this specific service
        serviceLogger.info("User service starting.");
        serviceLogger.debug("This debug message SHOULD now appear for the service logger.");

        rootLogger.setLevel(LogLevel.DEBUG);
        mainLogger.debug("This debug message should now be visible.");
    }
}