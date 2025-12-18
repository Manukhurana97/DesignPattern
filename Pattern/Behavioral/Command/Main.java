public class Main {

    public static void main(String[] args) {

        Light light = new Light();
        Thermostat thermostat = new Thermostat();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command setTemp = new SetTemperatureCommand(thermostat, 24);

        SmartButton button = new SmartButton();

        System.out.println("-> Pressing Light ON");
        button.setCommand(lightOn);
        button.press();

        System.out.println("\n-> Setting temperature to 24");
        button.setCommand(setTemp);
        button.press();

        System.out.println("\n-> Pressing Light OFF");
        button.setCommand(lightOff);
        button.press();

        System.out.println("\n-> Undo last action");
        button.undo();

        System.out.println("-> Undo last action");
        button.undo();

        System.out.println("-> Undo last action");
        button.undo();
    }
}
