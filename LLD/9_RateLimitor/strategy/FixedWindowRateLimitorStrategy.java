package strategy;

import entities.Window;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimitorStrategy implements RateLimitStrategy {
    private final int requests = 5;
    private final int windowMillis = 1000;
    private final ConcurrentHashMap<String, Window> userWindow;

    public FixedWindowRateLimitorStrategy() {
        userWindow = new ConcurrentHashMap<>();
    }


    public boolean requestAllowed(String userId) {
        long now = System.currentTimeMillis();

        Window window = userWindow.computeIfAbsent(
            userId, _ -> new Window(now)
        );

        synchronized (window) {
            if (now - window.startTime() >= windowMillis) {
                window.reset(now);
            }

            if (window.getCount() < requests) {
                window.incrementCount();
                return true;
            }

            return false;
        }
    }
}