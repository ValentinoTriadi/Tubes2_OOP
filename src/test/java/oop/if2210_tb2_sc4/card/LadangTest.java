package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LadangTest {

    @Test
    void getLadang() {
        Ladang ladang = new Ladang();
        FarmResourceCard[][] farmResourceCards = ladang.getLadang();
        assertEquals(4, farmResourceCards.length);
        assertEquals(5, farmResourceCards[0].length);
    }

    @Test
    void setLadang() {
        Ladang ladang = new Ladang();
        FarmResourceCard[][] farmResourceCards = new FarmResourceCard[4][5];
        ladang.setLadang(farmResourceCards);
        assertEquals(farmResourceCards, ladang.getLadang());
    }

    @Test
    void getCard() {
        Ladang ladang = new Ladang();
        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource", "image_path");
        ladang.setCard(0, 0, farmResourceCard);
        assertEquals(farmResourceCard, ladang.getCard(0, 0));
    }

    @Test
    void setCard() {
        Ladang ladang = new Ladang();
        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource", "image_path");
        ladang.setCard(0, 0, farmResourceCard);
        assertEquals(farmResourceCard, ladang.getCard(0, 0));
    }

    @Test
    void removeCard() {
        Ladang ladang = new Ladang();
        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource", "image_path");
        ladang.setCard(0, 0, farmResourceCard);
        ladang.removeCard(0, 0);
        assertNull(ladang.getCard(0, 0));
    }

    @Test
    void isFull() {
        Ladang ladang = new Ladang();
        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource", "image_path");
        for (int i = 0; i < Ladang.LADANG_ROW; i++) {
            for (int j = 0; j < Ladang.LADANG_COLUMN; j++) {
                ladang.setCard(i, j, farmResourceCard);
            }
        }
        assertTrue(ladang.isFull());
    }

    @Test
    void isEmpty() {
        Ladang ladang = new Ladang();
        assertTrue(ladang.isEmpty());
    }
}