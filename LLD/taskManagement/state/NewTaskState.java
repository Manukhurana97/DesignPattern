package state;

import entity.*;
import enums.*;

public class NewTaskState implements TaskState{
	@Override
	public void updateStatus(Task task) {
		task.setCurrentStatus(TaskStatus.OPEN);
		task.setState(new ReadyTaskState());
		addActivity(task,new Activity("Status Updated to OPEN"));
	}

	@Override
	public void updateStatus(Task task, TaskStatus status, TaskState state) {
		task.setCurrentStatus(status);
		task.setState(state);
        addActivity(task, new Activity("Status Updated to " + status.name()));
	}

	private void addActivity(Task task, Activity activity) {
		task.addActivity(activity);
	}
}