public class WindowsButton implements Button {

	@Override
	public void paint() {
		System.out.println("Painting a window style button");
	}

	@Override
	public void onClick() {
		System.out.println("Window button click");
	}
}