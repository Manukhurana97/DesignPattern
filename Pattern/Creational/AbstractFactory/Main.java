public class Main {
	public static void main(String[] args) {
		String os = System.getProperty("os.name");
		UIFactory factory;

		if(os.contains("Window")) {
			factory = new WindowFactory();
		} else {
			factory = new MacFactory();
		}

		Application application = new Application(factory);
		application.renderUI();
	}
}