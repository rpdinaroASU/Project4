package MusicDatabase.UI;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This class holds the database manipulation tools
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class OptionPanel extends SectionPanel {
    private static OptionPanel optionPanelInstance;
    private static JComboBox<String> comboBox;
    private static boolean built = false;
    private static MenuButtons currentMenu;
    /**
     * This constructor initializes the panel and sets styling and layout
     */
    private OptionPanel() {
        super();
        panel.setBackground(optionsColor);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    }

    /**
     * Returns and initializes a singleton instance of the option panel
     * @return singleton instance of option panel
     */
    public static OptionPanel getInstance() {
        if(optionPanelInstance==null) {
            optionPanelInstance = new OptionPanel();
            return optionPanelInstance;
        }
        return optionPanelInstance;
    }

    /**
     * Returns a string representation of the primary keys of the selected tuple
     * @return string representation of the primary keys of the selected tuple in the ComboBox
     */
    public static String getComboBoxSelection() {
        return Objects.requireNonNull(comboBox.getSelectedItem()).toString();
    }

    
    /**
     * Notifies the option menu a new menu option has been selected
     */
    public static void notifyMenuButtonPress() {
        comboBox.removeAllItems();
    }

    /**
     * Builds the option pane panel
     * @param panelDimension dimensions of the panel
     */
    @Override
    public void buildPanel(Dimension panelDimension, ButtonInterface[] buttonInterfaces) {
        if(!built) {
            comboBox = new JComboBox<String>();
            comboBox.setPreferredSize(new Dimension(panelDimension.width / 2, panelDimension.height / 5));
            comboBox.setMaximumSize(new Dimension(panelDimension.width / 2, panelDimension.height / 5));
            comboBox.setFont(entryFont);

            panel.add(Box.createHorizontalGlue());
            panel.add(Box.createHorizontalStrut(panelDimension.height / 5));
            panel.add(comboBox);

            final int buttonSpacerDivisor = 10;
            panel.add(Box.createHorizontalStrut(panelDimension.height / buttonSpacerDivisor));

            for (ButtonInterface optionButtons : buttonInterfaces) {
                JButton button = new JButton(optionButtons.getName());
                button.setPreferredSize(new Dimension(panelDimension.width / 8, panelDimension.height / 5));
                button.addActionListener(e -> {
                    EventHandler.handleOptionButtonEvent(optionButtons);
                });
                button.setFont(buttonFont);
                panel.add(button);
                panel.add(Box.createHorizontalStrut(panelDimension.height / buttonSpacerDivisor));
            }
            panel.add(Box.createHorizontalGlue());
            built = true;
        }
    }

    /**
     * sets optionPane combobox to results set passed to it
     * @param rs result set of primary keys
     * @throws SQLException pointer errors
     */
    public static void setComboBox(ResultSet rs) throws SQLException {
        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        while(rs.next()) {
            StringBuilder primaryKeyString = new StringBuilder();
            for(int i = 0; i < resultSetMetaData.getColumnCount(); i++){
                primaryKeyString.append(rs.getString(i + 1));
                if(i != resultSetMetaData.getColumnCount() - 1)
                    primaryKeyString.append(", ");
            }
            comboBox.addItem(primaryKeyString.toString());
        }
    }

    /**
     * Sets the menu button pressed to be used for remove switch. 
     * @param button
     */
	public static void setCurrentMenu(MenuButtons button) {
		 currentMenu = button;
		
	}

	/**
	 * Returns the current menu selection.
	 * @return
	 */
    public static MenuButtons getCurrentMenu() {
        return currentMenu;
    }
}
