public class Main {

	public static void main(String[] args) {
		FileSystemItem f1 = new File("readme.md", 2);
		FileSystemItem f2 = new File("Main.java", 5);
		FileSystemItem f3 = new File("Main.class", 6);

		Folder folder = new Folder("Composite");
		folder.add(f1);
		folder.add(f2);
		folder.add(f3);

		folder.printStructure("");
		System.out.println("Total size: "+folder.getSize()+" KB");
	}
}