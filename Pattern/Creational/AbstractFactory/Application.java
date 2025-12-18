public class Application {
	public final Button button;
	public final Checkbox checkbox;

	Application(UIFactory factory) {
		this.button = factory.createButton();
		this.checkbox = factory.createCheckBox();
	}

	public void renderUI() {
		button.paint();
		checkbox.paint();
	}
}