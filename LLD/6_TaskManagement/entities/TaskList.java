package entities;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TaskList {
    private final String id;
    private final String userName;
    private final Set<Task> tasks;

    public TaskList(String userName) {
        this.id = UUID.randomUUID().toString();
        this.userName = userName;
        this.tasks = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void display() {
        for (Task task : tasks) {
            System.out.println(" -> " + task.getTitle());

            Set<Task> subtasks = task.getSubTask();
            if (!subtasks.isEmpty()) {
                for (Task subTask : task.getSubTask()) {
                    System.out.println(" -> -> " + subTask.getTitle());
                }
            }
        }
    }

}