package MusicDatabase.Utilities;

import MusicDatabase.UI.MainContainer;
import MusicDatabase.JDBC.MusicDatabaseConnector;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
    	
    	 System.out.println("Database URL: " + System.getProperty("db.url"));
         System.out.println("Database Username: " + System.getProperty("db.username"));
         System.out.println("Database Password: " + System.getProperty("db.password"));
         
        MainContainer.getInstance();
    }
}