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
    requires annotations;
    requires com.fasterxml.jackson.core;
    requires json.simple;

    exports oop.if2210_tb2_sc4.UI;
    opens oop.if2210_tb2_sc4.UI to javafx.fxml;

    opens oop.if2210_tb2_sc4 to javafx.fxml;
    exports oop.if2210_tb2_sc4.game_manager;
    exports oop.if2210_tb2_sc4.player;
    exports oop.if2210_tb2_sc4;
    exports oop.if2210_tb2_sc4.card;
    exports oop.if2210_tb2_sc4.deck;
    exports oop.if2210_tb2_sc4.ladang;
    exports oop.if2210_tb2_sc4.shop;
    opens oop.if2210_tb2_sc4.card to javafx.fxml;
    exports oop.if2210_tb2_sc4.save_load;
    opens oop.if2210_tb2_sc4.save_load to javafx.fxml;
}