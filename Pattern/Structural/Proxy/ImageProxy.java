public class ImageProxy implements Image {

	private RealImage realImage;
	private String fileName;

	public ImageProxy(String fileName) {
		this.fileName = fileName;
	}

	public void display() {
		if(realImage == null) {
			synchronized(this){
				if(realImage == null) {
					realImage = new RealImage(fileName);  // lazy loading
				}
			}
		}

		realImage.display();
	} 
}