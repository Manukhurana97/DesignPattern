package startegy;

import java.util.*;

import entity;*

public class SortbyPriority implements TaskSortStrategy{
	@Override
	public void sort(List<Task> tasks) {
		tasks.sort(Comparator.comparing(Task::getPriority));
	}
}