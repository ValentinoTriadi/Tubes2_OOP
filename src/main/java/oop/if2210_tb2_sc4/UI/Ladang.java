package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Ladang extends GridPane {

    public Ladang() {
        setAlignment(javafx.geometry.Pos.CENTER);
        setGridLinesVisible(true);
        setHgap(30.0);
        setPrefHeight(507.0);
        setPrefWidth(582.0);
        setVgap(15.0);

        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
            column.setMaxWidth(100.0);
            column.setMinWidth(50.0);
            column.setPrefWidth(50.0);
            getColumnConstraints().add(column);
        }

        for (int i = 0; i < 4; i++) {
            RowConstraints row = new RowConstraints();
            row.setMaxHeight(200.0);
            row.setMinHeight(10.0);
            row.setPrefHeight(100.0);
            row.setVgrow(javafx.scene.layout.Priority.SOMETIMES);
            getRowConstraints().add(row);
        }
    }
}