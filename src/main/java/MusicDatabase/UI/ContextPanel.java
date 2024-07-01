package MusicDatabase.UI;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;

/**
 * This class is the context panel, the displayed values of the context panel are the returns of
 * the JDBC
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class ContextPanel extends SectionPanel {
    private static ContextPanel contextPanelInstance;
    private static JTable contextTable;

    /**
     * Singleton of the context panel
     */
    private ContextPanel() {
        super();
        panel.setBackground(mainColor);
        //TODO: CREATE CONTEXT PANEL
    }

    /**
     * Initializes and returns the singleton instance of the context panel
     * @return singleton instance of the context panel
     */
    public static ContextPanel getInstance() {
        if(contextPanelInstance==null) {
            contextPanelInstance = new ContextPanel();
            return contextPanelInstance;
        }
        return contextPanelInstance;
    }

    public static JTable getContextTable() {
        return contextTable;
    }

    public static void setContextTable(ResultSet resultSet) {

    }

    @Override
    public void buildPanel(Dimension panelDimension) {
        if(contextTable==null) {
            contextTable = new JTable();
            contextTable.setPreferredScrollableViewportSize(panelDimension);

            JScrollPane scrollPane = new JScrollPane(contextTable);
            scrollPane.setBackground(mainColor);
            contextTable.setBackground(mainColor);
            panel.setBackground(mainColor);

            panel.setPreferredSize(panelDimension);
            panel.add(scrollPane);
        }
    }
}
