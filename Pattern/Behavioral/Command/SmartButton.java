import java.util.*;

public class SmartButton {
	private Command currentCommand;
	private final Stack<Command> history = new Stack<>();

	public void setCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}

	public void press() {
		if(currentCommand != null) {
			currentCommand.execute();
			history.push(currentCommand);
		} else {
			System.out.println("No command assigned");
		}
	}

	public void undo() {
		if(!history.isEmpty()) {
			Command lastCommand = history.pop();
			lastCommand.undo();
		} else {
			System.out.println("Nothing to undo");
		}
	}
}