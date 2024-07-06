package MusicDatabase.UI;


import java.awt.BorderLayout;
import javax.swing.JFrame;

public class FiltersFrame {
    private static FiltersFrame instance;
    private JFrame frame = new JFrame("Filters");

    private FiltersFrame() {
        this.frame.setDefaultCloseOperation(2);
        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);
        this.frame.setSize(400, 300);
        MenuPanel menuPanel = new MenuPanel();
        menuPanel.buildPanel(this.frame.getSize(), FilterButtons.values());
        this.frame.add(menuPanel.getPanel(), "Center");
        this.frame.setVisible(true);
    }

    public static FiltersFrame getInstance() {
        if (instance == null) {
            instance = new FiltersFrame();
        } else {
            instance.frame.setVisible(true);
        }

        return instance;
    }
}