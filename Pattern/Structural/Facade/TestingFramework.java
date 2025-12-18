public class TestingFramework {

	public boolean runUnitTest() {
		System.out.println("TestingFramework: Running unit test...");
		testing();
		System.out.println("TestingFramework: All Unit test passed.");
		return true;
	}

	private void testing() {
		try{
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}