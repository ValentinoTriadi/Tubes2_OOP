package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

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
        shopGrid = new GridPane();
        shopGrid.setGridLinesVisible(true);

        // Create ColumnConstraints
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(50);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(50);

        RowConstraints row = new RowConstraints();
        row.setMinHeight(100);

        // Add the RowConstraints to the GridPane
        shopGrid.getRowConstraints().add(row);

        // Add the ColumnConstraints to the GridPane
        shopGrid.getColumnConstraints().addAll(column1, column2);
    }

    public void initializeSellZone(){
        SellZone.setPrefSize(100, 100);
        SellZone.setStyle("-fx-background-color: yellow;");
    }

    public void addShopItem(ShopItems item){
        shopGrid.add(item, 0, 0);
    }
}
