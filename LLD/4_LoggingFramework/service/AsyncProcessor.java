package service;

import entities.LogMessage;
import strategy.LogAdapter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncProcessor {

    private final ExecutorService service;

    public AsyncProcessor() {
        this.service = Executors.newSingleThreadExecutor(
                runnable -> {
                    Thread thread = new Thread(runnable, "AsyncLogProcessor");
                    thread.setDaemon(true);
                    return thread;
                }
        );
    }

    public void process(LogMessage message, List<LogAdapter> adaptors) {
        if(service.isShutdown()) {
            System.out.println("Logger is shutdown, cant process log message");
            return;
        }

        service.submit(() -> {
            for(LogAdapter adapter: adaptors) {
                adapter.append(message);
            }
        });
    }

    public void stop() {
        service.shutdown();

        try {
            if(!service.awaitTermination(2, TimeUnit.SECONDS)) {
                System.out.println("Logger executor didn't terminated in the specified time");
            }

            service.shutdownNow();
        } catch (InterruptedException e) {
            service.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
