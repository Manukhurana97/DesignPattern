package service;

import strategy.*;

public class RateLimitorService {
	private static RateLimitorService INSTANCE;
	private RateLimitStrategy strategy;

	public RateLimitorService() {
		strategy = new FixedWindowRateLimitorStrategy();
	}

	public static synchronized RateLimitorService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new RateLimitorService();
		}

		return INSTANCE;
	}


	public void setStrategy(RateLimitStrategy strategy) {
		this.strategy = strategy;
	}


	public boolean sendRequest(String userId) {
		return strategy.requestAllowed(userId);
	}	
}