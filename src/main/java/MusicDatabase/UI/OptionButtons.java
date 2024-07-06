package MusicDatabase.UI;

/**
 * Buttons in the optionPane
 */
public enum OptionButtons implements ButtonInterface {
    Edit,
    Add,
    Remove,
    Filter;

    @Override
    public String getName() {
        return this.name();
    }
}
