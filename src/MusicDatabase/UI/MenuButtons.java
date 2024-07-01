package MusicDatabase.UI;

public enum MenuButtons {
    RecordLabels("Record Labels") , Albums("Albums"), Songs("Songs"),
    Genres("Genres"), Contributors("Contributors"),
    Playlists("Playlists");

    private final String name;
    MenuButtons(String s) {
        name = s;
    }

    public String getButtonName() {
        return name;
    }
}
