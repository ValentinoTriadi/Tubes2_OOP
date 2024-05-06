package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;

public class DropZone extends Pane {

    private final int x,y;

    public DropZone(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
