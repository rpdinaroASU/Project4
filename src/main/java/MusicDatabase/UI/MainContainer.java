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

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setName("Music Database");
        frame.setFocusable(true);
        frame.setMinimumSize(new Dimension(1850,1000));

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));


        //Set Menu Dimensions and add to container
        int menuSectionWidth = frame.getWidth()/ MENU_WIDTH_DIVISOR;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenHeight = (int) toolkit.getScreenSize().getHeight();
        int menuSectionHeight = frame.getHeight();
        Dimension menuDimension = new Dimension(menuSectionWidth,screenHeight);
        MenuPanel.getInstance().buildPanel(menuDimension);

        contentPane.add(MenuPanel.getInstance().getPanel());
        contentPane.add(Box.createHorizontalGlue());

        //ContextPanel contextPanel = ContextPanel.getInstance();
        //frame.add(contextPanel.getPanel());


        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                ExitHandler.exitTriggered();
            }
        };
        frame.addWindowListener(exitListener);
        contentPane.grabFocus();
        frame.add(contentPane);
        frame.setVisible(true);
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
