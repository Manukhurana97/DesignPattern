interface Shape {
	void draw(int size);
}

class Circle implements Shape {
	@Override
	public void draw(int size) {
		System.out.println("drawing a circle of size "+ size);
	}
}


class Rectangle implements Shape {
	@Override
	public void draw(int size) {
		System.out.println("drawing a rectangle of size "+ size);
	}
}

class ShapeFactory {
	public Shape getShape(String input) {
		if(input == null) {
			return null;
		}

		if(input.equals("circle")) {
			return new Circle();
		} else if(input == "Rectangle") {
			return new Rectangle();
		}

		return null;
	}
}



public class Main {
	public static void main(String[] args) {
		ShapeFactory factory = new ShapeFactory();
		Shape shape = factory.getShape("Rectangle");
		shape.draw(10);



	}
}