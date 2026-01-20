public class Circle implements Shape {
	int radius;

	Circle(int radius) {
		this.radius = radius;
	}

	public Shape clone() {
		return new Circle(this.radius);
	}

	public void draw() {
		System.out.print("Circle with radius "+radius);
	}
}