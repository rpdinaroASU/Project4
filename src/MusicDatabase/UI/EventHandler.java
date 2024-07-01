package MusicDatabase.UI;

/**
 * This class handles all interactions with the UI
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class EventHandler {

    /**
     * This method handles Menu Button presses and switches the context panel accordingly
     * @param button the button pressed
     */
    public static void handleMenuButtonEvent(MenuButtons button) {
        System.out.println(button.getButtonName());
        //TODO: SWITCH CONTEXT PANELS ON BUTTON PRESS





        ContextPanel.getInstance().getContextPanel().updateUI();
    }
}
