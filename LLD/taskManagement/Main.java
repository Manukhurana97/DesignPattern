import entity.*;
import enums.*;
import service.*;

import java.time.LocalDateTime;


public class Main {
	public static void main(String[] args) {
		TaskManagerService taskManagerService = TaskManagerService.getInstance();
		
		User A = taskManagerService.createUser("A", "A@gmail.com");
		User B = taskManagerService.createUser("B", "B@gmail.com");
		User C = taskManagerService.createUser("C", "C@gmail.com");

		System.out.println();
		TaskList taskList1 = taskManagerService.createTaskList("Tool 1");
		TaskList taskList2 = taskManagerService.createTaskList("Tool 2");


		Task task1 = taskManagerService.createTask("Task title 1", "Task description 1", LocalDateTime.now().plusDays(2), TaskPriority.LOW, A);
		Task task2 = taskManagerService.createTask("Task title 2", "Task description 2", LocalDateTime.now().plusDays(1), TaskPriority.HIGH, A);
		Task task3 = taskManagerService.createTask("Task title 3", "Task description 3", LocalDateTime.now().plusDays(5), TaskPriority.LOW, B);

		taskList1.addTask(task1);
		taskList2.addTask(task2);
		taskList2.addTask(task2);

		for(var task: taskList1.geTasks()) {
			System.out.println("List 1 "+ task.toString());
		}
		for(var task: taskList2.geTasks()) {
			System.out.println("List 2"+ task.toString());
		}

        System.out.println();
		task1.addSubTask(A, task3);

		// taskManagerService.display();

		task3.assignUser(A, B);

		task3.startProgress();
		task3.startProgress();

	}
}