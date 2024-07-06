package MusicDatabase.UI;

import java.sql.*;
import java.util.Arrays;

import javax.swing.JOptionPane;

import MusicDatabase.JDBC.MusicDatabaseConnector;

/**
 * This class handles all interactions with the UI
 * @author Ryan Dinaro
 * @version 1.0.0
 */
public class EventHandler {


	/**
     * This method handles MenuPanel Button presses and switches the context panel accordingly
     * @param button the button pressed
     */
    public static void handleMenuButtonEvent(ButtonInterface button) {
        OptionPanel.notifyMenuButtonPress();
        MusicDatabaseConnector.menuButtonPress(button);
        OptionPanel.setCurrentMenu(button);
    }

    /**
     * This method handles option panel button presses and interacts with the database accordingly
     * @param optionButton the option button pressed
     */
    public static void handleOptionButtonEvent(ButtonInterface optionButton) {
        //TODO: HANDLE INSERTS (ADD), UPDATES(EDIT)
        if(optionButton == OptionButtons.Add) {

        }
        else if(optionButton == OptionButtons.Remove) {
            String pkSelection = OptionPanel.getComboBoxSelection();
            MenuButtons currentMenu = (MenuButtons) OptionPanel.getCurrentMenu();
            removeRecord(currentMenu, pkSelection);
        }
        else if(optionButton == OptionButtons.Edit) {
            String pkSelection = OptionPanel.getComboBoxSelection();
            //Retrieve a String representation of the primary keys of the table delimited by `,`
            MenuButtons currentMenu = (MenuButtons) OptionPanel.getCurrentMenu();
            MusicDatabaseConnector.editButtonPress(OptionButtons.Edit,currentMenu,pkSelection);
        }
        else if(optionButton == OptionButtons.Filter) {
            FiltersFrame.getInstance();
        }
    }

    
    /**
     * This method will remove the chosen PK associated with a table and refresh if successful.
     * If there are reference issues during delete then an exception popup will be given. 
     * @param currentMenu
     * @param pkSelection
     */
    private static void removeRecord(MenuButtons currentMenu, String pkSelection) {

        String tableName = "";
        String primaryKey = "";
        String joinTable = "";
        String deleteScript = "";
        
        switch (currentMenu) {
            case Songs -> {
                tableName = "SONG";
                primaryKey = "SongID";
                handleJoinDelete("song_contributor",primaryKey,pkSelection);
                handleJoinDelete("SONG_GENRE",primaryKey,pkSelection);
                handleJoinDelete("SONG_PLAYLIST",primaryKey,pkSelection);
            }
            case Albums -> {
             	deleteScript =  "-- Delete from song_contributor\n"
            	        + "DELETE sc\n"
            	        + "FROM song_contributor sc\n"
            	        + "INNER JOIN song s ON sc.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.albumid = ?;\n"
            	        + "\n"
            	        + "-- Delete from song_genre\n"
            	        + "DELETE sg\n"
            	        + "FROM song_genre sg\n"
            	        + "INNER JOIN song s ON sg.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.albumid = ?;\n"
            	        + "\n"
            	        + "-- Delete from song_playlist\n"
            	        + "DELETE sp\n"
            	        + "FROM song_playlist sp\n"
            	        + "INNER JOIN song s ON sp.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.albumid = ?;\n"
            	        + "\n"
            	        + "-- Delete from song\n"
            	        + "DELETE s\n"
            	        + "FROM song s\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.albumid = ?;\n"
            	        + "\n"
            	        + "-- Delete from record_label\n"
            	        + "DELETE FROM record_label WHERE LabelName = ?;\n"
            	        + "-- Delete from album\n"
            	        + "DELETE s\n"
            	        + "FROM album s\n"
            	        + "WHERE albumid = ?;\n"
            	        + "\n";
            }
            case RecordLabels -> {
            	deleteScript =  "-- Delete from song_contributor\n"
            	        + "DELETE sc\n"
            	        + "FROM song_contributor sc\n"
            	        + "INNER JOIN song s ON sc.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.LabelName = ?;\n"
            	        + "\n"
            	        + "-- Delete from song_genre\n"
            	        + "DELETE sg\n"
            	        + "FROM song_genre sg\n"
            	        + "INNER JOIN song s ON sg.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.LabelName = ?;\n"
            	        + "\n"
            	        + "-- Delete from song_playlist\n"
            	        + "DELETE sp\n"
            	        + "FROM song_playlist sp\n"
            	        + "INNER JOIN song s ON sp.SongID = s.SongID\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.LabelName = ?;\n"
            	        + "\n"
            	        + "-- Delete from song\n"
            	        + "DELETE s\n"
            	        + "FROM song s\n"
            	        + "INNER JOIN album a ON s.AlbumID = a.AlbumID\n"
            	        + "WHERE a.LabelName = ?;\n"
            	        + "\n"
            	        + "-- Delete from album\n"
            	        + "DELETE s\n"
            	        + "FROM album s\n"
            	        + "WHERE LabelName = ?;\n"
            	        + "\n"
            	        + "-- Delete from record_label\n"
            	        + "DELETE FROM record_label WHERE LabelName = ?;\n";
            	
                
            }
            case Genres -> {
                tableName = "GENRE";
                primaryKey = "GenreID";
                joinTable = "SONG_GENRE";
            }
            case Contributors -> {
                tableName = "CONTRIBUTOR";
                primaryKey = "ContributorID";
                joinTable = "song_contributor";
            }
            case Playlists -> {
                tableName = "PLAYLIST";
                primaryKey = "PLID";
                joinTable = "SONG_PLAYLIST";
            }
            default -> throw new IllegalStateException("Unexpected value: " + currentMenu);
        }

        
        if (joinTable != "") {
        	handleJoinDelete(joinTable,primaryKey,pkSelection);
        }

        if(tableName != "") {
        	handleBasicDelete(tableName, primaryKey, pkSelection, currentMenu);
        }
    
        if(deleteScript != "") {
        	handleAdvancedDelete(deleteScript,pkSelection,currentMenu);
        }
}
    
