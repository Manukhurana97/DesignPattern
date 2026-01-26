class CircuitBreaker {
	private final char[] buffer;
	private final int capacity;
	private int read = 0;
	private int write = 0;
	private int size = 0;

	public CircuitBreaker(int capacity) {
		this.capacity = capacity;
		this.buffer = new char[capacity+1];
	}

	public synchronized void insert(String word) {
		if(!canInsert(word.length())) {
			System.out.printf("Space full cannot insert %n");
			return;
		}

		for(char ch: word.toCharArray()) {
			buffer[write] = ch;
			write = (write + 1) % capacity;
			size++;
		}
	}

	public synchronized String read(int n) {
		if(n>size) {
			n = size;
		}

		StringBuilder builder = new StringBuilder();
		for(int i=0; i<n; i++) {
			builder.append(buffer[read]);
			read = (read + 1) % capacity;
			size--;
		}

		return builder.toString();
	}

	public  boolean canInsert(int wordLen) {
		return capacity - size >= wordLen;
	}
}

public class Main {
	public static void main(String[] args) {
		CircuitBreaker cb = new CircuitBreaker(7);
		cb.insert("Hello");
		cb.insert("to");
		cb.insert("all");

		System.out.printf("%n");
		System.out.println(cb.read(3));
		System.out.println(cb.read(2));
		System.out.println(cb.read(4));
	}
}