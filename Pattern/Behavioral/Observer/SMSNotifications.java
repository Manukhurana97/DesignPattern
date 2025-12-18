public class SMSNotifications implements Observer{
	
	public void update(NotificationService service) {
		System.out.println("📱 SMS received:"+ service.getMessage());
	}
}