package oop.if2210_tb2_sc4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    protected void NewGame(ActionEvent event) throws IOException {
        // Close the current window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        // Open the new window
        GameWindow gameWindow = new GameWindow();
        gameWindow.start(new Stage());
    }

}