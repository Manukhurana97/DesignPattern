interface PaymentSystem {
	void makePayment(int amount);
}



class CreditCard implements PaymentSystem{
	String cardNumber;

	CreditCard(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public void makePayment(int amount) {
		System.out.println("made payment successfully of amount "+ amount+" using credit card " + cardNumber);
	}
}

class Netbaking implements PaymentSystem{
	String bank;

	Netbaking(String bank) {
		this.bank = bank;
	}

	@Override
	public void makePayment(int amount) {
		System.out.println("made payment successfully of amount "+ amount+" using " + bank);
	}
} 

class UPI implements PaymentSystem{
	String upiId;

	UPI(String upiId) {
		this.upiId = upiId;
	}

	@Override
	public void makePayment(int amount) {
		System.out.println("made payment successfully of amount "+ amount+" using upi id" + upiId);
	}
}


class Shoping {
	private PaymentSystem paymentSystem;

	public void setPaymentStrategy(PaymentSystem paymentSystem) {
		this.paymentSystem = paymentSystem;
	}

	public void checkout(int amount) {
		if(paymentSystem == null) {
			System.out.println("Please select the payment methord first");
		} else{	
			paymentSystem.makePayment(amount);
		}
	}
}



public class Main {
	public static void main(String[] args) {
		Shoping cart = new Shoping();
		cart.checkout(15);

		cart.setPaymentStrategy(new UPI("abc@ybl"));
		cart.checkout(100);

		cart.setPaymentStrategy(new CreditCard("123-345-5-67"));
		cart.checkout(1000);

		cart.setPaymentStrategy(new Netbaking("icici"));
		cart.checkout(100000);
	}
}