package MusicDatabase.JDBC;
import MusicDatabase.UI.ContextPanel;

import java.sql.*;

public class MusicDatabaseConnector {
    private static String url = "jdbc:mysql://localhost:3306/D5";
    private static String username = "lioninn";
    private static String password = "Shamb00m!";

    public static void ButtonPress(String tableName) {
        Connection conn;
        ResultSet rs;
        Statement ps;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            ps = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = ps.executeQuery("SELECT * FROM ALBUM");
            ContextPanel.setContextTable(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver not found");
        }
    }
}
