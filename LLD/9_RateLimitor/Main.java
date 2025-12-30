import service.RateLimitorService;
import strategy.SlidingWindowRateLimiter;
import strategy.TokenBasedRateLimiter;

public class Main {

    public static void main(String[] args) {
        RateLimitorService service = RateLimitorService.getInstance();
        floodTest(service, "FixedWindow");

        System.out.println("--- setting new Strategy ---");
        service.setStrategy(new SlidingWindowRateLimiter());
        floodTest(service, "SlidingWindow");

        System.out.println("--- setting new Strategy ---");
        service.setStrategy(new TokenBasedRateLimiter());
        floodTest(service, "TokenBucket");
    }

    private static void floodTest(RateLimitorService service, String name) {
        for (int i = 0; i < 20; i++) {
            if (i % 10 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            if (service.sendRequest("abc")) {
                System.out.printf("[%s] %d Requested: Allowed \n", name, i);
            } else {

                System.out.printf("[%s] %d Requested: Not-Allowed \n", name, i);
            }
        }
    }
}