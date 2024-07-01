package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

/**
 * This abstract class represents different panels of the
 * MainContainer
 */
public abstract class SectionPanel {
    protected JPanel panel;
    protected Color panelColor;
    public SectionPanel() {
        panel = new JPanel();
        panel.setFocusable(true);
        panel.setVisible(true);
    }

    /**
     * Returns the panel in this singleton
     * @return panel in this singleton
     */
    public JPanel getPanel() {
        return panel;
    }


    public abstract void buildPanel(Dimension panelDimension);
}
