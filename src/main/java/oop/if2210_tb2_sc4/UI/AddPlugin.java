package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.io.File;

public class AddPlugin {
    public Label message;
    private File fileChoosen;

    public AddPlugin() {
        message = new Label();
        message.setText("");
    }
    
    public void initialize() {
    }


    public void onUploadJar(ActionEvent actionEvent) {
        try{
            //TODO: Make The Add Jar Function
        }catch(Exception e){
            MessageBox.getInstance().showErrorMessage("Error", " File is Empty");
        }
    }

    public void chooseFile(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JAR", "*.jar")
        );
        fileChoosen = fileChooser.showOpenDialog(null);
        if (fileChoosen != null) {
            message.setText("File selected: "+ fileChoosen.getName());
        }
    }
}
