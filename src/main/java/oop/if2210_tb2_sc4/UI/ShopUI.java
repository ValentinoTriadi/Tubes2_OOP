package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.shop.Shop;
import oop.if2210_tb2_sc4.util.ImageUtil;
import oop.if2210_tb2_sc4.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopUI {

    @FXML
    public AnchorPane paneGrid;
    private final GridPane shopGrid;
    public Pane SellZone;
    private Shop shopData;
    public static int itemCount;
    ScrollPane scrollPane;
    private final List<ShopItems> shopItems = new ArrayList<>();

    public ShopUI() {
        shopGrid = new GridPane();
        scrollPane = new ScrollPane(shopGrid);

        // Set the ScrollPane to always show vertical scroll bar and to never show horizontal scroll bar
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxWidth(400);

        // Set the GridPane to fill the width of the ScrollPane
        shopGrid.prefWidthProperty().bind(scrollPane.widthProperty());
        shopGrid.setVgap(10);
    }

    public void initialize(){
        initializeSellZone();
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);
        paneGrid.getChildren().add(scrollPane);
    }

    public Shop getShopData(){return shopData;};


    public void initializeShopData() {
        // Initialize the shop data
        shopData = new Shop();

        // Add cards to the shop UI based on the shop's card stock
        for (Map.Entry<ProductCard, Integer> entry : shopData.getCardStock().entrySet()) {
            ProductCard card = entry.getKey();
            int stock = entry.getValue();
            Image cardImage = ImageUtil.getCardImage(card);
            ShopItems item = new ShopItems(StringUtil.toTitleCase(card.getName()), cardImage, String.valueOf(card.getPrice()), String.valueOf(stock));
            addShopItem(item);
            shopItems.add(item);
        }
    }

    public ShopItems findItem(String productName) {
        for (ShopItems item : shopItems) {
            if (item.getProductName().equalsIgnoreCase(productName)) {
                return item;
            }
        }
        return null;
    }

    public void addShopItem(ShopItems item) {

        int column = itemCount % 2;
        int row = itemCount / 2;

        // TODO: List the items in shop in shop Class

        // If it's the start of a new row, add a new RowConstraints to the GridPane
        if (column == 0) {
            shopGrid.getRowConstraints().add(new RowConstraints(100)); // 100 is the height of the row

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setPercentWidth(50);
            shopGrid.getColumnConstraints().add(column1);

            ColumnConstraints column2 = new ColumnConstraints();
            column2.setPercentWidth(50);
            shopGrid.getColumnConstraints().add(column2);
        }

        shopGrid.add(item.getScene(), column, row);
        itemCount++;
    }

    public void initializeSellZone(){
        GameWindowController.sellZone = new SellZone(this);
        // Sellzone fill parent
        GameWindowController.sellZone.setMinSize(100, 100);
        // Set style
        GameWindowController.sellZone.setStyle("-fx-background-color: yellow;");

        SellZone.getChildren().add(GameWindowController.sellZone);
    }

}