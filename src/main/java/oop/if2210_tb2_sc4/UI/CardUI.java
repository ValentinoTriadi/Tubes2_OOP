package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.util.ImageUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CardUI extends DraggablePane implements UICard {

    private Card cardData;

    public CardUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        //
        setPrefSize(90, 100);
        setStyle("-fx-background-color: transparent;");
    }

    private void setImage() {
        Image image = ImageUtil.getCardImage(cardData);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(80);
        imageView.setFitHeight(120);
        imageView.setSmooth(true);
        imageView.setCache(true);

        // Bind the ImageView size to the CardUI size
        imageView.fitWidthProperty().bind(this.widthProperty());
        imageView.fitHeightProperty().bind(this.heightProperty());

        // Bind the layoutX and layoutY properties to center the ImageView
        imageView.layoutXProperty().bind(this.widthProperty().subtract(imageView.fitWidthProperty()).divide(2).add(4));
        imageView.layoutYProperty().bind(this.heightProperty().subtract(imageView.fitHeightProperty()).divide(2));

        getChildren().add(imageView);
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

    public Card getCardData(){
        return cardData;
    }

    public void setCard(Card card){
        this.cardData = card;
        setImage();
    }

    @Override
    public void OnClick(@NotNull MouseEvent e) {
        super.OnClick(e);
    }

    @Override
    public void OnRelease(MouseEvent e){
        boolean droppedOnDropZone = false;
        for (DropZone dz : dropZone) {

            // Check if the mouse position is within the dropzone
            if (isMouseInDropZone(e, dz) && dz.getChildren().isEmpty() && !dz.isDisabled()) {

                System.out.println("Intersected with dropzone");
                setLayoutX(0);
                setLayoutY(0);
                droppedOnDropZone = true;
                setParent(dz);
                //Update Realtime LadangUI data
                GameWindowController.getCurrentPlayerPane().getLadang().UpdateLadangData();
                break;
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }

}


