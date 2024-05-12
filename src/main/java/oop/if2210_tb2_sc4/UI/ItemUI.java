package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.Objects;

public class ItemUI extends DraggablePane {

    private Card cardItem;
    public ItemUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        setPrefSize(100, 120);
        setStyle("-fx-background-color: white;");
    }

    private void setImage() {
        Image image = ImageUtil.getCardImage(cardItem);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);
    }

    private void setName(Card card) {
        this.cardItem = card;
    }

    public Card getCard(){
        return this.cardItem;
    }

    public void initCard(Card card){
        setName(card);
        setImage();
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
            if (isMouseInDropZone(e, dz) && !dz.getChildren().isEmpty() && !dz.isDisabled()) {

                System.out.println("Intersected with enemy dropzone");
                setLayoutX(0);
                setLayoutY(0);
                droppedOnDropZone = true;

                // Set the parent to the dropzone
                setParent(dz);

                // Call the onDrop method of the dropzone
                dz.onItemDrop();
                break;
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }
}

enum Target {
    SELF,
    ENEMY
}