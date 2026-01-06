import entities.Message;
import service.PubSubService;
import subscribe.AlertSubscriber;
import subscribe.NewSubscriber;
import subscribe.Subscriber;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PubSubService service = PubSubService.getInstance();

        Subscriber sportsFan1 = new NewSubscriber("SportsFan1");
        Subscriber sportsFan2 = new NewSubscriber("SportsFan2");
        Subscriber techie = new NewSubscriber("Techie");
        Subscriber allNewsReader = new NewSubscriber("AllNewsReader");
        Subscriber systemAdmin = new AlertSubscriber("SystemAdmin");

        final String SPORTS_TOPIC = "SPORTS";
        final String TECH_TOPIC = "TECH";
        final String NEWS_TOPIC = "NEWS";

        service.createTopic(SPORTS_TOPIC);
        service.createTopic(TECH_TOPIC);
        service.createTopic(NEWS_TOPIC);
        System.out.println();

        service.subscribe(SPORTS_TOPIC, sportsFan1);
        service.subscribe(SPORTS_TOPIC, sportsFan2);
        service.subscribe(SPORTS_TOPIC, allNewsReader);
        service.subscribe(SPORTS_TOPIC, systemAdmin);
        System.out.println();

        service.subscribe(TECH_TOPIC, techie);
        service.subscribe(TECH_TOPIC, allNewsReader);
        service.subscribe(TECH_TOPIC, systemAdmin);
        System.out.println();

        service.subscribe(NEWS_TOPIC, allNewsReader);
        service.subscribe(NEWS_TOPIC, systemAdmin);
        System.out.println();


        service.publish(SPORTS_TOPIC, new Message("Team India won Test Match with South Africa"));
        service.publish(TECH_TOPIC, new Message("Nvidia created A200 AI chip"));
        service.publish(NEWS_TOPIC, new Message("Dollar drop sharply in 10 days"));

        Thread.sleep(500);

        service.unSubscribe(SPORTS_TOPIC, systemAdmin);
        System.out.println();

        service.publish(SPORTS_TOPIC, new Message("Team India won Test Match with South Africa"));

        Thread.sleep(500);

        service.shutDown();
    }
}