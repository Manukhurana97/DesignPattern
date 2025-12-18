public class Main {

	public static void main(String[] args) {
		EmailNotifications email = new EmailNotifications();
		SMSNotifications sms = new SMSNotifications();

		NotificationService service = new NotificationService();
		service.register(email);
		service.sendMessage("Order Placed Successfully!");
		System.out.println();

		service.register(sms);
		service.sendMessage("Order Out For Delivery!");
		System.out.println();	

		service.unregister(email);
		service.sendMessage("Order Delivered!");	
	}
}