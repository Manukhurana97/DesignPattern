public class SugarDecorator extends CoffeeDecorator {

	public SugarDecorator(Coffee coffee) {
		super(coffee);
	}

	public double cost() {
		return coffee.cost() + 4;
	}

	public String description() {
		return coffee.description() + ", Suger";
	}
}