package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    @Test
    void getName() {
        Card card = new Card("Card", "image_path");
        assertEquals("Card", card.getName());
    }

    @Test
    void getImagePath() {
        Card card = new Card("Card", "image_path");
        assertEquals("image_path", card.getImagePath());
    }
}