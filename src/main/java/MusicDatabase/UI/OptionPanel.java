package MusicDatabase.UI;

import java.awt.*;

public class OptionPanel extends SectionPanel {
    private static OptionPanel optionPanelInstance;

    private OptionPanel() {
        super();
        panel.setBackground(optionsColor);
    }

    public static OptionPanel getInstance() {
        if(optionPanelInstance==null) {
            optionPanelInstance = new OptionPanel();
            return optionPanelInstance;
        }
        return optionPanelInstance;
    }

    @Override
    public void buildPanel(Dimension panelDimension) {

    }
}
