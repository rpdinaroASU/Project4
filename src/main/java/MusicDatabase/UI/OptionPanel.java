package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;

public class OptionPanel extends SectionPanel {
    private static OptionPanel optionPanelInstance;

    private OptionPanel() {
        super();
        panel.setBackground(optionsColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    public static OptionPanel getInstance() {
        if(optionPanelInstance==null) {
            optionPanelInstance = new OptionPanel();
            return optionPanelInstance;
        }
        return optionPanelInstance;
    }

    @Override
    public void buildPanel(Dimension panelDimension) {
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setPreferredSize(new Dimension(panelDimension.width/2, panelDimension.height/5));
        comboBox.setMaximumSize(new Dimension(panelDimension.width/2, panelDimension.height/5));
        comboBox.addActionListener(e -> {
            EventHandler.handleComboBoxSelect(e.getSource());
        });
        String[] buttonText = {"Add", "Remove", "Edit", "Filter"};

        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalStrut(panelDimension.height/5));
        panel.add(comboBox);

        final int buttonSpacerDivisor = 10;
        panel.add(Box.createHorizontalStrut(panelDimension.height/buttonSpacerDivisor));

        for(String s: buttonText) {
            JButton button = new JButton(s);
            button.setPreferredSize(new Dimension(panelDimension.width/8, panelDimension.height/5));
            button.addActionListener(e -> {
                EventHandler.handleOptionButtonEvent(s);
            });
            button.setFont(buttonFont);
            panel.add(button);
            panel.add(Box.createHorizontalStrut(panelDimension.height/buttonSpacerDivisor));
        }
        panel.add(Box.createHorizontalGlue());




    }
}
