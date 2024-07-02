package MusicDatabase.UI;

import MusicDatabase.JDBC.MusicDatabaseConnector;

/**
 * This class handles all interactions with the UI
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class EventHandler {

    /**
     * This method handles MenuPanel Button presses and switches the context panel accordingly
     * @param button the button pressed
     */
    public static void handleMenuButtonEvent(MenuButtons button) {
        MusicDatabaseConnector.buttonPress(button);
    }

    public static void handleOptionButtonEvent(OptionButtons optionButton) {
        //TODO: HANDLE INSERTS (ADD), UPDATES(EDIT), DELETIONS(REMOVE), AND ADDITIONAL SELECTION OPTIONS(FILTER)
        if(optionButton == OptionButtons.Add) {

        }
        else if(optionButton == OptionButtons.Remove) {

        }
        else if(optionButton == OptionButtons.Edit) {

        }
        else if(optionButton == OptionButtons.Filter) {

        }
    }

    public static void handleComboBoxSelect(Object source) {
        System.out.println(source.toString());
    }
}
