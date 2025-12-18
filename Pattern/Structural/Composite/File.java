public class File implements FileSystemItem {
	private final String name;
	private final int size;

	public File(String name, int size) {
		this.name = name;
		this.size = size;
	}

	public int getSize() {
		return this.size;
	}

	public void printStructure(String indent) {
		System.out.println(indent + "-" + name + "(" + size + " KB)");
	}
	
	public void delete() {
		System.out.print("deleting file "+name);
	}
}