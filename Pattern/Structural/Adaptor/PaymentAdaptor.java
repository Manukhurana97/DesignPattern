public class PaymentAdaptor implements PaymentProcessor {
	private OldPaymentGateway oldPaymentGateway;

	PaymentAdaptor(OldPaymentGateway oldPaymentGateway) {
		this.oldPaymentGateway = oldPaymentGateway;
	}

	@Override
	public void pay(double amount) {
		oldPaymentGateway.makePayment(amount);
	}
}