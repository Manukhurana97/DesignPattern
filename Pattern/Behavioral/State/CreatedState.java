public class CreatedState implements OrderState {

	public void next(OrderContext context) {
		context.setState(new PaidState());
	}

	public void printStatus() {
		System.out.println("Order created");
	}
}