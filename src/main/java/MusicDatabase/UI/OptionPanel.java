package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

public class OptionPanel extends SectionPanel {
    private static OptionPanel optionPanelInstance;
    private static JComboBox<String> comboBox;

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

    public static String getComboBoxSelection() {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString();
    }

    public static void notifyMenuButtonPress() {
        comboBox.removeAllItems();
    }

    @Override
    public void buildPanel(Dimension panelDimension) {
        comboBox = new JComboBox<String>();
        comboBox.setPreferredSize(new Dimension(panelDimension.width/2, panelDimension.height/5));
        comboBox.setMaximumSize(new Dimension(panelDimension.width/2, panelDimension.height/5));
        comboBox.setFont(entryFont);

        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalStrut(panelDimension.height/5));
        panel.add(comboBox);

        final int buttonSpacerDivisor = 10;
        panel.add(Box.createHorizontalStrut(panelDimension.height/buttonSpacerDivisor));

        for(OptionButtons optionButtons: OptionButtons.values()) {
            JButton button = new JButton(optionButtons.getButtonName());
            button.setPreferredSize(new Dimension(panelDimension.width/8, panelDimension.height/5));
            button.addActionListener(e -> {
                EventHandler.handleOptionButtonEvent(optionButtons);
            });
            button.setFont(buttonFont);
            panel.add(button);
            panel.add(Box.createHorizontalStrut(panelDimension.height/buttonSpacerDivisor));
        }
        panel.add(Box.createHorizontalGlue());

    }

    public static void setComboBox(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        while(rs.next()) {
            StringBuilder primaryKeyString = new StringBuilder();
            for(int i = 0; i < rsmd.getColumnCount(); i++){
                primaryKeyString.append(rs.getString(i + 1));
                if(i != rsmd.getColumnCount() - 1)
                    primaryKeyString.append(", ");
            }
            comboBox.addItem(primaryKeyString.toString());
        }
    }
}
