public class WindowFactory implements UIFactory {

	@Override
	public Button createButton() {
		return new WindowsButton();
	}

	@Override
	public Checkbox createCheckBox() {
		return new WindowCheckbox();
	}
}