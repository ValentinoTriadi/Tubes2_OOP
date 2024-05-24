package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;


import java.io.IOException;

public class MainMenuController {

    public AnchorPane root;
    private AnchorPane settings;
    public void initialize() {
        try {
            AudioManager.getInstance().playBackgroundMusic("MainMenuMusic.wav");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
            settings = loader.load();
            settings.setVisible(false);
            root.getChildren().add(settings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void NewGame(ActionEvent event) throws IOException {

        // Close the current window
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.setResizable(false);
        currentStage.close();
        // Open the new window
        GameWindow gameWindow = new GameWindow();
        gameWindow.start(new Stage());
    }

    @FXML
    public void Exit(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    public void Settings(ActionEvent event) throws IOException {
        settings.setVisible(true);
    }

}