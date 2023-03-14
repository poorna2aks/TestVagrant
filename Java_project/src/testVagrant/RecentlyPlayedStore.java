package testVagrant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RecentlyPlayedStore {

    private int capacity;
    private Map<String, LinkedList<String>> userSongsMap;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        userSongsMap = new HashMap<>();
    }

    public void addSong(String user, String song) {
        LinkedList<String> songsList = userSongsMap.get(user);
        if (songsList == null) {
            songsList = new LinkedList<>();
            userSongsMap.put(user, songsList);
        }
        songsList.addFirst(song);
        if (songsList.size() > capacity) {
            songsList.removeLast();
        }
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        LinkedList<String> songsList = userSongsMap.get(user);
        if (songsList == null) {
            return Collections.emptyList();
        }
        return new ArrayList<>(songsList);
    }

    public static void main(String[] args) {
        RecentlyPlayedStore store = new RecentlyPlayedStore(3);

        store.addSong("user1", "S1");
        store.addSong("user1", "S2");
        store.addSong("user1", "S3");
        System.out.println(store.getRecentlyPlayedSongs("user1")); // [S3, S2, S1]

        store.addSong("user1", "S4");
        System.out.println(store.getRecentlyPlayedSongs("user1")); // [S4, S3, S2]

        store.addSong("user1", "S2");
        System.out.println(store.getRecentlyPlayedSongs("user1")); // [S2, S4, S3]

        store.addSong("user1", "S1");
        System.out.println(store.getRecentlyPlayedSongs("user1")); // [S1, S2, S4]
    }

}