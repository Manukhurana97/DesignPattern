public class WalletPayment implements PaymentStrategy {

	public final String type = "WALLET";

	public void pay(double amount) {
		System.out.println("Paid "+amount+" using Wallet");
	}
}