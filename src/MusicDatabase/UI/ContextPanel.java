package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

public class ContextPanel {
    private static ContextPanel contextPanelInstance;
    private final JPanel contextPanel;
    private ContextPanel() {
        contextPanel = new JPanel();
        //TODO: CREATE CONTEXT PANEL
    }

    public static ContextPanel getInstance() {
        if(contextPanelInstance==null) {
            contextPanelInstance = new ContextPanel();
            return contextPanelInstance;
        }
        return contextPanelInstance;
    }

    public JPanel getContextPanel() {
        return contextPanel;
    }
}
