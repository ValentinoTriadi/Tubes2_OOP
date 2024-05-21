package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;

public class DropZone extends Pane {

    private boolean isTarget = false;

    public boolean isTarget() {
        return isTarget;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public DropZone() {
        super();
    }

    public void onItemDrop() {
        System.out.println("Item Processed");
        CardUI card = (CardUI) this.getChildren().get(0);
        this.getChildren().remove(1);
    }
}
