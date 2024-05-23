package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.*;


public class ProductUI extends ItemUI{
    public ProductUI(Card cardData, Pane parent, DropZone[] dropZone){
        super(parent, dropZone);
        super.setCard(cardData);
    }

    @Override
    public void OnRelease(MouseEvent e){
        boolean droppedOnDropZone = false;
        for (DropZone dz : dropZone) {
            if(SellZone.OnSellZoneDetected(dz, this,e )){
                droppedOnDropZone = true;
                break;
            }
            System.out.println(dz.getChildren().size());
            // Check if the mouse position is within the dropzone
            if (isMouseInDropZone(e, dz) && !dz.getChildren().isEmpty() && !dz.isDisabled()) {

                setLayoutX(0);
                setLayoutY(0);

                FarmResourceCard cardLadang = ((FarmResourceCard)((CardUI)dz.getChildren().get(0)).getCardData());
                if(cardLadang instanceof AnimalCard animalLadang){
                    if(animalLadang.feed((ProductCard)this.getCard())){
                        setParent(dz);
                        droppedOnDropZone = true;
                        dz.onItemDrop();
                        break;
                    }
                }
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }

}
