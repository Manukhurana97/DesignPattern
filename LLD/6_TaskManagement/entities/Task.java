package entities;

import enums.TaskPriority;
import observer.ActivityObserver;
import observer.TaskObserver;
import state.NewState;
import state.TaskState;

import javax.naming.AuthenticationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Task {
    private final String id;
    private final String title;
    private final String description;
    private final LocalDateTime createdDate;
    private final LocalDate dueDate;
    private final User createdBy;
    private final Set<Task> subTask;
    private final Set<Tag> tags;
    private final List<Comment> comments;
    private final List<Activity> activities;
    private final TaskObserver taskObserver;
    private TaskPriority priority;
    private User assignTo;
    private int storyPoint;
    private TaskState state;

    public Task(String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.createdDate = LocalDateTime.now();
        this.dueDate = dueDate;
        this.priority = priority;
        this.createdBy = createdBy;
        this.subTask = new HashSet();
        this.tags = new HashSet();
        comments = new ArrayList<>();
        activities = new ArrayList<>();
        this.state = new NewState(this);
        this.taskObserver = new ActivityObserver(this);
    }


    public Task(String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy, User assignTo) {
        this(title, description, dueDate, priority, createdBy);
        this.assignTo = assignTo;
    }

    public Task(String title, String description, LocalDate dueDate, TaskPriority priority, User createdBy, User assignTo, int storyPoint) {
        this(title, description, dueDate, priority, createdBy, assignTo);
        this.storyPoint = storyPoint;
    }


    // -------------------- setter / getter --------------------

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Set<Task> getSubTask() {
        return subTask;
    }

    public void setAssignTo(User to, User by) {
        taskObserver.update("Assigned to user " + to.getName(), by);
        this.assignTo = to;

    }

    public void setStoryPoint(int newStoryPoint, User by) {
        taskObserver.update("Changed story point from " + this.storyPoint + " to " + newStoryPoint, by);
        this.storyPoint = newStoryPoint;

    }

    public void changePriority(TaskPriority newPriority, User by) throws AuthenticationException {
        if (!by.getId().equals(createdBy.getId())) {
            throw new AuthenticationException("Only author can change the Priority");
        }
        taskObserver.update("Changed story point from " + this.priority + " to " + priority, by);
        this.priority = newPriority;
    }

    public void addSubTask(Task task, User by) {
        taskObserver.update("new subtask added by " + task.getTitle(), by);
        subTask.add(task);
    }

    public void addComment(Comment comment, User by) {
        taskObserver.update("New comment added by ", by);
        comments.add(comment);
    }

    public void log(String description, User user) {
        this.taskObserver.update(description, user);
    }

    public void log(Activity activity) {
        activities.add(activity);
    }

    // --------------------------- state -----------------------

    public void newState(User user) {
        state.newState(user);
    }

    public void startProgress(User user) {
        state.startProgress(user);
    }

    public void inProgress(User user) {
        state.inProgress(user);
    }

    public void InSprintTesting(User user) {
        state.InSprintTesting(user);
    }

    public void complete(User user) {
        state.complete(user);
    }

    public void cancel(User user) {
        state.canceled(user);
    }


    public void setState(TaskState state) {
        this.state = state;
    }

//	----------------------------- print data ----------------------------

    public void displayActivity() {
        for (Activity activity : activities) {
            System.out.printf(" => [%s] - [%s] - %s \n", activity.getTimeStamp(), activity.getUser().getName(), activity.getDescription());
        }
    }
}