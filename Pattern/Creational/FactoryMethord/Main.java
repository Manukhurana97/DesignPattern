public class Main {

	public static void main(String[] args) {
		NotificationCreator creator;

		// send email
		creator = new EmailNotificationCreator();
		creator.send("hello");

		// send sms
		creator = new SMSNotificationCreator();
		creator.send("hello");
	}
}