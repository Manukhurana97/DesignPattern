interface State {
	void pressButton(Fan fan);
}



class OffState implements State {
	@Override
	public void pressButton(Fan fan) {
		System.out.println("Fan is Off -> Turning on fan to low state");
		fan.nextState(new LowState());
	}
}

class LowState implements State { 
	@Override
	public void pressButton(Fan fan) {
		System.out.println("Fan is low state -> increasing speed to medium state");
		fan.nextState(new MediumState());
	}
}

class MediumState implements State { 
	@Override
	public void pressButton(Fan fan) {
		System.out.println("Fan is medium state -> increasing speed to high state");
		fan.nextState(new HighState());
	}
}

class HighState implements State { 
	@Override
	public void pressButton(Fan fan) {
		System.out.println("Fan is high state -> Turning off Fan");
		fan.nextState(new OffState());
	}
}



class Fan {
	private State state;

	Fan() {
		this.state = new OffState();
	}

	public void nextState(State state) {
		this.state = state;
	}	

	public void pressButton() {
		state.pressButton(this);
	}
}



public class Main {
	public static void main(String[] args) {
		Fan fan = new Fan();
		
		fan.pressButton();
		fan.pressButton();
		fan.pressButton();
		fan.pressButton();
		
	}
}	