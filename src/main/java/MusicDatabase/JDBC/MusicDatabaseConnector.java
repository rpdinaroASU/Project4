package MusicDatabase.JDBC;
import MusicDatabase.UI.ContextPanel;
import MusicDatabase.UI.MenuButtons;

import java.sql.*;

public class MusicDatabaseConnector {
    private static String url = "jdbc:mysql://localhost:3306/D5";
    private static String username = "lioninn";
    private static String password = "Shamb00m!";

    public static void buttonPress(MenuButtons button) {
        Connection conn;
        Statement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = switch (button) {
                case Songs -> ps.executeQuery("SELECT * FROM SONG");
                case Albums -> ps.executeQuery("SELECT * FROM ALBUM");
                case RecordLabels -> ps.executeQuery("SELECT * FROM RECORD_LABEL");
                case Genres -> ps.executeQuery("SELECT * FROM GENRE");
                case Contributors -> ps.executeQuery("SELECT * FROM CONTRIBUTOR");
                case Playlists -> ps.executeQuery("SELECT * FROM PLAYLIST");
                default -> ps.executeQuery("SELECT * FROM RECORD_LABEL");
            };

            ContextPanel.setContextTable(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        }
    }
}
