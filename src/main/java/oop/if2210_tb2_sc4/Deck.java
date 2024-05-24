package oop.if2210_tb2_sc4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.card.Card;

import static oop.if2210_tb2_sc4.GameData.getAllCards;

public class Deck {
    public static final int DECK_SIZE = 40;
    public static final int HAND_SIZE = 6;
    public static final int GENERATED_CARD_COUNT = 4;

    private final List<Card> currentDeck;
    private final Card[] activeCards;

    private int cardsInDeck;
    private int cardsInHand;

    public Deck() {
        activeCards = new Card[HAND_SIZE];
        currentDeck = new ArrayList<>();
        cardsInDeck = 0;
    }

    //Deck in terms of hands
    public Card[] getActiveCards() {
        return activeCards;
    }

    public List<Card> getCurrentDeck() {
        return currentDeck;
    }

    public List<Card> generateCards() {
        Set<Card> cards = new HashSet<>();

        if(currentDeck.isEmpty()){
            return null;
        }

        int amountToGenerate = Math.min(GENERATED_CARD_COUNT, getCardsInDeckCount());

        // Get 4 random cards
        while(cards.size() < amountToGenerate){
            int randomIndex = (int) (Math.random() * currentDeck.size());
            cards.add(currentDeck.get(randomIndex));
        }
        return cards.stream().toList();
    }

    public Deck initializeDeck(Deck deck){
        List<Card> allCards = GameData.getDeckCards();
        int counter = 0;
        while(!(deck.isDeckFull())){
            Card newCard = GameData.returnCard(allCards.get(counter));
            if(deck.getCurrentDeck().contains(newCard)){
                counter--;
                continue;
            }
            deck.addCardToDeck(newCard);
            counter++;
            counter %= allCards.size();
        }
        return deck;
    }

    public void addCardToDeck(Card card) {
        cardsInDeck++;
        currentDeck.add(card);
    }

    public void setActiveCard(int index, Card card) {
        if(activeCards[index] == null){
            cardsInHand++;
        }
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
        if(activeCards[index] == null){
            return;
        }
        activeCards[index] = null;
        cardsInHand--;
    }

    public void removeCardFromDeck(Card c){
        cardsInDeck--;
        currentDeck.remove(c);
    }
        
    public void addActiveCard(Card card) throws FullActiveHandsException {
        if(isHandFull()){
            throw new FullActiveHandsException();
        }
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

    public boolean isDeckEmpty(){
        return getCardsInDeckCount() == 0;
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

    public int getMaxCards(){
        return DECK_SIZE;
    }

    public boolean isHandFull(){
        return this.cardsInHand == HAND_SIZE;
    }
    public boolean isDeckFull(){
        return this.currentDeck.size() == DECK_SIZE;
    }
}
