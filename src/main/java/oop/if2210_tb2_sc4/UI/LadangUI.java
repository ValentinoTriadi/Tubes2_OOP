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

    public DropZone[] getLadang() {
        return ladang;
    }

    public void setLadangData(Ladang ladang){
        this.ladangData = ladang;
    }

    public DropZone getLadang(int index) {
        return ladang[index];
    }

    public Ladang getLadangData(){
        return ladangData;
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

    public void UpdateLadangData() {
        for (DropZone dropZone : ladang) {
            // Get the column and row index of the DropZone in the GridPane
            Integer columnIndex = GridPane.getColumnIndex(dropZone);
            Integer rowIndex = GridPane.getRowIndex(dropZone);

            if (columnIndex != null && rowIndex != null) {
                // Get the child node (CardUI) of the DropZone
                Node child = dropZone.getChildren().isEmpty() ? null : dropZone.getChildren().get(0);
                if (child instanceof CardUI cardUI) {
                    FarmResourceCard cardData = (FarmResourceCard) cardUI.getCardData();
                    ladangData.setCard(String.valueOf(columnIndex + rowIndex * 5), cardData);
                } else {
                    // If no card is present, you may want to handle this case according to your application's logic
                    ladangData.setCard(String.valueOf(columnIndex + rowIndex * 5), null);
                }
            }
        }
    }

    public void UpdateLadangUI(){

    }

}