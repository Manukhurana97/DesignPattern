public class Main {
	public static void main(String[] args) {
		PaymentContext context = new PaymentContext();
		context.setStrategy(new UPIPayment());
		context.pay(100.99);

		context.setStrategy(new CardPayment());
		context.pay(100.99);

		context.pay(8.97);
	}
}