package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.*;

public class PotionUI extends ItemUI{
    public PotionUI(Card cardData, Pane parent, DropZone[] dropZone){
        super(parent, dropZone);
        super.setCard(cardData);
    }

    @Override
    public void OnRelease(MouseEvent e){
        boolean droppedOnDropZone = checkDropZones(e);
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }

    private boolean checkDropZones(MouseEvent e) {
        for (DropZone dz : dropZone) {
            if (dz.isDisabled()) {
                continue;
            }

            if(SellZone.OnSellZoneDetected(dz, this, e)){
                return true;
            }

            if (isMouseInDropZone(e, dz) && !dz.getChildren().isEmpty()) {
                handleDropOnZone(dz);
                return true;
            }
        }
        return false;
    }

    private void handleDropOnZone(DropZone dz) {
        setLayoutX(0);
        setLayoutY(0);
        setParent(dz);

        FarmResourceCard cardLadang = ((FarmResourceCard)((CardUI)dz.getChildren().get(0)).getCardData());
        ItemCard potion = (ItemCard)this.getCard();
        dz.onItemDrop();

        if(potion instanceof DestroyCard){
            ((DestroyCard) potion).setDestroyedCardContainer(dz);
        }

        if(potion instanceof InstantHarvestCard){
            ((InstantHarvestCard) potion).setHarvestedCardContainer(dz);
        }

        potion.applyEffect(cardLadang);
    }
}
