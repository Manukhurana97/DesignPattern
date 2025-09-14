package state;

import entity.*;
import enums.*;

public interface TaskState {
	void updateStatus(Task task);
	void updateStatus(Task task, TaskStatus status, TaskState state);
}