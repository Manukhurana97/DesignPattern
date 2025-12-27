package service;

import entities.Task;
import entities.TaskList;
import entities.User;
import enums.TaskPriority;
import strategy.SortByDueDate;
import strategy.SortByPriority;
import strategy.TaskSortStrategy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private static TaskManager INSTANCE;
    private final Map<String, User> users;
    private final Map<String, TaskList> tasklists;
    private final Map<String, Task> tasks;
    private TaskSortStrategy sortStrategy;


    public TaskManager() {
        this.users = new HashMap<>();
        this.tasklists = new HashMap<>();
        tasks = new HashMap<>();
        sortStrategy = new SortByDueDate();
    }

    public static TaskManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaskManager();
        }

        return INSTANCE;
    }


    public User createUser(String userName, String email) {
        User user = new User(userName, email);
        users.put(user.getId(), user);

        System.out.printf("Account with userName: %s created\n", userName);
        return user;
    }


    public TaskList createTaskList(String name) {
        TaskList list = new TaskList(name);
        tasklists.put(list.getId(), list);

        System.out.printf("%s tasklist created\n", name);
        return list;
    }

    private Task createTaskHelper(String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy) {
        Task task = new Task(title, description, dueDate, priority, createdBy);
        tasks.put(task.getId(), task);

        return task;
    }

    public Task createTask(String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy) {
        Task task = createTaskHelper(title, description, dueDate, priority, createdBy);

        System.out.printf("%s task created\n", title);
        return task;
    }


    public Task createSubTask(Task task, String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy, User user) {
        Task subTask = createTaskHelper(title, description, dueDate, priority, createdBy);
        task.addSubTask(subTask, user);

        return subTask;
    }


    public void printTaskActivity(Task task) {
        task.displayActivity();
    }


    public List<Task> sortTask() {
        return sortStrategy.sort(new ArrayList<>(tasks.values()));
    }

    public List<Task> sortTask(TaskSortStrategy strategy) {
        sortStrategy = strategy;
        return this.sortTask();
    }
}