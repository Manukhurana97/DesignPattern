public class LightOffCommand implements Command {

	private final Light light;

	LightOffCommand(Light light) {
		this.light = light;
	}

	public void execute(){
		light.off();
	}
	
	public void undo() {
		light.on();
	}
}