package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void initCards() {
        Deck deck = new Deck();
        List<Card> allCards = new ArrayList<>();
        Deck.initCards();
        assertEquals(Deck.allCards, allCards);
    }

    @Test
    void getActiveCards() {
        Deck deck = new Deck();
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }

    @Test
    void getCurrentDeck() {
        Deck deck = new Deck();
        List<Card> currentDeck = new ArrayList<>();
        assertEquals(deck.getCurrentDeck(), currentDeck);
    }

    @Test
    void generateCards() {
        Deck deck = new Deck();
        Deck.initCards();

        Card card = new Card("Card", "image_path");
        deck.addCardToDeck(card);
        card = new Card("Card2", "image_path2");
        deck.addCardToDeck(card);
        card = new Card("Card3", "image_path3");
        deck.addCardToDeck(card);
        card = new Card("Card4", "image_path4");
        deck.addCardToDeck(card);

        assertEquals(deck.generateCards().size(), 4);
    }

    @Test
    void addCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        deck.addCardToDeck(card);
        List<Card> currentDeck = new ArrayList<>();
        currentDeck.add(card);
        assertEquals(deck.getCurrentDeck(), currentDeck);
    }

    @Test
    void testAddCardToDeck() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        List<Card> cards = new ArrayList<>();
        cards.add(card);
        deck.addCardToDeck(cards);
        List<Card> currentDeck = new ArrayList<>();
        currentDeck.add(card);
        assertEquals(deck.getCurrentDeck(), currentDeck);
    }

    @Test
    void setActiveCards() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        activeCards[0] = card;
        deck.setActiveCards(activeCards);
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }

    @Test
    void setActiveCard() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        deck.setActiveCard(0, card);
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        activeCards[0] = card;
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }

    @Test
    void testSetActiveCard() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        deck.setActiveCard("A", card);
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        activeCards[0] = card;
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }

    @Test
    void removeActiveCard() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        deck.setActiveCard(0, card);
        deck.removeActiveCard(0);
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }

    @Test
    void addActiveCard() {
        Deck deck = new Deck();
        Card card = new Card("Card", "image_path");
        deck.addActiveCard(card);
        Card[] activeCards = new Card[Deck.HAND_SIZE];
        activeCards[0] = card;
        assertArrayEquals(deck.getActiveCards(), activeCards);
    }
}