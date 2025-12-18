public class Thermostat {

	private int currenTemperature;

	public int getCurrentTemperature() {
		return currenTemperature;
	}

	public void setCurrentTemperature(int temperature) {
		System.out.println("Thermostat temperature is set to "+temperature+"°C");
		this.currenTemperature = temperature;
	} 
}