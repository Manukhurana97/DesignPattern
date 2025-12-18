public class PaidState implements OrderState {

	public void next(OrderContext context) {
		context.setState(new ShipedState());
	}
	
	public void printStatus() {
		System.out.println("Order paid");
	}
}