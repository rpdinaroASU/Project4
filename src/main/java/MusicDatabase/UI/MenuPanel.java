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
        super();
        panelColor = Color.GRAY;
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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


    @Override
    public void buildPanel(Dimension panelDimension) {
        //panel.setPreferredSize(panelDimension);
        //panel.setMaximumSize(panelDimension);

        final int panelWidth = (int) panelDimension.getWidth();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth*3/4, 56*MenuButtons.values().length));
        buttonPanel.setMaximumSize(new Dimension(panelWidth*3/4, 56*MenuButtons.values().length));
        Font buttonFont = new Font("Times New Roman", Font.BOLD, 20);

        for(MenuButtons menuButton : MenuButtons.values()) {
            JButton button = new JButton(menuButton.getButtonName());
            button.setFont(buttonFont);
            button.addActionListener( e -> {
                EventHandler.handleMenuButtonEvent(menuButton);
            });
            Dimension buttonSize = new Dimension(panelWidth*3/4, 50);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            buttonPanel.add(button);
        }

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());

        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelPanel.setMaximumSize(new Dimension(150,150));
        JLabel label = new JLabel("Menu");
        label.setFont(new Font("Algerian", Font.BOLD, 50));
        labelPanel.add(label);

        panel.add(labelPanel);
        panel.add(buttonPanel);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());

        labelPanel.setBackground(panelColor);
        buttonPanel.setBackground(panelColor);
        panel.setBackground(panelColor);
    }


}
