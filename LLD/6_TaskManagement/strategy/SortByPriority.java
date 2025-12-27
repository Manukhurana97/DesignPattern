package strategy;

import entities.Task;

import java.util.Comparator;
import java.util.List;

public class SortByPriority implements TaskSortStrategy{

    public  List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(task -> task.getPriority().getPriority()));
        return tasks;
    }
}
