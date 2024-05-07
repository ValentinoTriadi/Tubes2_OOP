package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.Pane;

public class DeckUI extends Pane {

    CardHolder[] cardHolder;

    public DeckUI() {
        this.cardHolder = GameWindowController.activeDeck;
    }

    public boolean isDeckEmpty() {
        for (CardHolder ch : cardHolder) {
            if (ch.getChildren().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int index) {
        return cardHolder[index].getChildren().isEmpty();
    }

    public int getCardCount() {
        int count = 0;
        for (CardHolder ch : cardHolder) {
            if (!ch.getChildren().isEmpty()) {
                count++;
            }
        }
        return count;
    }
}
