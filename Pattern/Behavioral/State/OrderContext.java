public class OrderContext {

	private OrderState state;

	OrderContext() {
		this.state = new CreatedState();
	}


	public void setState(OrderState state) {
		this.state = state;
	}

	public void next() {
		state.next(this);
	}

	public void printStatus() {
		state.printStatus();
	}
}