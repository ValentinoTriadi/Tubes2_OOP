package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.card.ItemCard;

public class DropZone extends Pane {

    private boolean isTarget = false;

    public boolean isTarget() {
        return isTarget;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public DropZone() {
        super();
    }

    public void onItemDrop(Card cardInput) {
        CardUI card = (CardUI) this.getChildren().get(0);
        FarmResourceCard cardData = (FarmResourceCard) card.getCardData();

        ItemCard itemData = (ItemCard) cardInput;
        itemData.applyEffect(cardData);

        this.getChildren().remove(1);
    }
}
