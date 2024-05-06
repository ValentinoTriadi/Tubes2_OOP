package oop.if2210_tb2_sc4.dropzone;

import javafx.scene.layout.Pane;

public class dropzone extends Pane {

    private final int x,y;

    public dropzone(int x, int y) {
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
