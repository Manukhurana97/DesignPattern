public class Main {
	public static void main(String[] args) {
		Playlist playlist = new Playlist();
		playlist.addSong("1");
		playlist.addSong("2");
		playlist.addSong("3");
	
		Iterator<String> iterator = playlist.createIterator();
		while(iterator.hasNext()) {
			System.out.println(" 🎵 "+iterator.next());
		}
	}
}
