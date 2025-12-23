import service.*;
import enums.*;
import strategy.*;

public class Main {
	public static void main(String[] args) {
		LoggingFrameworkService service = LoggingFrameworkService.getInstance();
		Logger logger = service.getRootLogger();
		logger.setLevel(LogLevel.INFO);

		logger.addAppender(new ConsoleAdapter());

		System.out.println("--- Initial Logging Demo ---");
		Logger mainLogger = service.getLogger("com.example.main");
		mainLogger.info("Application starting up at 8080.");
		mainLogger.debug("This is debug message, it should NOT appear.");
		mainLogger.warn("This is an warning message");

		logger.addAppender(new FileAdapter());

		System.out.println("--- Hierarchy demo ---");
		Logger dbLogger = service.getLogger("com.example.dao");
		dbLogger.info("Database connection pool is initializing.");

		Logger serviceLogger = service.getLogger("com.example.service");
		serviceLogger.setLevel(LogLevel.DEBUG);
		serviceLogger.info("User service initializing");
		serviceLogger.debug("This debug message will come now.");

		try{
			Thread.sleep(1000);
			service.shutdown();
		} catch (Exception e) {
			System.out.println("Caught exception");
		}
	}
}
 