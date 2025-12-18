public class VersionControlSystem {

	public void pullLatestChanges(String branch) {
		System.out.println("Pulling latest changes from "+branch+"...");
		pulling();
		System.out.println("Pulling completed.");
	}

	private void pulling() {
		try{
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}