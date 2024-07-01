package MusicDatabase.UI;

import javax.swing.*;

/**
 * This class is the context panel, the displayed values of the context panel are the returns of
 * the JDBC
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class ContextPanel extends PanelSection {
    private static ContextPanel contextPanelInstance;


    /**
     * Singleton of the context panel
     */
    private ContextPanel() {
        panel = new JPanel();
        //TODO: CREATE CONTEXT PANEL
    }

    /**
     * Initializes and returns the singleton instance of the context panel
     * @return singleton instance of the context panel
     */
    public static ContextPanel getInstance() {
        if(contextPanelInstance==null) {
            contextPanelInstance = new ContextPanel();
            return contextPanelInstance;
        }
        return contextPanelInstance;
    }


}
