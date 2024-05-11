package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class CardUI extends DraggablePane implements UICard {

    private String name;

    public CardUI(Pane parent, DropZone[] dropZone) {
        super(parent, dropZone);
        setPrefSize(100, 120);
        setStyle("-fx-background-color: white;");
    }

    private void setImage() {
        String path = "assets/card/" + name + ".png";
        Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        getChildren().add(imageView);
    }

    private void setName(String name) {
        this.name = name;
    }

    public void initCard(String name){
        setName(name);
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
            // Check if the mouse position is within the Sell zone
            if (dz instanceof SellZone) {
                if (isMouseInDropZone(e, dz) && !dz.isDisabled()) {
                    System.out.println("Intersected with sellzone");
                    setLayoutX(0);
                    setLayoutY(0);
                    droppedOnDropZone = true;
                    setParent(dz);
                    ((SellZone) dz).onSell();
                    break;
                }
            }

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
}


