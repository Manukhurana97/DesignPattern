package state;

import entities.Task;
import entities.User;
import enums.TaskStatus;

public class NewState implements TaskState {
    private final Task task;

    public NewState(Task task) {
        this.task = task;
    }

    public String getCurrentState() {
        return TaskStatus.NEW.name();
    }

    public void newState(User user) {
        System.out.println("Task moved back to New state in progress");
        task.log("task moved back to NEW state", user);
        task.setState(new NewState(task));
    }

    public void startProgress(User user) {
        System.out.println("Task to State state start");
        task.log("task moved to in start state", user);
        task.setState(new InProgressState(task));
    }

    public void inProgress(User user) {
        System.out.println("Invalid State");
    }

    public void InSprintTesting(User user) {
        System.out.println("Invalid State");
    }

    public void canceled(User user) {
        System.out.println("Task is canceled");
        task.log("Task moved to in canceled state", user);
        task.setState(new CanceledState(task));
    }

    public void complete(User user) {
        System.out.println("Invalid State");
    }
}