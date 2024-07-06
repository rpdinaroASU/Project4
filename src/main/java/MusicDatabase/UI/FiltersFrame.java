package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

public class FiltersFrame {
    private static FiltersFrame instance;
    private FiltersFrame() {
        JFrame frame = new JFrame("Filters");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(800, 600);

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.buildPanel(frame.getSize(),FilterButtons.values());

        frame.add(menuPanel.getPanel());

        frame.setVisible(true);
    }
    public static FiltersFrame getInstance(){
        if(instance == null){
            instance = new FiltersFrame();
        }
        return instance;
    }


}
