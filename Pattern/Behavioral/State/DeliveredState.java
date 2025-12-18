public class DeliveredState implements OrderState {

	public void next(OrderContext context) {
		System.out.print("Order already delivered");
	}
	
	public void printStatus() {
		System.out.println("Order Delivered");
	}
}