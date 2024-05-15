package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.util.ImageUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class CardUI extends DraggablePane implements UICard {

    private Card cardData;
    private boolean isOnLadang = false;

    public CardUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        setPrefSize(100, 120);
        setStyle("-fx-background-color: white;");
        //setOnMouseReleased(this::OnMouseReleased);
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


    private double firstClickX = 0;
    private double firstClickY = 0;
    private final double POSITION_THRESHOLD = 5;
    @Override
    public void OnClick(@NotNull MouseEvent e) {
        super.OnClick(e);
        double currentX = e.getSceneX();
        double currentY = e.getSceneY();

        if ((Math.abs(currentX - firstClickX) < POSITION_THRESHOLD) && (Math.abs(currentY - firstClickY) < POSITION_THRESHOLD)) {
            // Double click detected at nearly the same position
            System.out.println("Double click detected at the same position!");
            if (isOnLadang) {
                try {
                    FXMLLoader loadCardPicker = new FXMLLoader(Objects.requireNonNull(getClass().getResource("HarvestedPanel.fxml")));
                    AnchorPane HarvestedPane = loadCardPicker.load();
                    HarvestedPanelController controller = loadCardPicker.getController();
                    controller.setProductName(cardData);
                    controller.setInformation(cardData);
                    controller.setActiveItem(cardData);
                    controller.setImage(cardData);
                    controller.setRoot(HarvestedPane);
                    controller.handlePanenButton(cardData);
                    GameWindowController.rootStatic.getChildren().add(HarvestedPane);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            firstClickX = currentX;
            firstClickY = currentY;
        }
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
                isOnLadang = true;
                setParent(dz);
                break;
            }
        }

        // If not dropped on a dropzone, return to default position
        if (!droppedOnDropZone) {
            resetPosition();
        }
    }

}


