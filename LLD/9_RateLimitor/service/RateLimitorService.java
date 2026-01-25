spackage service;

import entities.*;
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


	public Response sendRequest(String userId) {
		if(strategy.requestAllowed(userId)) {
			return new Response(true, 200, "Ok");
		}
			
		return new Response(false, 429, "Too many requests");
	}	
}