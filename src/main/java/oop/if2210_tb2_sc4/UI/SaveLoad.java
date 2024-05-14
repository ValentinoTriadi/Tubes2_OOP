package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class SaveLoad {

    @FXML
    public Button clickButton;
    @FXML
    public ComboBox<String> choice;
    @FXML
    public Label message;
    @FXML
    public Label title;


    public TextField FolderPath;

    public SaveLoad() {

    }

    public String getFolderName(){
        return FolderPath.getText();
    }

    @FXML
    public void initialize() {
        choice.getItems().add("TXT");
        choice.getItems().add("JSON");
        choice.getItems().add("YAML");
    }


    public void OnSaveLoad() {
        // Just A Button Holder so the other class can access it
    }

}