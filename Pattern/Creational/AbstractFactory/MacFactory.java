public class MacFactory implements UIFactory {

	@Override
	public Button createButton() {
		return new MacButton();
	}

	@Override
	public Checkbox createCheckBox() {
		return new MacCheckbox();
	}
}