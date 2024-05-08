package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Shop {

    public GridPane shopGrid;
    public Pane SellZone;

    public Shop() {
        shopGrid = new GridPane();
        SellZone = new Pane();
    }

    public void initialize(){
        initializeShop();
        initializeSellZone();
    }

    public void initializeShop(){
        shopGrid.setGridLinesVisible(true);
    }

    public void initializeSellZone(){
        SellZone.setPrefSize(100, 100);
        SellZone.setStyle("-fx-background-color: yellow;");
    }
    class items {
        public String name;
        public int price;
    }
}
