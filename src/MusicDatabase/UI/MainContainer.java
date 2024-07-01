package MusicDatabase.UI;


import javax.swing.*;
import java.awt.*;

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

        frame.setVisible(true);
        frame.setName("Music Database");
        frame.setFocusable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.add(MenuPanel.getInstance().getMenuPanel(), BorderLayout.WEST);

        ContextPanel contextPanel = ContextPanel.getInstance();
        frame.add(contextPanel.getContextPanel());


        //TODO: CHANGE TO CLOSE ALL RESOURCES ON EXIT
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
