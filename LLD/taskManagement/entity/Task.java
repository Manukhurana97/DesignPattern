package entity;

import enums.*;
import observer.TaskObserver;
import state.*;

import java.util.*;
import java.time.LocalDateTime;

public class Task {
	private final String id;
	private String title;
	private String description;
	private TaskPriority priority;
	private TaskStatus currentStatus;
	private final User createdBy;
	private final LocalDateTime createdAt;
	private LocalDateTime dueDate;
	private User assignTo;
	private Set<Task> subTasks;
	private List<Comment> comments;
	private List<TaskObserver> observers;
	private List<Tag> tags;
	private List<Activity> activities;
    private TaskState state;


    public Task(User user, String title, String description , LocalDateTime dueDate, TaskPriority priority, User createdBy) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.currentStatus = TaskStatus.NEW;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.dueDate = dueDate;

        subTasks = new HashSet<>();
        comments = new ArrayList<>();
        observers = new ArrayList<>();
        tags = new ArrayList<>();
        activities = new ArrayList<>();

        this.state = new NewTaskState();
    }

    public void assignUser(User from, User to) {
    	this.assignTo = to;
    	this.addActivity(new Activity(id +" is assign to "+ to.getName() +" by "+from .getName()));
    }

    public synchronized void addSubTask(User user, Task subTask) {
        this.subTasks.add(subTask);
        this.addActivity(new Activity("New subtask "+subTask.getId() +" added by "+user.getName()));
    }

    public synchronized void addComment(User user, Comment comment) {
    	comments.add(comment);
        this.addActivity(new Activity("Comment added by "+user.getName()));
    }

    public synchronized void addObserver(TaskObserver observer) {
    	observers.add(observer);
    	this.addActivity(new Activity("New Observer added"));
    }

    public synchronized void addTag(User user, Tag tag) {
    	tags.add(tag);
    	this.addActivity(new Activity("New tag "+tag.getName() +" added by "+user.getName()));
    }


    public void startProgress() {
        String oldStatus = currentStatus.name();
    	this.state.updateStatus(this);
        this.addActivity(new Activity("status update from: "+oldStatus +" to: " + currentStatus.name()));
    }

    public synchronized void addActivity(Activity activity) {
        activities.add(activity);
        this.notifyObserver(activity.getDescription());
    }

    private void notifyObserver(String description) {
        for(TaskObserver observer : observers) {
            observer.update(description);
        }
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public TaskStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(TaskStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public User getAssignTo() {
        return assignTo;
    }

    public Set<Task> getSubTasks() {
        return subTasks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<TaskObserver> getObservers() {
        return observers;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setState(TaskState state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", currentStatus=" + currentStatus +
                '}';
    }
}