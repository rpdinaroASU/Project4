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
    public MenuPanel() {
        super();
        panel.setLayout(null);
        panel.setBackground(menuColor);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    }


    /**
     * Constructs the panel when called after initialization
     * @param panelDimension dimensions of the panel
     */
    @Override
    public void buildPanel(Dimension panelDimension, ButtonInterface[] buttonGroup) {
        final int panelWidth = (int) panelDimension.getWidth();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(panelWidth * 3 / 4, 56 * MenuButtons.values().length));
        buttonPanel.setMaximumSize(new Dimension(panelWidth * 3 / 4, 56 * MenuButtons.values().length));


        for (ButtonInterface menuButton : buttonGroup) {
            JButton button = new JButton(menuButton.getName());
            button.setFont(buttonFont);
            button.addActionListener(e -> {
                EventHandler.handleMenuButtonEvent(menuButton);
            });
            Dimension buttonSize = new Dimension(panelWidth * 3 / 4, 50);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            buttonPanel.add(button);
        }

        panel.add(Box.createHorizontalGlue());

//        JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        labelPanel.setMaximumSize(new Dimension(150, 150));
//        JLabel label = new JLabel("Menu");
//        label.setFont(headerFont);
//        labelPanel.add(label);
//
//        panel.add(labelPanel);
        panel.add(buttonPanel);

        panel.add(Box.createVerticalGlue());
        panel.add(Box.createHorizontalGlue());

//        labelPanel.setBackground(menuColor);
        buttonPanel.setBackground(menuColor);
    }


}
