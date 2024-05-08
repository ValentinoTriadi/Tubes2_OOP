package oop.if2210_tb2_sc4.UI;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ShopItemsController {
    public ImageView ImageUI;
    public Label productNameUI;
    public Label labelHargaUI;
    public Label labelJumlahUI;

    private int index;

    public ShopItemsController() {
        index = Shop.itemCount;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void buyItem() {
        System.out.println("Buy item " + index);
    }
}
