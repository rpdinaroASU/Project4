package MusicDatabase.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class is the context panel, the displayed values of the context panel are the returns of
 * the JDBC
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class ContextPanel extends SectionPanel {
    private static ContextPanel contextPanelInstance;
    private static JTable contextTable;
    private static Dimension contextTableSize;
    private static JScrollPane scrollPane;
    private static ResultSet resultSet;
    private static JPanel contextPanel;
    private static Font eFont;

    /**
     * Singleton of the context panel
     */
    private ContextPanel() {
        super();
        panel.setBackground(mainColor);
        panel.setLayout(new BorderLayout());
        eFont = entryFont;

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

    public static void setContextTable(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columns = resultSetMetaData.getColumnCount();
        String[] columnNames = new String[columns];
        for (int i = 0; i < columns; i++) {
            columnNames[i] = resultSetMetaData.getColumnName(i + 1);
        }
        resultSet.last();
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        resultSet.beforeFirst();
        contextTable = new JTable(tableModel);
        while (resultSet.next()) {
            Object[] row = new Object[columns];
            for (int i = 0; i < columns; i++) {
                row[i] = resultSet.getObject(i + 1);
            }
            tableModel.addRow(row);
        }
        contextTable.getTableHeader().setFont(new Font("Times new Roman", Font.BOLD, 25));
        contextTable.setPreferredScrollableViewportSize(new Dimension(contextTableSize.width*3/4, contextTableSize.height*3/4));
        contextTable.updateUI();
        contextTable.setFont(eFont);

        final int rowPadding = 20;
        int rowHeight = contextPanel.getFontMetrics(eFont).getHeight() + rowPadding;
        contextTable.setRowHeight(rowHeight);

        if (scrollPane != null) {
            scrollPane.setViewportView(contextTable);
        }
    }

    @Override
    public void buildPanel(Dimension panelDimension) {
        if(contextTable==null) {
            contextTable = new JTable();
            contextTableSize = panelDimension;
            contextTable.setPreferredScrollableViewportSize(new Dimension(panelDimension.width*3/4, panelDimension.height*3/4));

            scrollPane = new JScrollPane(contextTable);
            scrollPane.setBackground(mainColor);
            scrollPane.setPreferredSize(new Dimension(panelDimension.width*3/4, panelDimension.height*3/4));
            panel.setBackground(mainColor);

            panel.setPreferredSize(panelDimension);
            panel.add(scrollPane,BorderLayout.CENTER);
            int structSize = panelDimension.height/8;
            panel.add(Box.createVerticalStrut(structSize),BorderLayout.NORTH);
            panel.add(Box.createVerticalStrut(structSize),BorderLayout.SOUTH);
            panel.add(Box.createHorizontalStrut(structSize),BorderLayout.EAST);
            panel.add(Box.createHorizontalStrut(structSize),BorderLayout.WEST);
            contextPanel = panel;
        }
    }
}
