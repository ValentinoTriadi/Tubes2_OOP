package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SaveLoad {

    @FXML
    public ComboBox<String> choice;
    public Label message;

    public SaveLoad() {
        message = new Label();
        message.setText("");
    }

    @FXML
    public void initialize() {
        choice.getItems().add("TXT");
        choice.getItems().add("JSON");
        choice.getItems().add("YAML");
    }

    public void saveGame() {
        // Save the game
    }

    public void loadGame() {
        // Load the game
    }
}