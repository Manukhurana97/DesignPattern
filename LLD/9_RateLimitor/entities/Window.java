package entities;

public class Window {
	private int count;
	private long startTime;

	public Window(long startTime) {
		this.reset(startTime);
	}

	public int getCount() { 
		return count; 
	}
	
	public long startTime() { 
		return startTime; 
	}

	public void reset(long startTime) {
		this.startTime = startTime;
		this.count = 0;
	}

	public void incrementCount() {
		this.count += 1;
	}
}