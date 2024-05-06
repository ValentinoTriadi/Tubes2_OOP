package oop.if2210_tb2_sc4.draggable;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.dropzone.dropzone;

public class DraggablePane extends Pane {
    private double x, y;
    private Pane root;

    public DraggablePane(dropzone[] dropZone, Pane root) {

        setOnMousePressed(e -> {
            x = e.getSceneX() - getLayoutX();
            y = e.getSceneY() - getLayoutY();
        });

        setOnMouseDragged(e -> {
            setLayoutX(e.getSceneX() - x);
            setLayoutY(e.getSceneY() - y);
        });

        setOnMouseReleased(e -> {
            Bounds draggableBounds = localToScene(getBoundsInLocal());
            for (dropzone dz : dropZone) {
                Bounds dropzoneBounds = dz.localToScene(dz.getBoundsInLocal());
                if (dropzoneBounds.intersects(draggableBounds)) {

                    double dropzoneX = dropzoneBounds.getMinX();
                    double dropzoneY = dropzoneBounds.getMinY();
                    double anchorPaneX = root.sceneToLocal(dropzoneX, dropzoneY).getX();
                    double anchorPaneY = root.sceneToLocal(dropzoneX, dropzoneY).getY();
                    setLayoutX(anchorPaneX);
                    setLayoutY(anchorPaneY);

                    break;
                }
            }
        });
    }
}