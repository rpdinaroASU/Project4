package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;
/**
 * @author Zac Barlow
 * @version 1.0.0
 */
public class EditFrame {
    private static EditFrame instance;
    static String jText;
    private EditFrame(String[] tupleString) {
        JFrame frame = new JFrame("Edit Options");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(600, 600);

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.buildPanel(frame.getSize(),FilterButtons.values());

        StringBuilder tString = new StringBuilder();
        int i  = 0;
        while(i< tupleString.length){
            if(i>0){
                tString.append(",");}
            tString.append(tupleString[i]);
            i++;
        }
        jText = tString.toString();
        JTextField textField = new JTextField(jText);
        JTextArea tArea = new JTextArea("Edit the values you wish to change\n*WARNING: Do NOT delete \',\' characters*");
        frame.add(menuPanel.getPanel());
        frame.add(tArea);
        frame.setVisible(true);

    }
    public static EditFrame getInstance(String[] tupleString){
        if(instance == null){
            instance = new EditFrame(tupleString);
        }
        return instance;
    }
    public static String[] getjText(){
    String[] textj = jText.split(",");
        return textj;
    }

}