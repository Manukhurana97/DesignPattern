package state;

import entities.Task;
import entities.User;


public class CompletedState implements TaskState {
    private final Task task;

    CompletedState(Task task) {
        this.task = task;
    }

    public void newState(User user) {
        System.out.println("Task moved back to New state in progress");
        task.log("task moved back to STATE state", user);
        task.setState(new NewState(task));
    }

    public void startProgress(User user) {
        System.out.println("Task is already completed");
    }

    public void inProgress(User user) {
        System.out.println("Task is already completed");
    }

    public void InSprintTesting(User user) {
        System.out.println("Task is already completed");
    }

    public void canceled(User user) {
        System.out.println("Task is CANCELED");
        task.log("Task moved to in CANCEL state", user);
        task.setState(new CanceledState(task));
    }

    public void complete(User user) {
        System.out.println("Task Is Already Completed");
    }
}