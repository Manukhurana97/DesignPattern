public class WindowCheckbox implements Checkbox{

	@Override
	public void paint() {
		System.out.println("Painting a window style checkbox");
	}
	
	@Override
	public void onSelect() {
		System.out.println("Window checkbox selected");
	}
}