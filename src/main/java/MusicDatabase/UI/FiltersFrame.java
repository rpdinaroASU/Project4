package MusicDatabase.UI;

public class FiltersFrame {
    private static FiltersFrame instance;
    private FiltersFrame() {
        //super();
    }
    public static FiltersFrame getInstance(){
        if(instance == null){
            instance = new FiltersFrame();
        }
        return instance;
    }


}
