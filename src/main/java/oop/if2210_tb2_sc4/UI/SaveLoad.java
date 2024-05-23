package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import oop.if2210_tb2_sc4.Exception.GameException;
import oop.if2210_tb2_sc4.Exception.InvalidInputException;
import oop.if2210_tb2_sc4.save_load.Load;
import oop.if2210_tb2_sc4.Plugins;

import java.io.File;
import java.util.List;

public class SaveLoad {

    @FXML
    public Button clickButton;
    @FXML
    public ComboBox<String> choice;
    @FXML
    public Label title;


    public TextField FolderPath;
    public ImageView SaveMascot;
    public ImageView LoadMascot;

    public SaveLoad() {

    }

    public String getFolderName(){
        return FolderPath.getText();
    }

    @FXML
    public void initialize() {
        // Get choices from Plugins Class
        choice.getItems().add("TXT");
    }

    public void ActivateMascot(Object type){
        if(type instanceof SaveUI){
            SaveMascot.setVisible(true);
            LoadMascot.setVisible(false);
        }else{
            SaveMascot.setVisible(false);
            LoadMascot.setVisible(true);
        }
    }

    protected void OnSaveLoad() throws GameException{
        handleInput();
    }

    protected void handleInput() throws GameException {
        if (choice.getValue() == null) {
            throw new InvalidInputException("Please Select the extension of the File");
        }

        if (getFolderName().equalsIgnoreCase("")) {
            throw new InvalidInputException("Please Input a folder");
        }
    }
}