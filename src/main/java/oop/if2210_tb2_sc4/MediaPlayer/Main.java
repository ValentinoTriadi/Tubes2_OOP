package oop.if2210_tb2_sc4.MediaPlayer;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;


public class Main extends Application {

    private AudioManager audioManager;

    @Override
    public void start(Stage primaryStage) {
        audioManager = AudioManager.getInstance();

        // Play background music
        audioManager.playBackgroundMusic("FullBG.wav");

        // Play a sound effect
//        audioManager.playSFX("a.wav");

        // Set up the primary stage and scene
        primaryStage.setTitle("JavaFX Audio Example");
        primaryStage.setScene(new Scene(new StackPane(), 300, 250));
        primaryStage.show();
    }

    @Override
    public void stop() {
        // Stop all audio when the application is closed
        audioManager.stopBackgroundMusic();
        audioManager.stopSFX();
    }

    public static void main(String[] args) {
        launch(args);
    }
}