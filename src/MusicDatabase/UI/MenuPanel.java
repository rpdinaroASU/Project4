package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the menu options for the Music Database
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class MenuPanel {
    private static MenuPanel menuPanelInstance;
    private final JPanel menuPanel;

    /**
     * This constructor creates the menu panel as a singleton
     */
    private MenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setFocusable(true);
        menuPanel.setVisible(true);
        menuPanel.setPreferredSize(new Dimension(1000,300));

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        for(MenuButtons menuButton : MenuButtons.values()) {
            JButton button = new JButton(menuButton.getButtonName());
            button.addActionListener( e -> {
                EventHandler.handleMenuButtonEvent(menuButton);
            });
            button.setPreferredSize(new Dimension(200,400));
            menuPanel.add(button);
            menuPanel.add(Box.createGlue());
        }
    }

    /**
     * Returns and initializes a singleton instance of MenuPanel
     * @return singleton instance of MenuPanel
     */
    public static MenuPanel getInstance() {
        if(menuPanelInstance == null) {
            menuPanelInstance = new MenuPanel();
        }
        return menuPanelInstance;
    }

    /**
     * Returns the panel in this singleton
     * @return panel in this singleton
     */
    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
