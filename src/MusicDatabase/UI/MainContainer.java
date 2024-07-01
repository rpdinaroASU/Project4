package MusicDatabase.UI;


public class MainContainer {
    private static MainContainer mainContainer;
    private MainContainer() {

    }

    public static MainContainer getInstance() {
        if(mainContainer==null) {
            mainContainer = new MainContainer();
            return mainContainer;
        }
        return mainContainer;
    }
}
