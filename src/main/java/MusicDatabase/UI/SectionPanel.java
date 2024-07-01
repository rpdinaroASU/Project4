package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

/**
 * This abstract class represents different panels of the
 * MainContainer
 */
public abstract class SectionPanel {
    protected JPanel panel;
    protected Color mainColor = new Color(219, 234, 186);
    protected Color menuColor = new Color(200, 186, 234);
    protected Color optionsColor = new Color(152, 124, 215);

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
