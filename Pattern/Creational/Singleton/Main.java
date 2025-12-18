public class Main {

	/* Eager initialization
	private static final Main INSTANCE = new Main();

	public Main getInstance() {
		return INSTANCE;
	} */


	/* lazy instance , (Not thread safe)
	private static Main INSTANCE;

	public Main getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Main();
		}

		return INSTANCE;
	}*/


	// lazy instance , (Not thread safe)
	private static Main INSTANCE;

	public static Main getInstance() {
		if(INSTANCE == null) {
			synchronized(Main.class) {
				if(INSTANCE == null) {
					INSTANCE = new Main();
				}
			}
		}

		return INSTANCE;
	}


	public static void main(String[] args) {
		Main obj = new Main();

		System.out.println(obj.getInstance());
	}
}