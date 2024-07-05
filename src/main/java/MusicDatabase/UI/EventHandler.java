package MusicDatabase.UI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
    public static void handleMenuButtonEvent(MenuButtons button) {
        OptionPanel.notifyMenuButtonPress();
        MusicDatabaseConnector.menuButtonPress(button);
        OptionPanel.setCurrentMenu(button);
    }

    /**
     * This method handles option panel button presses and interacts with the database accordingly
     * @param optionButton the option button pressed
     */
    public static void handleOptionButtonEvent(OptionButtons optionButton) {
        //TODO: HANDLE INSERTS (ADD), UPDATES(EDIT), DELETIONS(REMOVE), AND ADDITIONAL SELECTION OPTIONS(FILTER)
        if(optionButton == OptionButtons.Add) {

        }
        else if(optionButton == OptionButtons.Remove) {
        	
            String pkSelection = OptionPanel.getComboBoxSelection();
            MenuButtons currentMenu = OptionPanel.getCurrentMenu();
            removeRecord(currentMenu, pkSelection);
            
        }
        else if(optionButton == OptionButtons.Edit) {
            String pkSelection = OptionPanel.getComboBoxSelection();
            //Retrieve a String representation of the primary keys of the table delimited by `,`
        }
        else if(optionButton == OptionButtons.Filter) {

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

        switch (currentMenu) {
            case Songs -> {
                tableName = "SONG";
                primaryKey = "SongID";
            }
            case Albums -> {
                tableName = "ALBUM";
                primaryKey = "AlbumID";
            }
            case RecordLabels -> {
                tableName = "RECORD_LABEL";
                primaryKey = "LabelName";
            }
            case Genres -> {
                tableName = "GENRE";
                primaryKey = "GenreID";
            }
            case Contributors -> {
                tableName = "CONTRIBUTOR";
                primaryKey = "ContributorID";
            }
            case Playlists -> {
                tableName = "PLAYLIST";
                primaryKey = "PLID";
            }
            default -> throw new IllegalStateException("Unexpected value: " + currentMenu);
        }

        String deleteSQL = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

        try (Connection conn = DriverManager.getConnection(MusicDatabaseConnector.getUrl(), MusicDatabaseConnector.getUsername(), MusicDatabaseConnector.getPassword());
             PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {

            pstmt.setString(1, pkSelection);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
            	
                System.out.println("Record deleted successfully.");
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


}
