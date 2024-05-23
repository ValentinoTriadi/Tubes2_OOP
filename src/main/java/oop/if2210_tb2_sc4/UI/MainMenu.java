package oop.if2210_tb2_sc4.UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenu extends Application {

    Thread gameThread;
    public static void main(String[] args) {
        launch(args);
    }

    public static FXMLLoader getScene() {
        return new FXMLLoader();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        Scene scene = new Scene(loader.load(), 1024, 720);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Farm Violate");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
