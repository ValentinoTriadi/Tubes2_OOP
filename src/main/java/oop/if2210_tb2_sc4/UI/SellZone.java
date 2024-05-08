package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;

public class SellZone extends DropZone{
    public SellZone() {
        super();
    }

    public void initializeSellZone(){
        this.setPrefSize(100, 100);
        this.setStyle("-fx-background-color: yellow;");
    }

    public void disableField(){
        this.setDisable(true);
    }

    public void enableField(){
        this.setDisable(false);
    }

    public void onSell(){
        Pane droppedItems = (Pane) this.getChildren().get(0);
        droppedItems.getChildren().clear();
        // Make add money mechanism here

    }
}
