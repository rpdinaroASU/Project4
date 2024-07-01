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
    private static ResultSet resultSet;

    /**
     * Singleton of the context panel
     */
    private ContextPanel() {
        super();
        panel.setBackground(mainColor);
        panel.setLayout(new BorderLayout());

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

    public static ResultSet getResultSet() {
        return resultSet;
    }

    public static void setContextTable(ResultSet resultSet) {
        System.out.println("Made it this far");
    }

    @Override
    public void buildPanel(Dimension panelDimension) {
        if(contextTable==null) {
            contextTable = new JTable();
            contextTable.setPreferredScrollableViewportSize(panelDimension);

            JScrollPane scrollPane = new JScrollPane(contextTable);
            scrollPane.setBackground(mainColor);
            scrollPane.setPreferredSize(new Dimension(panelDimension.width*3/4, panelDimension.height*3/4));

            panel.setBackground(mainColor);

            panel.setPreferredSize(panelDimension);
            panel.add(scrollPane,BorderLayout.CENTER);
            panel.add(Box.createVerticalStrut(panelDimension.height/8),BorderLayout.NORTH);
            panel.add(Box.createVerticalStrut(panelDimension.height/8),BorderLayout.SOUTH);
            panel.add(Box.createHorizontalStrut(panelDimension.width/16),BorderLayout.EAST);
            panel.add(Box.createHorizontalStrut(panelDimension.width/16),BorderLayout.WEST);
        }
    }
}
