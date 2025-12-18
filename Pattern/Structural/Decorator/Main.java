public class Main {
	public static void main(String[] args) {
		Coffee coffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));

		System.out.println(coffee.description());
        System.out.println("Cost: " + coffee.cost());
	}
}