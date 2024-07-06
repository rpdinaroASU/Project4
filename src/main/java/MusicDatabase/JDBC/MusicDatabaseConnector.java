package MusicDatabase.JDBC;
import MusicDatabase.UI.*;

import javax.swing.*;
import java.sql.*;
import java.util.Objects;

/**
 * Database connector, handles interactions between the UI and database
 * @author Ryan Dinaro
 * @verion 1.0.0
 */
public class MusicDatabaseConnector {
    private static String url = System.getProperty("db.url");
    private static String username = System.getProperty("db.username");
    private static String password = System.getProperty("db.password");

    /**
     * generates ResultSets for responses to menu button presses
     * @param button the menu button pressed
     */
    public static void menuButtonPress(ButtonInterface button) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        ResultSet primaryKeys = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            if (button.equals(MenuButtons.Songs)) {
                rs = statement.executeQuery("SELECT * FROM SONG");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT SongID FROM SONG");
                OptionPanel.setComboBox(primaryKeys);
            } else if (button.equals(MenuButtons.Albums)) {
                rs = statement.executeQuery("SELECT * FROM ALBUM");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT AlbumID FROM ALBUM");
                OptionPanel.setComboBox(primaryKeys);
            } else if (button.equals(MenuButtons.RecordLabels)) {
                rs = statement.executeQuery("SELECT * FROM RECORD_LABEL");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT LabelName FROM RECORD_LABEL");
                OptionPanel.setComboBox(primaryKeys);
            } else if (button.equals(MenuButtons.Genres)) {
                rs = statement.executeQuery("SELECT * FROM GENRE");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT GenreID FROM GENRE");
                OptionPanel.setComboBox(primaryKeys);
            } else if (button.equals(MenuButtons.Contributors)) {
                rs = statement.executeQuery("SELECT * FROM CONTRIBUTOR");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT ContributorID FROM CONTRIBUTOR");
                OptionPanel.setComboBox(primaryKeys);
            } else if (button.equals(MenuButtons.Playlists)) {
                rs = statement.executeQuery("SELECT * FROM PLAYLIST");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT PLID FROM PLAYLIST");
                OptionPanel.setComboBox(primaryKeys);
            }else {
                rs = statement.executeQuery("SELECT * FROM SONG_CONTRIBUTOR");
                ContextPanel.setContextTable(rs);
                primaryKeys = statement.executeQuery("SELECT * FROM SONG_CONTRIBUTOR");
                OptionPanel.setComboBox(primaryKeys);
            }
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        } finally {
            try {
                Objects.requireNonNull(rs).close();
                Objects.requireNonNull(primaryKeys).close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                System.err.println("Issue closing resources");
            }

        }
    }
    public static ResultSet executeFilterQuery(String query, String filterValue) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, filterValue);
            rs = pstmt.executeQuery();
        } catch (SQLException var6) {
            var6.printStackTrace();
            throw new RuntimeException(var6);
        } catch (ClassNotFoundException var7) {
            System.err.println("Driver not found");
        }

        return rs;
    }
    public static void editButtonPress(ButtonInterface button, MenuButtons currentMenu, String pkSelection) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        ResultSet primaryKeys = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            if (button.equals(OptionButtons.Edit)) {
                EditFrame.getInstance(editRecordSetup(currentMenu,pkSelection));
                editSubmitPress(EditButtons.SUBMIT_EDIT);
            }
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        } finally {
            try {
                Objects.requireNonNull(rs).close();
                Objects.requireNonNull(primaryKeys).close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                System.err.println("Issue closing resources");
            }

        }
    }
    static String tablename, primarykey, pkselection;
    static MenuButtons currentmenu;
    static String[] colnames, updatestring;
    public static void editSubmitPress(ButtonInterface button) {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;
        ResultSet primaryKeys = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            statement = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            if (button.equals(EditButtons.SUBMIT_EDIT)) {

                handleEdit(tablename,primarykey,pkselection,currentmenu,colnames,EditFrame.getjText());
            }
            ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        } finally {
            try {
                Objects.requireNonNull(rs).close();
                Objects.requireNonNull(primaryKeys).close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                System.err.println("Issue closing resources");
            }

        }
    }
    private static String[] editRecordSetup(MenuButtons currentMenu, String pkSelection){
        String tableName = "";
        String primaryKey = "";
        String[] colNames = null;

        switch (currentMenu) {
            case Songs -> {
                tableName = "SONG";
                primaryKey = "SongID";
                colNames= new String[]{"Title"};
            }
            case Albums -> {
                tableName = "ALBUM";
                primaryKey = "AlbumID";
                colNames= new String[]{"AlbumName"};
            }
            case RecordLabels -> {
                tableName = "RECORD_LABEL";
                primaryKey = "Label_ID";
                colNames= new String[]{};
            }
            case Genres -> {
                tableName = "GENRE";
                primaryKey = "GenreID";
                colNames= new String[]{"GenreName"};
            }
            case Contributors -> {
                tableName = "CONTRIBUTOR";
                primaryKey = "ContributorID";
                colNames= new String[]{"FirstName", "LastName"};
            }
            case Playlists -> {
                tableName = "PLAYLIST";
                primaryKey = "PLID";
                colNames= new String[]{"PlaylistName", "CreatorName"};
            }
            default -> throw new IllegalStateException("Unexpected value: " + currentMenu);
        }

        String[] tupleString = getTupleString(tableName, pkSelection, colNames, primaryKey);

        tablename = tableName;
        primarykey = primarykey;
        pkselection = pkSelection;
        currentmenu = currentMenu;
        colnames = colNames;
        return tupleString;
    }

    private static String[] getTupleString(String tableName, String pkSelection, String[] colNames, String primarykey) {
        String[] sa = new String[colNames.length];
        StringBuilder selectSB = new StringBuilder();
        String selectSQL = "";

        int i = 0;
        selectSB.append("SELECT ");
        while(i< colNames.length){
            if(i>0){selectSB.append(",");}
            selectSB.append(colNames[i]);
            i++;
        }
        selectSB.append(" FROM ").append(tableName);
        selectSB.append(" WHERE ").append(primarykey).append(" = ").append(pkSelection).append(";");
        selectSQL = selectSB.toString();

        try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword());
             Statement pstmt = conn.createStatement()){

            ResultSet rs = pstmt.executeQuery(selectSQL);
            int j= 0;
            while(rs.next()){
                sa[j] = rs.getString(j);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "failed to retrieve tuple data";
            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
        }
        return sa;
    }

    private static void handleEdit(String tableName, String primaryKey, String pkSelection, MenuButtons currentMenu,
                                   String[] colNames, String[] updateString){
        if(pkSelection == ""){
            System.out.println("No record found with the provided primary key.");
            String message = "ERROR : Please select a record key in drop down.";
            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int i = 0;
        StringBuilder editSQLsb = new StringBuilder("UPDATE " + tableName + " SET ");
        while(i < colNames.length){
            if(i>0){editSQLsb.append(",");}
            editSQLsb.append(colNames[i]).append(" = ").append(updateString[i]);
            i++;
        }
        editSQLsb.append(" WHERE ").append(primaryKey).append(" = ").append(pkSelection).append(";");
        String editSQL = editSQLsb.toString();

        try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword());
             Statement stmt = conn.createStatement()){

            int rowUpdated = stmt.executeUpdate(editSQL);

            if (rowUpdated > 0) {
                System.out.println("Record Updated successfully.");
                OptionPanel.notifyMenuButtonPress();
                MusicDatabaseConnector.menuButtonPress(currentMenu);

            } else {
                System.out.println("No record found with the provided primary key.");
                String message = "ERROR : Please select a record key in drop down.";
                JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "ERROR : There are other references to this table.";
            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
        }
    }

	public static String getUrl() {
        return url;

	}

	public static String getUsername() {
        return username;
	}

	public static String getPassword() {
        return password;
	}

}
