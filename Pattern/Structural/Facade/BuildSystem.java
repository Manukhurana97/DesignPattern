public class BuildSystem {

	public boolean compileProject() {
		System.out.println("BuildSystem: Compile project...");
		building();
		System.out.println("BuildSystem: Build successful.");
		return true;
	}

	private void building() {
		try{
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}