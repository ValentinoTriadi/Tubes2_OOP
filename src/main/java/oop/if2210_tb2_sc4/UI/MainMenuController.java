package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class MainMenuController {

    public AnchorPane root;

    @FXML
    public void NewGame(ActionEvent event) throws IOException {

        // Close the current window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Open the new window
        GameWindow gameWindow = new GameWindow();
        gameWindow.start(new Stage());
    }

}