package MusicDatabase.UI;

public enum MenuButtons implements ButtonInterface {
    RecordLabels("Record_Label", "LabelName") , Albums("Album","AlbumID"),
    Songs("Song","SongID"), Genres("Genre","GenreID"),
    Contributors("Contributor","ContributorID"), Playlists("Playlist","PLID");

    private final String name;
    private final String pK;
    MenuButtons(String s, String pKey) {
        name = s;
        pK = pKey;
    }

    public String getName() {
        return name;
    }
    public String getpK(){return pK;}
}
