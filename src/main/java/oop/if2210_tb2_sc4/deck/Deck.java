package oop.if2210_tb2_sc4.deck;

import java.util.ArrayList;
import java.util.List;
import oop.if2210_tb2_sc4.card.Card;

public class Deck {
    public static final int DECK_SIZE = 40;
    public static final int HAND_SIZE = 6;
    public static final int GENERATED_CARD_COUNT = 4;

    private List<Card> currentDeck;
    private Card[] activeCards;

    private int cardsInDeck;
    private int cardsInHand;

    public Deck() {
        activeCards = new Card[HAND_SIZE];
        currentDeck = new ArrayList<Card>();
        cardsInDeck = DECK_SIZE;
    }

    public Card[] getActiveCards() {
        return activeCards;
    }

    public List<Card> getCurrentDeck() {
        return currentDeck;
    }

    public List<Card> generateCards() {
        List<Card> cards = new ArrayList<Card>();
        
        // Get 4 random cards
        for (int i = 0; i < GENERATED_CARD_COUNT; i++) {
            int randomIndex = (int) (Math.random() * currentDeck.size());
            cards.add(currentDeck.get(randomIndex));
            currentDeck.remove(randomIndex);

        }

        cardsInDeck -= GENERATED_CARD_COUNT;
        return cards;
    }

    public void addCardToDeck(Card card) {
        cardsInDeck++;
        currentDeck.add(card);
    }

    public void addCardToDeck(List<Card> cards) {
        for (Card card : cards) {
            cardsInDeck++;
            currentDeck.add(card);
        }
    }

    public void setActiveCards(Card[] activeCards) {
        if (activeCards.length != HAND_SIZE) {
            throw new IllegalArgumentException("Active cards must be of size " + HAND_SIZE);
        }

        for (int i = 0; i < HAND_SIZE; i++) {
            this.activeCards[i] = activeCards[i];
        }
    }

    public void setActiveCard(int index, Card card) {
        activeCards[index] = card;
    }

    public void setActiveCard(String slot, Card card) {
        int index = slot.charAt(0) - 'A';

        if (index < 0 || index >= HAND_SIZE) {
            throw new IllegalArgumentException("Invalid slot " + slot);
        }

        activeCards[index] = card;
    }

    public void removeActiveCard(int index) {
        activeCards[index] = null;
        cardsInHand--;
    }
        
    public void addActiveCard(Card card) {
        for (int i = 0; i < HAND_SIZE; i++) {
            if (activeCards[i] == null) {
                activeCards[i] = card;
                cardsInHand++;
                return;
            }
        }
    }

    public int getCardsInDeckCount() {
        return cardsInDeck;
    }

    public void setCardsInDeckCount(int cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public int getActiveCardinHandCount() {
        return cardsInHand;
    }

    public void setCardsInHandCount(int cardsInHand) {
        this.cardsInHand = cardsInHand;
    }

    
}
