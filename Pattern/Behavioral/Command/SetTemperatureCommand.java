public class SetTemperatureCommand implements Command {
	private final Thermostat thermostat;
	private final int newTemperature;
	private int prevTemperature;


	public SetTemperatureCommand(Thermostat thermostat, int temperature) {
		this.thermostat = thermostat;
		this.newTemperature = temperature;
	}


	public void execute() {
		prevTemperature = thermostat.getCurrentTemperature();
		thermostat.setCurrentTemperature(newTemperature);
	}

	public void undo() {
		thermostat.setCurrentTemperature(prevTemperature);
	}
}