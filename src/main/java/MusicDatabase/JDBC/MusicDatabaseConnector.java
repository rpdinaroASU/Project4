package MusicDatabase.JDBC;
import MusicDatabase.UI.ButtonInterface;
import MusicDatabase.UI.ContextPanel;
import MusicDatabase.UI.MenuButtons;
import MusicDatabase.UI.OptionPanel;

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
            } else {
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
