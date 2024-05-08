package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCardTest {

    @Test
    void getPrice() {
        ProductCard productCard = new ProductCard("name", "image_path", 100, 10);
        assertEquals(100, productCard.getPrice());
    }

    @Test
    void getWeightAddition() {
        ProductCard productCard = new ProductCard("name", "image_path", 100, 10);
        assertEquals(10, productCard.getWeightAddition());
    }
}