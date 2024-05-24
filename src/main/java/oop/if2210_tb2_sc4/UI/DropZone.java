package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;

public class DropZone extends Pane {

    private boolean isTarget = false;
    private boolean isMusuhDisabilitas = false;

    public boolean isTarget() {
        return isTarget;
    }

    public boolean isMusuhDisabilitas() {
        return isMusuhDisabilitas;
    }

    public void setMusuhDisabilitas(boolean disabled) {
        isMusuhDisabilitas = disabled;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public DropZone() {
        super();
    }

    public void onItemDrop() {
        this.getChildren().remove(1);
    }
}
