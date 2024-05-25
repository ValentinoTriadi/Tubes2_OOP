package oop.if2210_tb2_sc4.card;

import oop.if2210_tb2_sc4.UI.CardUI;
import oop.if2210_tb2_sc4.UI.DropZone;

import java.util.function.Function;

public class DestroyCard  extends BadPotion {
    public DropZone destroyedCardContainer;
    public DestroyCard(String name) {
        super(name);
    }

    public void setDestroyedCardContainer(DropZone dz){
        destroyedCardContainer = dz;
    }
    public DestroyCard(DestroyCard destroyCard) {
        super(destroyCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
       if (!card.getEffect().contains(EffectType.PROTECT)) {
           // Remove the UI Card From Container
            destroyedCardContainer.getChildren().remove(0);
       }
    }
}
