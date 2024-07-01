package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

public class MenuPanel {
    private static MenuPanel menuPanelInstance;
    private JPanel menuPanel;
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

    public static MenuPanel getInstance() {
        if(menuPanelInstance == null) {
            menuPanelInstance = new MenuPanel();
        }
        return menuPanelInstance;
    }

    public JPanel getMenuPanel() {
        return menuPanel;
    }
}
