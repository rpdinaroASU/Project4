package MusicDatabase.UI;

import javax.xml.namespace.QName;

public enum TableNames {
    Song("SONG", new String[]{"SongID", "ReleaseDate", "Title", "AlbumID"},"SongID"),
    Record_Label("RECORD_LABEL",new String[]{"LabelName","Address","Phone"},"LabelName"),
    Album("ALBUM",new String[]{"AlbumID","AlbumName","LabelName"},"AlbumID"),
    Genre("GENRE",new String[]{"GenreID","GenreName"},"GenreID"),
    Contributor("CONTRIBUTOR",new String[]{"ContributorID","FirstName","LastName","DOB"},"ContributorID"),
    Playlist("PLAYLIST",new String[]{"PLID","PLName","CreatorName","DateCreated"},"PLID");

    private final String tName, pK;
    private final String[] elements;
    TableNames(String name, String[] elems, String pkey){
        elements = elems;
        tName = name;
        pK = pkey;
    }
    public String[] getElements(){return elements;}
    public String getName(){return tName;}
    public String getpK(){return pK;}
}
