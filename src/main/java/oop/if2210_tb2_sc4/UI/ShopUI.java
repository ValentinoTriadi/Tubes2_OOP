package oop.if2210_tb2_sc4.UI;

import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Shop;
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
        itemCount = 0;
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