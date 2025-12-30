package entities;

public class TokenBucket {
    private final int capacity;
    private final double refillRate;
    private double token;
    private long lastRefillTime;

    public TokenBucket(int capacity, double refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.token = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public synchronized boolean isAllowed() {
        refill();

        if (token >= 1) {
            token -= 1;
            return true;
        }

        return false;
    }

    private void refill() {
        long now = System.currentTimeMillis();
        long elapsedMillis = now - lastRefillTime;

        double tokenToAdd = (elapsedMillis / 1000.0) * refillRate;

        if (tokenToAdd > 0) {
            token = Math.min(capacity, token + tokenToAdd);
            lastRefillTime = now;
        }
    }
}