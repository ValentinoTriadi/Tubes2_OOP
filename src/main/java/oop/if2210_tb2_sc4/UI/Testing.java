package oop.if2210_tb2_sc4.UI;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Testing extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the main StackPane
        StackPane mainStackPane = new StackPane();

        // Create the first complex hierarchy
        AnchorPane anchorPaneA = new AnchorPane();
        VBox vBox = new VBox();
        TabPane tabPane = new TabPane();
        GridPane gridPane = new GridPane();
        Pane pane1 = new Pane();
        pane1.setStyle("-fx-background-color: lightblue;");
        pane1.setPrefSize(300, 300);

        // Add pane1 to the gridPane
        gridPane.add(pane1, 0, 0);

        // Add gridPane to the tabPane
        Tab tab = new Tab("Tab 1", gridPane);
        tabPane.getTabs().add(tab);

        // Add tabPane to the vBox
        vBox.getChildren().add(tabPane);

        // Add vBox to the anchorPaneA
        anchorPaneA.getChildren().add(vBox);

        // Add anchorPaneA to the mainStackPane
        mainStackPane.getChildren().add(anchorPaneA);

        // Create the second complex hierarchy
        StackPane stackPane2 = new StackPane();
        AnchorPane anchorPaneB = new AnchorPane();
        Pane pane2 = new Pane();
        pane2.setStyle("-fx-background-color: lightgreen;");
        pane2.setPrefSize(300, 300);

        // Add pane2 to the anchorPaneB
        anchorPaneB.getChildren().add(pane2);

        // Add anchorPaneB to the stackPane2
        stackPane2.getChildren().add(anchorPaneB);

        // Add stackPane2 to the mainStackPane
        mainStackPane.getChildren().add(stackPane2);

        // Create a target point in pane1
        Circle targetPoint = new Circle(20, 20, 5);
        pane1.getChildren().add(targetPoint);

        // Create a moving object in pane2
        Circle movingObject = new Circle(10, 10, 10);
        pane2.getChildren().add(movingObject);

//        // Convert the target point's coordinates from pane1 to the scene
//        Point2D targetInScene = targetPoint.localToScene(targetPoint.getCenterX(), targetPoint.getCenterY());
//
//        // Convert the scene coordinates to pane2's local coordinates
//        Point2D targetInPane2 = pane2.sceneToLocal(targetInScene);
//
//        // Move the object to the target point's coordinates in pane2
//        movingObject.setCenterX(targetInPane2.getX());
//        movingObject.setCenterY(targetInPane2.getY());

        // Set up the scene and stage
        Scene scene = new Scene(mainStackPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Coordinate Sync Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}