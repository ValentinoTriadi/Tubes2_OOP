package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.Objects;

public class ItemUI extends DraggablePane implements UICard {

    private Card cardItem;
    public ItemUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        setPrefSize(90, 100);
        setStyle("-fx-background-color: transparent;");
    }

    private void setImage() {
        Image image = ImageUtil.getCardImage(cardItem);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // Bind the ImageView size to the CardUI size
        imageView.fitWidthProperty().bind(this.widthProperty());
        imageView.fitHeightProperty().bind(this.heightProperty());

        // Bind the layoutX and layoutY properties to center the ImageView
        imageView.layoutXProperty().bind(this.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));
        imageView.layoutYProperty().bind(this.heightProperty().subtract(imageView.fitHeightProperty()).divide(2));

        getChildren().add(imageView);
    }

    public void setCard(Card card) {
        this.cardItem = card;
        setImage();
    }

    public Card getCard(){
        return this.cardItem;
    }

    // Used to resize the pane and the card image inside it
    public void setSize(int width, int height){
        this.setPrefSize(width, height);
        // Resize the Card size of the children
        if(!this.getChildren().isEmpty()){
            ImageView child = (ImageView) this.getChildren().get(0);
            child.setFitWidth(100);
            child.setFitHeight(100);
            this.getChildren().set(0, child);
        }
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
            if (isMouseInDropZone(e, dz) && !dz.getChildren().isEmpty()) {

                System.out.println("Intersected with enemy dropzone");
                setLayoutX(0);
                setLayoutY(0);
                droppedOnDropZone = true;

                // Set the parent to the dropzone
                setParent(dz);

                // Call the onDrop method of the dropzone
                dz.onItemDrop(this.cardItem);
                break;
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }
}