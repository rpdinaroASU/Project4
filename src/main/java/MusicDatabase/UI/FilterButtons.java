package MusicDatabase.UI;

public enum FilterButtons implements ButtonInterface {
    SONGS_BY_GENRE("Songs By Genre"),
    SONGS_BY_ALBUM("Songs By Album"),
    SONGS_BY_PLAYLIST("Songs By Playlist");

    private final String name;
    FilterButtons(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
