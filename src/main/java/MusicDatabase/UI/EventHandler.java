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

    public static void handleOptionButtonEvent(String s) {
        System.out.println(s);
    }

    public static void handleComboBoxSelect(Object source) {
        System.out.println(source.toString());
    }
}
