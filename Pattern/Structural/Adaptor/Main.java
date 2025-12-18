public class Main {
	public static void main(String[] args) {
		PaymentProcessor processor = new PaymentAdaptor(new OldPaymentGateway());
		processor.pay(100.31);
	}
}