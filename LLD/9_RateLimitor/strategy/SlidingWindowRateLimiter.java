package strategy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimitStrategy {
    private final int requests = 5;
    private final int windowMillis = 1000;
    private final ConcurrentHashMap<String, Deque<Long>> userWindow;

    public SlidingWindowRateLimiter() {
        userWindow = new ConcurrentHashMap<>();
    }


    public boolean requestAllowed(String userId) {
        long now = System.currentTimeMillis();

        Deque<Long> queue = userWindow.computeIfAbsent(
                userId, _ -> new ArrayDeque<>()
        );

        synchronized (queue) {
            while (!queue.isEmpty() && now - queue.peek() >= windowMillis) {
                queue.pollFirst();
            }

            if (queue.size() < requests) {
                queue.addLast(now);
                return true;
            }

            return false;
        }
    }
}