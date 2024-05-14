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
        setPrefSize(100, 120);
        setStyle("-fx-background-color: white;");
    }

    private void setImage() {
        Image image = ImageUtil.getCardImage(cardData);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);
    }

    public Card getCardData(){
        return cardData;
    }

    private void setCard(Card card) {
        this.cardData = card;
    }

    public void initCard(Card card){
        setCard(card);
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
                break;
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }

    public void UpdateLadangData(){

    }
}


