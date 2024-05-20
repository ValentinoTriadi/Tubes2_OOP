package oop.if2210_tb2_sc4.UI;

import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.shop.Shop;
import oop.if2210_tb2_sc4.util.ImageUtil;
import oop.if2210_tb2_sc4.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShopUI{

    @FXML
    public ScrollPane ScrollPane;
    private final GridPane shopGrid;
    public Pane SellZone;
    public static int itemCount;
    private final List<ShopItems> shopItems = new ArrayList<>();

    public ShopUI() {
        shopGrid = new GridPane();
    }

    public void initialize(){
        shopGrid.prefWidthProperty().bind(ScrollPane.widthProperty());
        ScrollPane.setContent(shopGrid);
        shopGrid.setStyle("-fx-background-color: transparent");
        initializeSellZone();
    }

    public void initializeShopData() {
        // Initialize the shop data
        GameState.getInstance().setShop(new Shop());

        // Add cards to the shop UI based on the shop's card stock
        for (Map.Entry<ProductCard, Integer> entry : GameState.getInstance().getShopItems().entrySet()) {
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
//        if (column == 0) {
//            shopGrid.getRowConstraints().add(new RowConstraints(128)); // 100 is the height of the row
//
//            ColumnConstraints column1 = new ColumnConstraints();
//            column1.setPercentWidth(50);
//            shopGrid.getColumnConstraints().add(column1);
//
//            ColumnConstraints column2 = new ColumnConstraints();
//            column2.setPercentWidth(50);
//            shopGrid.getColumnConstraints().add(column2);
//            }
        shopGrid.add(item.getScene(), column, row);
        itemCount++;
    }

    public void initializeSellZone(){
        GameWindowController.sellZone = new SellZone(this, 125, 150);
        // Sellzone fill parent
        GameWindowController.sellZone.setMinSize(125, 150);
        // Set style


        SellZone.getChildren().add(GameWindowController.sellZone);
    }

}