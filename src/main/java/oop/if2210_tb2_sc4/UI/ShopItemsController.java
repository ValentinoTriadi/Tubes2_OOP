package oop.if2210_tb2_sc4.UI;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.Exception.NotEnoughMoneyException;
import oop.if2210_tb2_sc4.Exception.ZeroItemStockException;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.GameData;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.util.StringUtil;

public class ShopItemsController {
    public ImageView ImageUI;
    public String productNameUI;
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
        ShopUI shop = GameWindowController.getShop();
        String productName= StringUtil.toUpperSnakeCase(productNameUI);
        ProductCard tempCard = null;
        try{
            ProductCard card = GameState.getInstance().getShop().buyCardFromShop((ProductCard) GameData.getCard(productName));
            tempCard = card;
            GameWindowController.getCurrentPlayerPane().getPlayerData().reduceGulden(card.getPrice(), card.getName());
            GameWindowController.getCurrentPlayerPane().addItem(tempCard);
            shop.findItem(productName).reduceJumlah();
        }catch (ZeroItemStockException err){
            err.ShowErrorPanel();
        }catch (NotEnoughMoneyException err){
            GameState.getInstance().getShop().addCard((ProductCard) GameData.getCard(productName));
            err.ShowErrorPanel();
        }catch (FullActiveHandsException err){
            GameState.getInstance().getShop().addCard((ProductCard) GameData.getCard(productName));
            GameWindowController.getCurrentPlayerPane().getPlayerData().addGulden(tempCard.getPrice());
            err.ShowErrorPanel();
        }
    }
}
