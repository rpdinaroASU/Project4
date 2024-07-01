package MusicDatabase.UI;


import javax.swing.*;
import java.awt.*;

public class MainContainer {
    private static MainContainer mainContainer;
    private JFrame frame;
    private MainContainer() {
        frame = new JFrame();
        frame.setVisible(true);
        frame.setName("Music Database");
        frame.setFocusable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.add(createMenuButtons());

        //TODO: CHANGE TO CLOSE ALL RESOURCES ON EXIT
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static MainContainer getInstance() {
        if(mainContainer==null) {
            mainContainer = new MainContainer();
            return mainContainer;
        }
        return mainContainer;
    }

    private JPanel createMenuButtons() {
        JPanel panel = new JPanel();
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
        return panel;
    }


}
