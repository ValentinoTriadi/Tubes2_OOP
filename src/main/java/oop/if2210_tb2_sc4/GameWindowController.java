package oop.if2210_tb2_sc4;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import oop.if2210_tb2_sc4.draggable.DraggablePane;
import oop.if2210_tb2_sc4.dropzone.dropzone;

public class GameWindowController {

    @FXML
    public GridPane deck;
    public AnchorPane root;

    public void initialize() {

        // Create a new Drop Zone
        dropzone[] dropZone = new dropzone[20];

        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            dropZone[i] = new dropzone(col,row);
            dropZone[i].setPrefSize(200, 200);
            dropZone[i].setStyle("-fx-background-color: lightgreen;");

            deck.add(dropZone[i], col, row);
        }

        // Create a new DraggablePane
        DraggablePane draggablePane = new DraggablePane(dropZone, root);
        draggablePane.setPrefSize(100, 150);
        draggablePane.setStyle("-fx-background-color: black;");

        // Add the DraggablePane to the Anchor pane
        root.getChildren().add(draggablePane);

        // Create a new DraggablePane
        DraggablePane draggablePane2 = new DraggablePane(dropZone, root);
        draggablePane2.setPrefSize(100, 150);
        draggablePane2.setStyle("-fx-background-color: red;");

        // Add the DraggablePane to the Anchor pane
        root.getChildren().add(draggablePane2);

        // Create a new DraggablePane
        DraggablePane draggablePane3 = new DraggablePane(dropZone, root);
        draggablePane3.setPrefSize(100, 150);
        draggablePane3.setStyle("-fx-background-color: green;");

        // Add the DraggablePane to the Anchor pane
        root.getChildren().add(draggablePane3);
    }
}
