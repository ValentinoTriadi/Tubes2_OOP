package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getName() {
        Card card = new Card("Card");
        assertEquals("Card", card.getName());
    }

}