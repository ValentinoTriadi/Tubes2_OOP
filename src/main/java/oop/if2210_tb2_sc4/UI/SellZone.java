package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.player.Player;

public class SellZone extends DropZone{
    public SellZone(ShopUI shop) {
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

    public void onSell(ProductCard card){
        Pane droppedItems = (Pane) this.getChildren().get(0);
        this.getChildren().remove(0);
        // Make add money mechanism here

        Player currPlayer = GameWindowController.getCurrentPlayerPane().getPlayerData();
        currPlayer.addGulden(card.getPrice());

        GameWindowController.getShop().getShopData().sellCardToShop(card);
    }

    //  Check if intersect with sellzone
    //  Return true if success
    //  Return false if failed
    public static boolean OnSellZoneDetected(DropZone dz, DraggablePane card, MouseEvent e){
        if (dz instanceof SellZone && card.isMouseInDropZone(e, dz) && !dz.isDisabled()) {
            if (card instanceof ItemUI) {
                Card checkedCard = ((ItemUI) card).getCard();
                if(checkedCard instanceof ProductCard productCard){
                    System.out.println("Intersected with sellzone");
                    card.setLayoutX(0);
                    card.setLayoutY(0);
                    card.setParent(dz);
                    ((SellZone) dz).onSell(productCard);
                    GameWindowController.getShop().findItem(checkedCard.getName()).addJumlah(1);
                    return true;
                }
                return  false;
            }
            return false;
        }
        return false;
    }
}
