package state;

import entities.Task;
import entities.User;


public class InSprintTestingState implements TaskState {
    private final Task task;

    InSprintTestingState(Task task) {
        this.task = task;
    }

    public void newState(User user) {
        System.out.println("Task moved back to New state in progress");
        task.log("task moved back to STATE state", user);
        task.setState(new NewState(task));
    }

    public void startProgress(User user) {
        System.out.println("Task is canceled");
    }

    public void inProgress(User user) {
        System.out.println("Task IN PROGRESS state");
        task.log("task moved to IN_PROGRESS state", user);
        task.setState(new InProgressState(task));
    }

    public void InSprintTesting(User user) {
        System.out.println("Task IN SPRINT TESTING");
        task.log("task moved to IN_SPRINT_TESTING state", user);
        task.setState(new CompletedState(task));
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