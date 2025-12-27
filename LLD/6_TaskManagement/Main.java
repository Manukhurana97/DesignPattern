import entities.Task;
import entities.TaskList;
import entities.User;
import enums.TaskPriority;
import service.TaskManager;
import strategy.SortByPriority;

import java.time.LocalDate;
import java.util.List;

public class Main {
    static void main(String[] args) {
        TaskManager taskManager = TaskManager.getInstance();

        System.out.println("=== User ===");
        User user1 = taskManager.createUser("abc", "abc@google.com");
        User user2 = taskManager.createUser("def", "def@google.com");

        System.out.println("=== TaskList ===");
        TaskList taskList1 = taskManager.createTaskList("enhancement");
        TaskList taskList2 = taskManager.createTaskList("vulnerability");

        System.out.println("=== Task ===");
        Task task1 = taskManager.createTask("add user auth check", "added auth check that user with xx role access can access /admin api", LocalDate.now().plusDays(10), TaskPriority.MEDIUM, user1);

        System.out.println("=== Task ===");
        Task subtask1 = taskManager.createSubTask(task1, "add api /auth-admin", "added auth check that user with xx role access can access /admin api", LocalDate.now().plusDays(6), TaskPriority.HIGH, user1, user1);
        Task subtask2 = taskManager.createSubTask(task1, "add api /auth-admin-test", "added auth check that user with xx role access can access /admin api", LocalDate.now().plusDays(6), TaskPriority.LOW, user1, user1);

        taskList1.addTask(task1);

        taskList1.display();

        subtask1.startProgress(user1);
        subtask1.setStoryPoint(3, user1);
        try {
            subtask1.changePriority(TaskPriority.HIGH, user2);
        } catch (Exception e) {
            System.out.println(" ### " + e.getLocalizedMessage() + " ### ");
        }
        subtask1.inProgress(user1);
        subtask1.InSprintTesting(user1);
        subtask1.cancel(user1);
        subtask1.newState(user1);
        subtask1.startProgress(user1); // re start
        subtask1.inProgress(user1);
        subtask1.InSprintTesting(user1);
        subtask1.complete(user1);

        taskManager.printTaskActivity(task1);
        taskManager.printTaskActivity(subtask1);

        List<Task> tasks = taskManager.sortTask(new SortByPriority());
		for(Task task: tasks) {
			System.out.println(task.getTitle());
		}
    }
}