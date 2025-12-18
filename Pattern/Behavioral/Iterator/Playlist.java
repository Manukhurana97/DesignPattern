import java.util.*;

public class Playlist implements IterableCollection<String>{

	private final List<String> songs = new ArrayList<>();

	public void addSong(String name) {
		songs.add(name);
	}

	public String getSongAt(int index) {
		return songs.get(index);
	}

	public int getSize() {
		return songs.size();
	}

	public Iterator<String> createIterator() {
		return new PlaylistIterator(this);
	}
}