    /**
     * This handles the table deletes that require an advance delete script
     * @param deleteScript
     * @param pkSelection
     * @param currentMenu
     */
    private static void handleAdvancedDelete(String deleteScript, String pkSelection, MenuButtons currentMenu ) {

        String[] deleteStatements = deleteScript.split(";\n");

        try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword())) {

            for (String statement : deleteStatements) {

                if (statement.trim().isEmpty()) {
                    continue;
                }

                try (PreparedStatement pstmt = conn.prepareStatement(statement)) {

                    pstmt.setString(1, pkSelection);
                    int rowsAffected = pstmt.executeUpdate();

                    if (rowsAffected > 0) {
                        System.out.println("Record deleted successfully.");
                        OptionPanel.notifyMenuButtonPress();
                        MusicDatabaseConnector.menuButtonPress(currentMenu);

                    } 
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "ERROR : There are other references to this table. Delete the other references before deleting this.";
            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This will handle basic deletes. 
     * @param tableName
     * @param primaryKey
     * @param pkSelection
     * @param currentMenu
     */
	private static void handleBasicDelete(String tableName, String primaryKey, String pkSelection, MenuButtons currentMenu ) {
    
    String deleteSQL = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

    try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword());
         PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

        pstmt.setString(1, pkSelection);
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
        	
            System.out.println("Record deleted successfully.");
            OptionPanel.notifyMenuButtonPress();
            MusicDatabaseConnector.menuButtonPress(currentMenu);
            
        } else {
        	
            System.out.println("No record found with the provided primary key.");
            String message = "ERROR : Please select a record key in drop down.";
            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        e.printStackTrace();
        String message = "ERROR : There are other refrences to this table. Delete the other refrences before deleting this.";
        JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
    }
	}

    /**
     * This handles the deletes to referenced tables.
     * @param jonTable
     * @param primaryKey
     * @param pkSelection
     */
	private static void handleJoinDelete(String jonTable, String primaryKey, String pkSelection) {
		
		   String deleteSQL = "DELETE FROM " + jonTable + " WHERE " + primaryKey + " = ?";

	        try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword());
	             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

	            pstmt.setString(1, pkSelection);
	            int rowsAffected = pstmt.executeUpdate();
	            
	            if (rowsAffected > 0) {
	            	
	                System.out.println("Record deleted successfully.");
	                
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            String message = "ERROR :  Cannot delete Jon item. There are other references to this table. "
	            		+ "Delete the other refrences before deleting this.";
	            JOptionPane.showMessageDialog(null, message, "Please try again", JOptionPane.INFORMATION_MESSAGE);
	        }
		
	}

    /**
     * This method will update the chosen record after accepting an input from a JtextArea in a new frame
     * @param currentMenu
     * @param pkSelection
     */


}
