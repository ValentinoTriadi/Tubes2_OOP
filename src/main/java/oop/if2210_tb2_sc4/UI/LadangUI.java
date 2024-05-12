package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.ladang.Ladang;
import javafx.scene.Node;

import java.util.HashMap;
import java.util.Map;

public class LadangUI extends GridPane {

    private final DropZone[] ladang = new DropZone[20];
    private Ladang ladangData;

    public LadangUI(Ladang ladangData) {
        setAlignment(javafx.geometry.Pos.CENTER);
        setGridLinesVisible(true);
        setHgap(30.0);
        setPrefHeight(507.0);
        setPrefWidth(582.0);
        setVgap(15.0);

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            column.setMaxWidth(100.0);
            column.setMinWidth(50.0);
            column.setPrefWidth(50.0);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(200.0);
            row.setMinHeight(10.0);
            row.setPrefHeight(100.0);
            row.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
            getRowConstraints().add(row);
        }

        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            ladang[i] = new DropZone();
            ladang[i].setPrefSize(50, 100);
            ladang[i].setStyle("-fx-background-color: yellow;");
            this.add(ladang[i], col, row);
        }
        this.ladangData = ladangData;
    }

    public void UpdateCards(Ladang ladang){

    }

    public DropZone[] getLadang() {
        return ladang;
    }

    public DropZone getLadang(int index) {
        return ladang[index];
    }

    public void disableField(){
        for (DropZone dz : ladang) {
            dz.setDisable(true);
        }
    }

    public void enableField(){
        for(DropZone dz : ladang){
            dz.setDisable(false);
        }
    }

    public Map<String, FarmResourceCard> getAllCardWithLocationinLadang() {
        Map<String, FarmResourceCard> cardList = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            DropZone dropZone = ladang[i];
            if (dropZone.getChildren().isEmpty()) {
                continue; // Skip empty DropZone
            }
            CardUI cardUI = (CardUI) dropZone.getChildren().get(0);
            if (cardUI != null && cardUI.getCardData() != null) {
                FarmResourceCard card = (FarmResourceCard) cardUI.getCardData();
                String location = String.format("%c%d", 'A' + row, col + 1);
                cardList.put(location, card);
            }
        }
        return cardList;
    }


    public void UpdateLadangData() {
        Map<String, FarmResourceCard> oldCardList = ladangData.getAllCardwithLocationinLadang();
        Map<String, FarmResourceCard> newCardList = getAllCardWithLocationinLadang();

        int counter = 0;
        // Check for removed cards
        for (String oldLocation : oldCardList.keySet()) {
            if (!newCardList.containsKey(oldLocation)) {
                // Remove the card from the ladangData
                ladangData.removeCard(oldLocation);
            }
        }

        // Check for added cards or updated cards
        for (String newLocation : newCardList.keySet()) {
            FarmResourceCard newCard = newCardList.get(newLocation);
            FarmResourceCard oldCard = oldCardList.get(newLocation);
            if (oldCard == null || !oldCard.equals(newCard)) {
                // Add or update the card in the ladangData
                int row = newLocation.charAt(0) - 'A';
                int column = Integer.parseInt(newLocation.substring(1)) - 1;
                ladangData.setCard(row, column, newCard);
                counter++;
            }
        }
        System.out.println("Ladang Change: "+ counter);
    }

}