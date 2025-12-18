public class DeploymentTarget {

	public void transferArtifact(String path, String server) {
		System.out.println("DeploymentTarget: transfer new code "+path +" to "+server);
		delay();
		System.out.print("DeploymentTarget: code transfer complete.");
	}
	
	public void activateNewVersion(String server) {
		System.out.println("DeploymentTarget: activating new version: "+server);
		delay();
		System.out.print("DeploymentTarget: new version activation completed.");
	}

	private void delay() {
		try{
			Thread.sleep(2000);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}