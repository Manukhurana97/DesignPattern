package state;

import entity.*;
import enums.*;

public class ReadyTaskState implements TaskState{
	@Override
	public void updateStatus(Task task) {
		task.setCurrentStatus(TaskStatus.IN_PROGRESS);
		task.setState(new InProgressTaskState());
		addActivity(task,new Activity("Status Updated to IN_PROGRESS"));
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