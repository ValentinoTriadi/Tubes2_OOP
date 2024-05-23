package oop.if2210_tb2_sc4.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GameWindow extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GameWindow.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 720);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("GameWindow.css")).toExternalForm());
        primaryStage.setTitle("Game Window");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1024);
        primaryStage.setMinHeight(720);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}