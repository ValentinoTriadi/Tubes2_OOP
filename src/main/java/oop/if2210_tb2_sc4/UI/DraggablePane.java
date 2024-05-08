package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Bounds;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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

    private void OnClick(@NotNull MouseEvent e){
        setParent(root);
        Bounds tempParentBounds = tempParent.localToScene(tempParent.getBoundsInLocal());

        //! DONT REMOVE THE +10 OR IT WILL BROKE
        x = e.getSceneX() - tempParentBounds.getMinX() + 10;
        y = e.getSceneY() - tempParentBounds.getMinY() + 10;


        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    private void OnDrag(@NotNull MouseEvent e){
        setLayoutX(e.getSceneX() - x);
        setLayoutY(e.getSceneY() - y);
    }

    protected abstract void OnRelease(MouseEvent e);

    public void resetPosition() {
        setParent(tempParent);
        setLayoutX(0);
        setLayoutY(0);
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
    }
}