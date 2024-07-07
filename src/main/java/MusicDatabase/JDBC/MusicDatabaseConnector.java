package MusicDatabase.JDBC;
import MusicDatabase.UI.*;

import javax.print.attribute.DateTimeSyntax;
import javax.swing.*;
import java.sql.*;
import java.time.format.DateTimeFormatter;
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

    /**
     * This method will select the tuple values, their associated data types, and status as NOT NULL and/or a primary
     * key of the table; it will append this information to a string and then split it for use in the EditFrame.
     * @param pKey
     * @param pkSelection
     * @param tableName
     * @return String[]
     */
    public static String[] editQuerySelectExecute(String tableName,String pKey,String pkSelection){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rsSEL = null,rsCOL = null;

        if (Objects.equals(pkSelection, "")) {
            return null;
        }

        String query1 = "SELECT * FROM " + tableName + "WHERE " + pKey + " = " + pkSelection + ";";
        String query2 = "SHOW COLUMNS FROM " + tableName + ";";
        StringBuilder sb = new StringBuilder();
        String resultString;
        String[] tupleString = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            rsSEL = stmt.executeQuery(query1);
            rsCOL = stmt.executeQuery(query2);

            int i = 1;
            while(rsCOL.next()) {
                rsSEL.next();
                sb.append(rsCOL.getString(1)).append(", ").append(rsCOL.getString(2));
                if(rsCOL.getString(3).equalsIgnoreCase("NO")) sb.append("(NOT NULL)");
                if(rsCOL.getString(4).equalsIgnoreCase("PRI")) sb.append("(Primary Key)");
                if(rsCOL.getObject(5)!= null) sb.append(", '").append(rsCOL.getObject(5))
                        .append("'");
                sb.append(", ").append(rsSEL.getObject(i)).append(":\n");
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (ClassNotFoundException var7) {
            System.err.println("Driver not found");
        }

        if(!sb.isEmpty()) {
            String sbStr = sb.toString();
            tupleString = sbStr.split("\n");
        }
        return tupleString;
    }
    public static void executeUpdateQuery(String[] updateVals, MenuButtons currentMenu,String pkSelection) {
        StringBuilder uQueryA,uQueryB;
        String tableName,primaryKey,fQuery;
        String[] tableCols;

        Connection conn = null;
        PreparedStatement pstmt = null;


        uQueryA = new StringBuilder("UPDATE ? SET\n");
        uQueryB = new StringBuilder("WHERE ? = ?;");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);

            switch (currentMenu) {
                case Songs -> {
                    tableName = TableNames.Song.getName();
                    tableCols = TableNames.Song.getElements();
                    primaryKey = TableNames.Song.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setDate(3, Date.valueOf(updateVals[1]));
                    pstmt.setString(4, tableCols[2]);
                    pstmt.setString(5, updateVals[2]);
                    pstmt.setString(6, tableCols[3]);
                    pstmt.setString(7, updateVals[3]);
                    pstmt.setString(8, tableCols[0]);
                    pstmt.setString(9, updateVals[0]);

                }
                case Albums -> {
                    tableName = TableNames.Album.getName();
                    tableCols = TableNames.Album.getElements();
                    primaryKey = TableNames.Album.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setString(3, updateVals[1]);
                    pstmt.setString(4, tableCols[2]);
                    pstmt.setString(5, updateVals[2]);
                    pstmt.setString(8, tableCols[0]);
                    pstmt.setString(9, updateVals[0]);
                }
                case RecordLabels -> {
                    tableName = TableNames.Record_Label.getName();
                    tableCols = TableNames.Record_Label.getElements();
                    primaryKey = TableNames.Record_Label.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setString(3, updateVals[1]);
                    pstmt.setString(4, tableCols[2]);
                    pstmt.setString(5, updateVals[2]);
                    pstmt.setString(6, tableCols[0]);
                    pstmt.setString(7, updateVals[0]);
                }
                case Genres -> {
                    tableName = TableNames.Genre.getName();
                    tableCols = TableNames.Genre.getElements();
                    primaryKey = TableNames.Genre.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setString(3, updateVals[1]);
                    pstmt.setString(6, tableCols[0]);
                    pstmt.setString(7, updateVals[0]);
                }
                case Contributors -> {
                    tableName = TableNames.Contributor.getName();
                    tableCols = TableNames.Contributor.getElements();
                    primaryKey = TableNames.Contributor.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setString(3, updateVals[1]);
                    pstmt.setString(4, tableCols[2]);
                    pstmt.setString(5, updateVals[2]);
                    pstmt.setString(6, tableCols[3]);
                    pstmt.setDate(7, Date.valueOf(updateVals[3]));
                    pstmt.setString(8, tableCols[0]);
                    pstmt.setString(9, updateVals[0]);
                }
                case Playlists -> {
                    tableName = TableNames.Playlist.getName();
                    tableCols = TableNames.Playlist.getElements();
                    primaryKey = TableNames.Playlist.getpK();
                    int i = 2;
                    uQueryA.append("? = ?");
                    while (i < tableCols.length) {
                        uQueryA.append(",").append("? = ?");
                        i++;
                    }
                    uQueryA.append("\n").append(uQueryB);
                    fQuery = uQueryA.toString();
                    pstmt = conn.prepareStatement(fQuery);
                    pstmt.setString(1, tableName);
                    pstmt.setString(2, tableCols[1]);
                    pstmt.setString(3, updateVals[1]);
                    pstmt.setString(4, tableCols[2]);
                    pstmt.setString(5, updateVals[2]);
                    pstmt.setString(6, tableCols[3]);
                    pstmt.setDate(7, Date.valueOf(updateVals[3]));
                    pstmt.setString(8, tableCols[0]);
                    pstmt.setString(9, updateVals[0]);
                }
                default -> throw new IllegalStateException("Unexpected value: " + currentMenu);
            }


            int updateBinary = pstmt.executeUpdate();
            if(updateBinary != 0){
                System.out.println("Record Updated successfully.");
                OptionPanel.notifyMenuButtonPress();
                MusicDatabaseConnector.menuButtonPress(currentMenu);
            }



        }catch(SQLException | ClassNotFoundException sexc){
            sexc.printStackTrace();
            throw new RuntimeException();
        }finally{
            System.out.println("Closing edit connection resources...");
            try{
                if(pstmt != null) pstmt.close();
                if(conn != null) conn.close();
                System.out.println("Finished.");
            } catch(Exception e){ System.out.println(e.getMessage()); }
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
