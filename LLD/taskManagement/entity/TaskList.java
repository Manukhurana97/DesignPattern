package entity;

import java.util.*;
 
public class TaskList {
	public String id;
	public String name;
	public Set<Task> tasks;

	public TaskList(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.tasks = new HashSet<>();
	}

   
	public void addTask(Task task) {
		this.tasks.add(task);
		System.out.println();
	}
  

	public String getId() { return id; }
	public String getName() { return name; }
	public Set<Task> geTasks() { return tasks; }
}