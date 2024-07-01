package MusicDatabase.JDBC;
import MusicDatabase.UI.ContextPanel;

import java.sql.*;

public class MusicDatabaseConnector {
    private static String url = "jdbc:mysql://localhost:3306/D5";
    private static String username = "lioninn";
    private static String password = "Shamb00m!";

    public MusicDatabaseConnector() {

    }
    public static void ButtonPress(String TableName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stmt.executeQuery("SELECT * FROM ALBUM");
            ContextPanel.setContextTable(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        }
    }
}
