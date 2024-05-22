package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;
import org.jetbrains.annotations.NotNull;

public abstract class DraggablePane extends Pane {

    private double x, y;
    private final Pane root;
    protected Pane parent, tempParent;
    private final Pane cardPane;
    protected DropZone[] dropZone;

    public DraggablePane(Pane parent, DropZone[] dropZone) {
        this.root = GameWindowController.rootStatic;
        this.parent = parent;
        cardPane = this;
        this.dropZone = dropZone;

        setOnMousePressed(this::OnClick);
        setOnMouseDragged(this::OnDrag);
        setOnMouseReleased(this::OnRelease);
    }
    protected void setAllignmentToCenter(Pane parent) {

        double parentWidth = parent.getWidth();
        double parentHeight = parent.getHeight();
        double cardWidth = this.getPrefWidth();
        double cardHeight = this.getPrefHeight() ;

        // Calculate the position to center the CardUI
        double centeredX = (parentWidth - cardWidth) / 2;
        double centeredY = (parentHeight - cardHeight) / 2;

        // Set the layout positions
        this.setLayoutX(centeredX);
        this.setLayoutY(centeredY);
    }

    protected void OnClick(@NotNull MouseEvent e){
        setParent(root);
        Bounds tempParentBounds = tempParent.localToScene(tempParent.getBoundsInLocal());

        //! DONT REMOVE THE +10 OR IT WILL BROKE
        x = e.getSceneX() - tempParentBounds.getMinX() + 10;
        y = e.getSceneY() - tempParentBounds.getMinY() + 10;

        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    protected void OnDrag(@NotNull MouseEvent e){
        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    protected abstract void OnRelease(MouseEvent e);

    public void resetPosition() {
        setParent(tempParent);
    }

    boolean isMouseInDropZone(MouseEvent e, DropZone dz) {
        Bounds dropzoneBounds = dz.localToScene(dz.getBoundsInLocal());
        return dropzoneBounds.contains(e.getSceneX(), e.getSceneY());
    }

    public void setParent(Pane newParent) {
        if (parent != null) {
            parent.getChildren().remove(cardPane);
        }
        tempParent = parent;
        newParent.getChildren().add(cardPane);
        parent = newParent;
        setAllignmentToCenter(newParent);
    }
}