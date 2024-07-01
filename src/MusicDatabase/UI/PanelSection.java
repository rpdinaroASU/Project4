package MusicDatabase.UI;

import javax.swing.*;

public abstract class PanelSection {
    protected JPanel panel;

    /**
     * Returns the panel in this singleton
     * @return panel in this singleton
     */
    public JPanel getPanel() {
        return panel;
    }

}
