package MusicDatabase.UI;
/**
 * @author Zac Barlow
 * @version 1.0.0
 */
public enum EditButtons implements ButtonInterface {
    SUBMIT_EDIT("Apply Edit");

    private final String name;
    EditButtons(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
