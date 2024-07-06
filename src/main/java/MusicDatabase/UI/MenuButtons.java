package MusicDatabase.UI;

public enum MenuButtons implements ButtonInterface {
    RecordLabels("Record Labels") , Albums("Albums"), Songs("Songs"),
    Genres("Genres"), Contributors("Contributors"),
    Playlists("Playlists");

    private final String name;
    MenuButtons(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
