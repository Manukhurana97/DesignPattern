package strategy;

import java.util.concurrent.ConcurrentHashMap;

import entities.*;

public class TokenBasedRateLimiter implements RateLimitStrategy {

    private final int requests = 5;
    private final int windowMillis = 1000;
    private final ConcurrentHashMap<String, TokenBucket> buckets;

    public TokenBasedRateLimiter() {
        buckets = new ConcurrentHashMap<>();
    }

    public boolean requestAllowed(String userId) {
        TokenBucket bucket = buckets.computeIfAbsent(
            userId, _ -> new TokenBucket(requests, 5.0/60.0)
        );

        return bucket.isAllowed();
    }
}