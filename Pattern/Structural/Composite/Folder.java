import java.util.*;

public class Folder implements FileSystemItem {
	private final String name;
	private final List<FileSystemItem> items = new ArrayList<>();

	public Folder(String name) {
		this.name = name;
	}

	public void add(FileSystemItem item) {
		items.add(item);
	}

	public int getSize() {
		int size = 0;
		for(var item: items) {
			size += item.getSize();
		}
		return size;
	}

	public void printStructure(String indent) {
		System.out.println(indent + "-" + name +"/");
		for(var item: items) {
			item.printStructure(indent+" ");
		}
	}
	
	public void delete() {
		for(var item: items) {
			item.delete();
		}
		System.out.print("deleting folder "+name);
	}
}