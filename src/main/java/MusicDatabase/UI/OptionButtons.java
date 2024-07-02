package MusicDatabase.UI;

public enum OptionButtons {
    Edit("Edit"),
    Add("Add"),
    Remove("Remove"),
    Filter("Filter");


    private final String name;
    OptionButtons(String s) {
        name = s;
    }

    public String getButtonName() {
        return name;
    }
}
