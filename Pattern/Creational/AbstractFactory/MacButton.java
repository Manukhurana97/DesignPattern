public class MacButton implements Button {

	@Override
	public void paint() {
		System.out.println("Painting a Mac style button");
	}

	@Override
	public void onClick() {
		System.out.println("Mac button click");
	}
}