public class RealImage implements Image {
	private String fileName;

	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDisk();
	}

	private void loadFromDisk() {
		System.out.println("Loading image: " + fileName);
	}

	public void display() {
		System.out.println("Displaying image: " + fileName);
	}
}