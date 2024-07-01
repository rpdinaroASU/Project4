package MusicDatabase.UI;

import javax.swing.*;

/**
 * This abstract class represents different panels of the
 * MainContainer
 */
public abstract class SectionPanel {
    protected JPanel panel;

    /**
     * Returns the panel in this singleton
     * @return panel in this singleton
     */
    public JPanel getPanel() {
        return panel;
    }

}
