package state;

import entities.Task;
import entities.User;


public class CanceledState implements TaskState {
    private final Task task;

    CanceledState(Task task) {
        this.task = task;
    }

    public void newState(User user) {
        System.out.println("Task moved back to New state in progress");
        task.log("task moved back to STATE state", user);
        task.setState(new NewState(task));
    }

    public void startProgress(User user) {
        System.out.println("Task is already canceled");
    }

    public void inProgress(User user) {
        System.out.println("Task is already canceled");
    }

    public void InSprintTesting(User user) {
        System.out.println("Task is already canceled");
    }

    public void canceled(User user) {
        System.out.println("Task is already canceled");
    }

    public void complete(User user) {
        System.out.println("Task is canceled");
    }
}