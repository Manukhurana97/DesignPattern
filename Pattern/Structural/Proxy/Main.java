public class Main {
	public static void main(String[] args) {
		Image image = new ImageProxy("photo1.png");
		image.display(); // loading + display
		image.display(); // display

		Image image1 = new ImageProxy("photo2.png");
		image1.display(); // loading + display
		image1.display(); // display
	}
}