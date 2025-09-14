package service; 

import java.util.*;
import java.time.LocalDateTime;

import entity.*;
import enums.*;
import observer.ActivityObserver;


public class TaskManagerService {
	Map<String, User> users;
	Map<String, TaskList> tasksList;
	Map<String, Task> tasks;

	private static final TaskManagerService taskManagerService = new TaskManagerService();	

	TaskManagerService() {
		users = new HashMap<>();
		tasksList = new HashMap<>();
		tasks = new HashMap<>();
	}

	public static TaskManagerService getInstance() {
		return taskManagerService;
	}

    // create user
	public User createUser(String userName, String email) {
		User user = new User(userName, email);
		users.put(user.getId(), user);
		System.out.println(userName +" created");
        return user;
	}

    // create task list
	public TaskList createTaskList(String name) {
		TaskList taskList = new TaskList(name);
		tasksList.put(taskList.getId(), taskList);

		System.out.println(name + " Task list created");
		return taskList;
	}

	// create task
	public Task createTask(String title, String description, LocalDateTime dueDate, TaskPriority priority, User createdBy) {
		Task task = new Task(createdBy, title, description , dueDate, priority, createdBy);
		task.addObserver(new ActivityObserver());

		System.out.println("new task Created "+title);
		return task;
	}



}