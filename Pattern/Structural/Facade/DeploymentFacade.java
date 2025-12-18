public class DeploymentFacade {
	private VersionControlSystem vcs = new VersionControlSystem();
	private BuildSystem bs = new BuildSystem();
	private TestingFramework tf = new TestingFramework();
	private DeploymentTarget dt = new DeploymentTarget();

	public boolean deployApplication(String branch, String server) {
		System.out.println("\n --- Initiating Deployment of branch: "+branch+" ---");
		boolean success = true;

		try {

			vcs.pullLatestChanges(branch);

			if(!bs.compileProject()) {
				System.out.print("Deployment failed - Build compiling failed");
				return false;
			}

			if(!tf.runUnitTest()) {
				System.out.print("Deployment failed - Unit test failed");
				return false;
			}

			dt.transferArtifact("main", server);
			dt.activateNewVersion(server);


		} catch (Exception e) {
			System.out.print("Deployment failed of branch: "+branch);
			e.printStackTrace();
			success = false;
		}

		return success;
	}
}