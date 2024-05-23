package oop.if2210_tb2_sc4.UI;

import com.almasb.fxgl.ui.UI;
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
        boolean droppedOnDropZone = false;
        for (DropZone dz : dropZone) {
            if(SellZone.OnSellZoneDetected(dz, this,e )){
                droppedOnDropZone = true;
                break;
            }

            // Check if the mouse position is within the dropzone
            if (isMouseInDropZone(e, dz) && !dz.getChildren().isEmpty() ) {

                System.out.println("Intersected with enemy dropzone");
                setLayoutX(0);
                setLayoutY(0);


                // Set the parent to the dropzone
                setParent(dz);

                FarmResourceCard cardLadang = ((FarmResourceCard)((CardUI)dz.getChildren().get(0)).getCardData());
                ItemCard potion = (ItemCard)this.getCard();
                potion.applyEffect(cardLadang);

                droppedOnDropZone = true;
                dz.onItemDrop();


            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }
}
