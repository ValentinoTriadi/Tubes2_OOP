package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.HBox;

public class DeckUI extends HBox {

    private final CardHolder[] activeDeck;

    public DeckUI() {
        this.activeDeck = new CardHolder[6];
        this.setSpacing(20);
    }

    public void initializeActiveDeck() {
        // Initialize activeDeck
        for (int i = 0; i < 6; i++) {
            activeDeck[i] = new CardHolder();
            activeDeck[i].setPrefSize(100, 150);
            activeDeck[i].setStyle("-fx-background-color: cyan;");
            this.getChildren().add(activeDeck[i]);
        }
    }

    public boolean isDeckEmpty() {
        for (CardHolder ch : activeDeck) {
            if (ch.getChildren().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty(int index) {
        return activeDeck[index].getChildren().isEmpty();
    }

    public int getCardCount() {
        int count = 0;
        for (CardHolder ch : activeDeck) {
            if (!ch.getChildren().isEmpty()) {
                count++;
            }
        }
        return count;
    }

    public void addCard(CardUI card) {
        for (int i = 0; i < 6; i++) {
            if (activeDeck[i].getChildren().isEmpty()) {
                card.setParent(activeDeck[i]);
                break;
            }
        }
    }
}