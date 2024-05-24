package oop.if2210_tb2_sc4.UI;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.Ladang;
import javafx.scene.Node;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.HashMap;
import java.util.Map;

public class LadangUI extends GridPane {

    private final DropZone[] ladang = new DropZone[20];
    private Ladang ladangData;
    int columns = 5;


    public LadangUI(Ladang ladangData) {

        this.setTranslateY(10);
        setAlignment(javafx.geometry.Pos.CENTER);
        setGridLinesVisible(false);
        setHgap(10);
        setPrefHeight(507.0);
        setPrefWidth(582.0);
        setVgap(10);

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            column.setMaxWidth(95);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(120);
            row.setMinHeight(100);
            row.setPrefHeight(100.0);
            row.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
            getRowConstraints().add(row);
        }

        //Load Background Image
        Image backgroundImage = ImageUtil.getComponentImage("TileBg.png");

        BackgroundImage bgImage = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(90, 110, true, true, true, false)
        );

        for (int i = 0; i < 20; i++) {
            int col = i % 5;
            int row = i / 5;
            ladang[i] = new DropZone();
            ladang[i].setPrefSize(104, 110);
            ladang[i].setBackground(new Background(bgImage));
//            ladang[i].setStyle("-fx-background-color: yellow;");
            this.add(ladang[i], col, row);
        }
        this.ladangData = ladangData;
    }

    public void setIsDisabled(Boolean isDisabled){
        for (DropZone dz : ladang) {
            dz.setMusuhDisabilitas(isDisabled);
        }
    }

    public Boolean getIsDisabled(){
        return ladang[0].isMusuhDisabilitas();
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
    private int getIndex(int row, int col) {
        return row * columns + col;
    }
    public void setLadangOnRedColor(int row, int col) {
        int index = getIndex(row,col);
        if (index < 0 || index >= ladang.length) {
            return;
        }
        DropZone dz = ladang[index];
        if (dz != null) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(0.0); // Red hue
            colorAdjust.setSaturation(1.0);

            DropShadow dropShadow = new DropShadow();
            dropShadow.setColor(Color.RED);
            dropShadow.setRadius(20);
            dropShadow.setSpread(0.5);

            dz.setEffect(new javafx.scene.effect.Blend(
                    javafx.scene.effect.BlendMode.MULTIPLY, colorAdjust, dropShadow));

            dz.setTarget(true);
        }
    }

    public void resetLadangColor() {
        for(DropZone dz : ladang) {
            dz.setEffect(null);
            dz.setTarget(false);
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
                    ladangData.setCard(rowIndex, columnIndex, cardData);
                } else {
                    // If no card is present, you may want to handle this case according to your application's logic
                    ladangData.setCard(rowIndex,columnIndex, null);
                }
            }
        }
    }

    public void UpdateLadangUI(){
        for (DropZone dropZone : ladang) {
            // Get the column and row index of the DropZone in the GridPane
            Integer columnIndex = GridPane.getColumnIndex(dropZone);
            Integer rowIndex = GridPane.getRowIndex(dropZone);

            if (columnIndex != null && rowIndex != null) {
                FarmResourceCard card = ladangData.getCard(rowIndex, columnIndex);
                if(card == null){
                    if(!dropZone.getChildren().isEmpty()){
                        dropZone.getChildren().remove(0);
                    }
                }else{
                    if(!dropZone.getChildren().isEmpty()){
                        dropZone.getChildren().remove(0);
                    }
                    CardUI cardUI = new CardUI(dropZone, ladang);
                    cardUI.setParent(dropZone);
                    cardUI.setCard(card);
                    cardUI.resetPosition();
                }
            }
        }
    }

}