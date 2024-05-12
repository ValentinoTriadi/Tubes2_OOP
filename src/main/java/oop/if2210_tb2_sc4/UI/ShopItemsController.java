package oop.if2210_tb2_sc4.UI;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.util.StringUtil;

public class ShopItemsController {
    public ImageView ImageUI;
    public Label productNameUI;
    public Label labelHargaUI;
    public Label labelJumlahUI;

    private int index;

    public ShopItemsController() {
        index = ShopUI.itemCount;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void buyItem() {
        Card cardData = GameData.getCard(StringUtil.toUpperSnakeCase(productNameUI.getText()));
        GameWindowController.getCurrentPlayerPane().addItem(cardData);
    }
}
