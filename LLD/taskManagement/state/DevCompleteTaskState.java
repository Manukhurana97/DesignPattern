package state;

import entity.*;
import enums.*;

public class DevCompleteTaskState implements TaskState {
	@Override
	public void updateStatus(Task task) {
		if(task.getCurrentStatus() == TaskStatus.COMPLETED) {
			System.out.println("Task Already completed");
			return;
		}

		task.setCurrentStatus(TaskStatus.COMPLETED);
		task.setState(new DevCompleteTaskState());
		addActivity(task, new Activity("Status Updated to COMPLETED"));
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