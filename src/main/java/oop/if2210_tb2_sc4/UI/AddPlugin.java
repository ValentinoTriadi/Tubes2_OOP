package oop.if2210_tb2_sc4.UI;

import javafx.stage.FileChooser;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import java.io.File;

public class AddPlugin {
    public Button button;
    public Label message;
    public Button upload;
    
    public AddPlugin() {
        message = new Label();
        message.setText("");
    }
    
    public void initialize() {
        button.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JAR", "*.jar")
            );
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                message.setText("File selected: " + file.getName());
            }
        });
    }
}
