package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.jetbrains.annotations.NotNull;

public class DraggablePane extends Pane {
    private double x, y;
    private final Pane root;
    private Pane parent, tempParent;
    private final Pane cardPane;
    private final DropZone[] dropZone = GameWindowController.ladang;

    boolean isDragging = false;

    public DraggablePane(Pane parent) {

        root = parent;
        this.parent = parent;
        cardPane = this;

        setOnMousePressed(this::OnClick);
        setOnMouseDragged(this::OnDrag);
        setOnMouseReleased(this::OnRelease);
    }

    private void OnClick(@NotNull MouseEvent e){
        setParent(root);
        x = e.getSceneX() - tempParent.getLayoutX();
        y = e.getSceneY() - tempParent.getLayoutY();
        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    private void OnDrag(@NotNull MouseEvent e){
        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    private void OnRelease(MouseEvent e){
        boolean droppedOnDropZone = false;
        for (DropZone dz : dropZone) {
            // Check if the mouse position is within the dropzone
            if (isMouseInDropZone(e, dz) && dz.getChildren().isEmpty()) {

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

    private void resetPosition() {
        setParent(tempParent);
        setLayoutX(0);
        setLayoutY(0);
    }

    private boolean isMouseInDropZone(MouseEvent e, DropZone dz) {
        Bounds dropzoneBounds = dz.localToScene(dz.getBoundsInLocal());
        return dropzoneBounds.contains(e.getSceneX(), e.getSceneY());
    }

    private void setParent(Pane newParent) {
        if (parent != null) {
            parent.getChildren().remove(cardPane);
        }
        tempParent = parent;
        newParent.getChildren().add(cardPane);
        parent = newParent;
    }
}