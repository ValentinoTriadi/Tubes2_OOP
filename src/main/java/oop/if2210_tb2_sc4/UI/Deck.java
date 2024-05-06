package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;

public class Deck extends Pane {

    CardHolder[] cardHolder;

    public Deck(CardHolder[] cardHolder) {
        this.cardHolder = cardHolder;
    }
}
