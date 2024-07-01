package MusicDatabase.UI;




import MusicDatabase.Utilities.ExitHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * This class is a Singleton of the UI, this ensures that only one instance of the UI can be called at once.
 * The Main Container is composed of three distinct sections, the MenuPanel to the left which allows
 * a User to select the context of their interaction with the database, the ContextPane in the center that
 * displays ResultSets according to user specification, Options above the Context pane which will
 * allow the user to do common actions, such as adding, deleting, updating, and applying filters.
 *
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class MainContainer {
    private static MainContainer mainContainer;

    /**
     * This method initializes the Singleton MainContainer, adding the three sections to the
     * container.
     */
    private MainContainer() {
        JFrame frame = new JFrame();
        final int MENU_WIDTH_DIVISOR = 5;

        frame.setVisible(true);
        frame.setName("Music Database");
        frame.setFocusable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        //Set Menu Dimensions and add to container
        int menuSectionWidth = frame.getWidth()/ MENU_WIDTH_DIVISOR;
        int menuSectionHeight = frame.getHeight();
        Dimension menuDimension = new Dimension(menuSectionWidth,menuSectionHeight);
        JPanel menuPanel =  MenuPanel.getInstance().getPanel();
        menuPanel.setPreferredSize(menuDimension);
        frame.add(menuPanel, BorderLayout.WEST);

        ContextPanel contextPanel = ContextPanel.getInstance();
        frame.add(contextPanel.getPanel());


        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ExitHandler.exitTriggered();
            }
        };
        frame.addWindowListener(exitListener);
    }

    /**
     * Singleton instance of the MainContainer
     * @return Returns the singleton instance
     */
    public static MainContainer getInstance() {
        if(mainContainer==null) {
            mainContainer = new MainContainer();
            return mainContainer;
        }
        return mainContainer;
    }

}
