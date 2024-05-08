package oop.if2210_tb2_sc4.UI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class ItemUI extends DraggablePane {

    private String name;
    public ItemUI(Pane parent, DropZone[] dropZone) {
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
    public void OnRelease(MouseEvent e){
        boolean droppedOnDropZone = false;
        for (DropZone dz : dropZone) {
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