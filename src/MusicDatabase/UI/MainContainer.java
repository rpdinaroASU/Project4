package MusicDatabase.UI;


import javax.swing.*;
import java.awt.*;

public class MainContainer {
    private static MainContainer mainContainer;

    private final JFrame frame;
    private MainContainer() {
        frame = new JFrame();

        frame.setVisible(true);
        frame.setName("Music Database");
        frame.setFocusable(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        Menu menu = new Menu();
        frame.add(menu.getMenuPanel(), BorderLayout.WEST);

        ContextPanel contextPanel = ContextPanel.getInstance();
        frame.add(contextPanel.getContextPanel());


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

}
