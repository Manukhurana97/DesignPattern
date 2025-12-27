package strategy;

import entities.Task;

import java.util.Comparator;
import java.util.List;

public class SortByDueDate implements TaskSortStrategy{

    public List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDueDate));
        return tasks;
    }
}
