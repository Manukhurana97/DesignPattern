package enums;

public enum LogLevel {
	INFO(1),WARN(2),DEBUG(3),ERROR(4);

	private final int level;

	LogLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	public boolean isGreaterOrEqual(LogLevel logLevel) {
		return this.level >= logLevel.level;
	}

}