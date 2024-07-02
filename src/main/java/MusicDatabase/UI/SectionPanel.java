package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

/**
 * This parent class holds common methods used by Panel classes as well as centralized styling values
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public abstract class SectionPanel {
    protected JPanel panel;
    protected Color mainColor = new Color(219, 234, 186);
    protected Color optionsColor = new Color(200, 186, 234);
    protected Color menuColor = new Color(152, 124, 215);
    private final String systemFontName = "Times New Roman";
    private final int entryFontSize = 20;
    private final int headerFontSize = 50;
    protected Font buttonFont = new Font(systemFontName, Font.BOLD, entryFontSize);
    protected Font entryFont = new Font(systemFontName, Font.PLAIN, entryFontSize);
    protected Font headerFont = new Font(systemFontName, Font.BOLD, headerFontSize);

    /**
     * This constructor initializes the main panel all subclasses utilizes
     */
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


    /**
     * Constructs the panel when called after singleton initialization
     * @param panelDimension dimensions of the panel
     */
    public abstract void buildPanel(Dimension panelDimension);
}
