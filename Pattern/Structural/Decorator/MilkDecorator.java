public class MilkDecorator extends CoffeeDecorator {

	public MilkDecorator(Coffee coffee) {
		super(coffee);
	}

	public double cost() {
		return coffee.cost() + 10;
	}

	public String description() {
		return coffee.description() + ", Milk";
	}
}