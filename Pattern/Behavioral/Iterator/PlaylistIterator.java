public class PlaylistIterator implements Iterator<String> {
	private final Playlist playlist;
	private int index = 0;

	public PlaylistIterator(Playlist playlist) {
		this.playlist = playlist;
	}

	public boolean hasNext(){
		return index < playlist.getSize();
	}

	public String next() {
		return playlist.getSongAt(index++);
	}
}