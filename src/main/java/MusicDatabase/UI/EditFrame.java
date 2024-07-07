package MusicDatabase.UI;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Zac Barlow
 * @version 1.0.0
 */
public class EditFrame {
    private static EditFrame instance;
    private static JFormattedTextField[] fieldArray;
    static String[] UpdateText;
    private EditFrame(String[] tupleString) {
        JFrame frame = new JFrame("Edit Options");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setSize(1000, 600);

        MenuPanel menuPanel = new MenuPanel();
        menuPanel.buildPanel(frame.getSize(),EditButtons.values());

        JLabel headerLabel1 = new JLabel("HOW TO EDIT:\n" +
                "Input desired change into text field following current value.",JLabel.CENTER );
        JLabel headerLabel2 = new JLabel("Format of tuple shown:" +
                "column name, data type(NULL?)(PK?), value",JLabel.CENTER );
        JLabel infoLabel = new JLabel("*Pressing the 'Update' button will close the window*",JLabel.CENTER);

        frame.add(headerLabel1);
        frame.add(headerLabel2);

        int i  = 0;
        JLabel newLabel;
        JFormattedTextField newField;
        fieldArray = new JFormattedTextField[tupleString.length];
        MaskFormatter fieldForm;
        while(i < tupleString.length){
            fieldForm = getFormatter(tupleString[i]);
            newField = new JFormattedTextField(fieldForm);
            if(tupleString[i].contains("Primary Key")){
                newField.setEditable(false);
            }
            newLabel = new JLabel(tupleString[i],JLabel.LEFT);
            fieldArray[i] = newField;
            frame.add(newLabel);
            frame.add(newField,JFormattedTextField.RIGHT);
            i++;
        }
        frame.add(infoLabel, JLabel.BOTTOM);
        frame.add(menuPanel.getPanel());
        frame.setVisible(true);

    }
    private static MaskFormatter getFormatter(String tupleString) {
        MaskFormatter fieldFormatter;
        fieldFormatter = new MaskFormatter();

        String datatype, mask;
        String valDef;
        boolean notNull= false;
        int varLen=0;
        valDef = tupleString.substring(tupleString.lastIndexOf(", ")+1,tupleString.indexOf(":\n")-1);

        if(tupleString.contains("varchar")){
            datatype = "varchar";
            varLen =
                    Integer.parseInt(tupleString.substring(tupleString.indexOf('(')+1,tupleString.indexOf(')')-1));
        }else if(tupleString.contains("time")) datatype = "datetime";
        else datatype = "date";
        if(tupleString.contains("Default")){
            valDef = tupleString.substring(tupleString.indexOf('\'')+1, tupleString.indexOf('\'')-1);
            fieldFormatter.setPlaceholder(valDef);
        }if(tupleString.contains("NOT NULL")){
            fieldFormatter.setPlaceholder(valDef);
        }

        try {
            return switch (datatype) {
                case "varchar" -> {
                    int i =0;
                    mask="";
                    while(i<varLen){
                        mask+="*"; i++;
                    }
                    fieldFormatter.setMask(mask);
                    yield fieldFormatter;
                }
                case "date" -> {
                   mask = "####-##-##";
                   fieldFormatter.setMask(mask);
                   yield fieldFormatter;
                }
                case "datetime" -> {
                    mask = "####-##-## ##:##:##";
                    fieldFormatter.setMask(mask);
                    yield fieldFormatter;
                }
                default -> null;
            };
        }catch(java.text.ParseException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }
    public static EditFrame getInstance(String[] tupleString){
        if(instance == null){
            instance = new EditFrame(tupleString);
        }
        return instance;
    }
    public static String[] getinputText(){
        String[] updateQueryInput = new String[fieldArray.length];
        int i = 0;
        while(i < fieldArray.length){
            updateQueryInput[i] = fieldArray[i].getText();
            i++;
        }
        return updateQueryInput;
    }

}