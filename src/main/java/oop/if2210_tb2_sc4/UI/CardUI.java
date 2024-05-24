package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.util.ImageUtil;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
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


    private double firstClickX = 0;
    private double firstClickY = 0;

    @Override
    public void OnClick(@NotNull MouseEvent e) {
        if (this.getParent() instanceof DropZone && !((DropZone) this.getParent()).isMusuhDisabilitas() || this.getParent() instanceof CardHolder) {
            super.OnClick(e);
        }

        double currentX = e.getSceneX();
        double currentY = e.getSceneY();

        if (isDoubleClickAtSamePosition(currentX, currentY)) {
            if (!this.tempParent.isDisabled()) {
                try {
                    loadHarvestedPanel();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            updateClickPosition(currentX, currentY);
        }
    }

    private boolean isDoubleClickAtSamePosition(double currentX, double currentY) {
        double POSITION_THRESHOLD = 5;
        return (Math.abs(currentX - firstClickX) < POSITION_THRESHOLD) && (Math.abs(currentY - firstClickY) < POSITION_THRESHOLD);
    }

    private void updateClickPosition(double currentX, double currentY) {
        firstClickX = currentX;
        firstClickY = currentY;
    }

    private void loadHarvestedPanel() throws IOException {
        FXMLLoader loadCardPicker = new FXMLLoader(Objects.requireNonNull(getClass().getResource("HarvestedPanel.fxml")));
        AnchorPane HarvestedPane = loadCardPicker.load();
        HarvestedPanelController controller = loadCardPicker.getController();
        controller.setInformation(cardData);
        controller.setActiveItem(cardData);
        controller.setImage(cardData);
        controller.setRoot(HarvestedPane);
        controller.setCard(cardData);
        if (this.tempParent instanceof AnchorPane) {
            controller.setHasHarvestAccess(false);
            controller.setDropZones((DropZone) this.getParent());
        } else {
            controller.setHasHarvestAccess(true);
            controller.setDropZones((DropZone) this.tempParent);
        }
        controller.handlePanenButton(cardData);
        GameWindowController.rootStatic.getChildren().add(HarvestedPane);
    }

    @Override
    public void OnDrag(@NotNull MouseEvent e) {
        if (this.getParent() instanceof DropZone && ((DropZone) this.getParent()).isMusuhDisabilitas()) {
            return;
        }
        super.OnDrag(e);
    }

    @Override
    public void OnRelease(MouseEvent e){
        if (this.getParent() instanceof DropZone && ((DropZone) this.getParent()).isMusuhDisabilitas()) {
            return;
        }

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
        }else{
            // Play sfx sound
            playCardSound();
        }
    }

    private void playCardSound() {
        String cardName = cardData.getName();
        String soundFile = AudioManager.getInstance().getCardSoundMap().get(cardName);
        if (soundFile != null) {
            AudioManager.getInstance().playSFX(soundFile);
        }
    }
}


