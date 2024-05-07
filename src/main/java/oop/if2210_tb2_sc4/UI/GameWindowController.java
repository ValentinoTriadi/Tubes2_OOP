package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;

public class GameWindowController {


    private Stage player1Stage;
    private Stage player2Stage;

    @FXML
    public GridPane ladangGrid;
    public AnchorPane root;
    public HBox activeDeckHBox;

    public static DropZone[] ladang = new DropZone[20];
    public static CardHolder[] activeDeck = new CardHolder[6];

    public Button MyFieldButton;
    public Button EnemyFieldButton;
    public Button ShopButton;
    public Button SaveButton;
    public Button LoadButton;
    public Button AddPluginButton;

    public Button EndTurnButton;

    public void initialize() {

    }

    public void initializePlayer() {
        System.out.println("Initializing player");
    }

    public void initializeActiveDeck() {
        // Initialize activeDeck
        for (int i = 0; i < 6; i++) {
            activeDeck[i] = new CardHolder();
            activeDeck[i].setPrefSize(100, 150);
            activeDeck[i].setStyle("-fx-background-color: cyan;");
            activeDeckHBox.getChildren().add(activeDeck[i]);
        }
    }

    public void initializeLadang(){
        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            ladang[i] = new DropZone(col,row);
            ladang[i].setPrefSize(200, 200);
            ladang[i].setStyle("-fx-background-color: yellow;");
            ladangGrid.add(ladang[i], col, row);
        }
    }



    public void createGridPane() {
        GridPane gridPane = new GridPane();

        // Set properties
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(30);
        gridPane.setVgap(15);

        // Set column constraints
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(Priority.SOMETIMES);
            column.setMaxWidth(100);
            column.setMinWidth(50);
            column.setPrefWidth(50);
            gridPane.getColumnConstraints().add(column);
        }

        // Set row constraints
        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(200);
            row.setMinHeight(10);
            row.setPrefHeight(100);
            row.setVgrow(Priority.SOMETIMES);
            gridPane.getRowConstraints().add(row);
        }

        // Add children
        root.getChildren().add(gridPane);
    }

}
