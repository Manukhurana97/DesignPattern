package strategy;

public interface RateLimitStrategy {

	boolean requestAllowed(String userId);
}