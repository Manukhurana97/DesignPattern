import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;


class Song {
    private final String id;
    private final String name;
    private final String url;

    public Song(String name, String url) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}

class Playlist {
    private final String id;
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        songs = new CopyOnWriteArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }
}



class PlayListManager {
    private final Map<String, Playlist> playlists = new ConcurrentHashMap<>();

    public Playlist createPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    public boolean containPlayList(String playListId) {
        return playlists.containsKey(playListId);
    }

    public Playlist getPlayList(String id) {
        return playlists.get(id);
    }
}

//------------------------------------------------------------


enum DeviceType {SPEAKER, ALEXA, BLUETOOTH;}

// factory
interface IAudioDevice {
    void playAudio(Song song);
}

class Speaker implements IAudioDevice {
    public void playAudio(Song song) {
        System.out.println("[Speaker] playing song :" + song.getName());
    }
}

class Bluetooth implements IAudioDevice {
    public void playAudio(Song song) {
        System.out.println("[Bluetooth] playing song :" + song.getName());
    }
}

//adaptor
interface AlexaAdaptor {
    void playSong(String alexaKey, Song song);
}

class AlexaPlayer implements AlexaAdaptor {
    public void playSong(String key, Song song) {
        System.out.println(key + " [Alexa] playing song : " + song);
    }
}

class Alexa implements IAudioDevice {
    AlexaAdaptor alexaAdaptor;
    String key;

    public Alexa(String key) {
        this.key = key;
        this.alexaAdaptor = new AlexaPlayer();
    }

    public void playAudio(Song song) {
        alexaAdaptor.playSong(key, song);
    }
}

class DeviceFactory {
    public static IAudioDevice getDevice(DeviceType type) {
        return switch (type) {
            case BLUETOOTH -> new Bluetooth();
            case ALEXA -> new Alexa("ALEXA_KEY");
            default -> new Speaker();
        };
    }
}


class DeviceManager {
    private final Map<DeviceType, IAudioDevice> devices = new ConcurrentHashMap<>();
    private volatile IAudioDevice activeDevice;

    public DeviceManager() {
        activeDevice = DeviceFactory.getDevice(DeviceType.SPEAKER);
        devices.put(DeviceType.SPEAKER, activeDevice);
    }

    public void addDevice(DeviceType type, IAudioDevice device) {
        devices.put(type, device);
    }

    public void setActiveDevice(DeviceType type)  {
        if(!devices.containsKey(type)) {
            System.out.println("Device not found");
            return;
        }

        activeDevice = devices.get(type);
    }

    public IAudioDevice getActiveDevice() {
        return activeDevice;
    }

    public Set<DeviceType> getActiveDevices() {
        return devices.keySet();
    }
}

class AudioEngine {
    public volatile boolean playing = false;
    public volatile Song currentSong = null;
    public volatile AtomicInteger index = new AtomicInteger(-1);
    public volatile Playlist activePlayList;


    public void playSong(Song song, IAudioDevice device) {
        if(song == null) {
            System.out.println("Invalid song");
            return;
        }

        this.currentSong = song;
        this.playing = true;
        device.playAudio(song);
        System.out.println("[Audio Engine] Playing song "+ song.getName());
    }

    public void playPlayList(Playlist playlist, IAudioDevice device) {
        if(playlist.getSongs().isEmpty()) {
            System.out.println("PlayList is empty");
            return;
        }

        this.activePlayList = playlist;
        Song song = playlist.getSongs().get(index.incrementAndGet());
        this.currentSong = song;
        this.playing = true;
        device.playAudio(song);
        System.out.println("[Audio Engine] Playing song from playlist "+ song.getName());
    }

    public void pauseSong() {
        if(currentSong == null) {
            System.out.println("No song playing");
            return;
        }

        if(!playing) {
            System.out.println("Already paused");
            return;
        }

        this.playing = false;
        System.out.println("[Audio Engine] paused");
    }

    public void resume(IAudioDevice device) {
        if(currentSong == null) {
            System.out.println("No active song");
            return;
        }

        if(playing) {
            System.out.println("Song already playing");
            return;
        }

        this.playing = true;
        device.playAudio(currentSong);
    }

    public void next(IAudioDevice device) {
        if(index.get() == -1) {
            System.out.println("No Playlist Found");
            return;
        }

        if(index.get() < activePlayList.getSongs().size()-1) {
            this.playPlayList(activePlayList, device);
        }
    }
}

class MusicPlayer {
    public final DeviceManager deviceManager;
    public final AudioEngine audioEngine;
    public final PlayListManager playListManager;

    public MusicPlayer(DeviceManager deviceManager, AudioEngine audioEngine, PlayListManager playListManager) {
        this.audioEngine=audioEngine;
        this.deviceManager = deviceManager;
        this.playListManager = playListManager;
    }

    public void addDevice(DeviceType type) {
        deviceManager.addDevice(type, DeviceFactory.getDevice(type));
        System.out.println("[Music Player] device added "+ type);
    }

    public void setActiveDevice(DeviceType type) {
        deviceManager.setActiveDevice(type);
    }

    public void listDevices() {
        for(DeviceType deviceType : deviceManager.getActiveDevices()) {
            System.out.println("( "+deviceType.name()+" )");
        }
    }

    public Playlist createPlayList(String name) {
        Playlist playlist = playListManager.createPlaylist(name);
        System.out.println("[Music Player] Playlist created "+name);
        return playlist;
    }

    public void addSongToPlayList(Playlist playlist, Song song) {
        Playlist playlist1 = playListManager.getPlayList(playlist.getId());
        playlist1.addSong(song);
        System.out.println("[Music Player] song added to playlist "+ playlist1.getName());
        return;
    }

    public void playSong(Song song) {
        audioEngine.playSong(song, deviceManager.getActiveDevice());
    }

    public void pauseSong() {
        audioEngine.pauseSong();
    }

    public void playNext() {
        audioEngine.next(deviceManager.getActiveDevice());
    }

    public void playPlayList(Playlist playlist) {
        audioEngine.playPlayList(playlist, deviceManager.getActiveDevice());
    }

}

public class Main {
    public static void main() {

    }
}