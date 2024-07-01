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
        panel.setBackground(Color.BLUE);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createHorizontalStrut(panel.getHeight()/(MenuButtons.values().length+10)));
        panel.add(Box.createGlue());
        for(MenuButtons menuButton : MenuButtons.values()) {
            JButton button = new JButton(menuButton.getButtonName());
            button.addActionListener( e -> {
                EventHandler.handleMenuButtonEvent(menuButton);
            });
            button.setPreferredSize(new Dimension(panel.getWidth()*3/4,panel.getHeight()/(MenuButtons.values().length+3)));
            panel.add(button);
            panel.add(Box.createGlue());
            panel.add(Box.createHorizontalStrut(panel.getHeight()/(MenuButtons.values().length+3)));
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
