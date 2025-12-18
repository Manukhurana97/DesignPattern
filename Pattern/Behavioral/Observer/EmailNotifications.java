public class EmailNotifications implements Observer{
	
	public void update(NotificationService service) {
		System.out.println("📧 Email received: "+ service.getMessage());
	}
}