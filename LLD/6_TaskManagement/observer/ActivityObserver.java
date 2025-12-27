package observer;

import entities.Activity;
import entities.Task;
import entities.User;

public class ActivityObserver implements TaskObserver {

    private final Task task;

    public ActivityObserver(Task task) {
        this.task = task;
    }

    public void update(String changeDescription, User user) {
        task.log(new Activity(changeDescription, user));
    }
}