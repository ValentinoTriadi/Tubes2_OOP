module oop.if2210_tb2_sc4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens oop.if2210_tb2_sc4 to javafx.fxml;
    exports oop.if2210_tb2_sc4.game_manager;
    exports oop.if2210_tb2_sc4.player;
    exports oop.if2210_tb2_sc4;
    exports oop.if2210_tb2_sc4.card;
    opens oop.if2210_tb2_sc4.card to javafx.fxml;
}