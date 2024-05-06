package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameWindowController {

    @FXML
    public GridPane deck;
    public AnchorPane root;

    public static DropZone[] ladang = new DropZone[20];
    public static CardHolder[] activeDeck = new CardHolder[6];

    public void initialize() {
        // Initialize ladang
        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            ladang[i] = new DropZone(col,row);
            ladang[i].setPrefSize(200, 200);
            ladang[i].setStyle("-fx-background-color: yellow;");

            deck.add(ladang[i], col, row);
        }
    }

    /**
     * ! Debug Method! Remove When Production Start
     */
    public void spawnCard() {
        String[] cardNames = {"hewan/bear", "hewan/chicken", "hewan/cow", "hewan/sheep", "hewan/horse"};
        String name = cardNames[(int) (Math.random() * cardNames.length)];

        CardUI card = new CardUI(root);
        card.initCard(name);
        root.getChildren().add(card);
        System.out.println("Card" + name +" spawned");
    }
}
