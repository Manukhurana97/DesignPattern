public class Main {
	public static void main(String[] args) {
		Circle orignalObj = new Circle(10);
		Circle copy = (Circle) orignalObj.clone();
		copy.draw();
	}
}