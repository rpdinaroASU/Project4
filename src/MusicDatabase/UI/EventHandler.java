package MusicDatabase.UI;

public class EventHandler {
    public static void handleMenuButtonEvent(MenuButtons button) {
        System.out.println(button.getButtonName());
        //TODO: SWITCH CONTEXT PANELS ON BUTTON PRESS





        ContextPanel.getInstance().getContextPanel().updateUI();
    }
}
