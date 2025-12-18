public class ShipedState implements OrderState {

	public void next(OrderContext context) {
		context.setState(new DeliveredState());
	}
	
	public void printStatus() {
		System.out.println("Order Shiped");
	}
}