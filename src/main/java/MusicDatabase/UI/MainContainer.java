package MusicDatabase.UI;




import MusicDatabase.Utilities.ExitHandler;
import MusicDatabase.JDBC.MusicDatabaseConnector;

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
        final int MENU_WIDTH_DIVISOR = 4;

        frame.setTitle("Music Database");
        frame.setFocusable(true);
        frame.setMinimumSize(new Dimension(1850,1000));


        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));


        //TODO:CODE SPAGHETTI
        //Set Menu Dimensions and add to container
        int menuSectionWidth = frame.getWidth()/ MENU_WIDTH_DIVISOR;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int screenHeight = (int) toolkit.getScreenSize().getHeight();
        Dimension menuDimension = new Dimension(menuSectionWidth,screenHeight);
        MenuPanel.getInstance().buildPanel(menuDimension);
        MenuPanel menuPanel = MenuPanel.getInstance();
        menuPanel.getPanel().setPreferredSize(menuDimension);
        contentPane.add(MenuPanel.getInstance().getPanel());
        //END TODO

        JPanel contextOptionPanel = new JPanel();
        contextOptionPanel.setLayout(new BoxLayout(contextOptionPanel, BoxLayout.Y_AXIS));
        contextOptionPanel.setPreferredSize(new Dimension(frame.getWidth()*(MENU_WIDTH_DIVISOR-1)/MENU_WIDTH_DIVISOR,frame.getHeight()));


        OptionPanel optionPanel = OptionPanel.getInstance();
        optionPanel.getPanel().setPreferredSize(
                new Dimension(frame.getWidth(),frame.getHeight()/4));

        ContextPanel contextPanel = ContextPanel.getInstance();
        contextPanel.getPanel().setPreferredSize(
                new Dimension(frame.getWidth(),frame.getHeight()*3/4));
        contextPanel.buildPanel(contextPanel.getPanel().getPreferredSize());

        contextOptionPanel.add(optionPanel.getPanel());
        contextOptionPanel.add(contextPanel.getPanel());

        contextOptionPanel.setVisible(true);
        contentPane.add(contextOptionPanel);

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


        MusicDatabaseConnector.buttonPress(MenuButtons.Albums);
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
