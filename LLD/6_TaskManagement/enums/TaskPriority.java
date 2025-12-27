package enums;

public enum TaskPriority {
    LOW(1), MEDIUM(2), HIGH(3), VERY_HIGH(4);

    private final int priority;

    TaskPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}