package state;

import entity.*;
import enums.*;

public class InProgressTaskState implements TaskState {
	@Override
	public void updateStatus(Task task) {
		task.setCurrentStatus(TaskStatus.DEV_COMPLETE);
		task.setState(new DevCompleteTaskState());
		addActivity(task,new Activity("Status Updated to DEV_COMPLETE"));
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