package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the menu options for the Music Database
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class MenuPanel extends SectionPanel{
    private static MenuPanel menuPanelInstance;

    /**
     * This constructor creates the menu panel as a singleton
     */
    private MenuPanel() {
        panel = new JPanel();
        panel.setFocusable(true);
        panel.setVisible(true);
        panel.setPreferredSize(new Dimension(1000,300));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(MenuButtons menuButton : MenuButtons.values()) {
            JButton button = new JButton(menuButton.getButtonName());
            button.addActionListener( e -> {
                EventHandler.handleMenuButtonEvent(menuButton);
            });
            button.setPreferredSize(new Dimension(200,400));
            panel.add(button);
            panel.add(Box.createGlue());
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


}
