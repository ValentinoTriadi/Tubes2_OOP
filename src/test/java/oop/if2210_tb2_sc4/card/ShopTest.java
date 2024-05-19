package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

    @Test
    void addCard() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard,4);
        ProductCard productCard2 = new ProductCard("Product2", "image_path", 1, 4);
        shop.addCard(productCard2,4);
        ProductCard productCard3 = new ProductCard("Product3", "image_path", 1, 4);
        shop.addCard(productCard3,4);
        assertEquals(3, shop.getCardStock().size());
    }

    @Test
    void testAddCard() {
        Shop shop = new Shop();
        PlantCard plantCard = new PlantCard("Plant", "image_path", 0, 4);
        shop.addCard(plantCard.getProductResult());
        assertEquals(1, shop.getCardStock().size());
    }

    @Test
    void removeCard() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard,4);
        shop.removeCard(productCard);
        assertEquals(3, shop.getCardStock().get(productCard));
    }

    @Test
    void testRemoveCard() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard,4);
        shop.removeCard(productCard, 2);
        assertEquals(2, shop.getCardStock().get(productCard));
    }

    @Test
    void getCardStock() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard,4);
        assertEquals(4, shop.getCardStock().get(productCard));
    }

    @Test
    void sellCardToShop() {
        int money = 100;
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard,4);
        money = money + shop.sellCardToShop(productCard);
        assertEquals(101, money);
    }

    @Test
    void testSellCardToShop() {
        int money = 100;
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        money = money + shop.sellCardToShop(productCard,4);
        assertEquals(104, money);
    }

    @Test
    void buyCardFromShop() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard, 4);
        ProductCard boughtCard = shop.buyCardFromShop(productCard);
        assertEquals(productCard.getName(), boughtCard.getName());
    }

    @Test
    void testBuyCardFromShop() {
        Shop shop = new Shop();
        ProductCard productCard = new ProductCard("Product", "image_path", 1, 4);
        shop.addCard(productCard, 4);
        ProductCard boughtCard = shop.buyCardFromShop(productCard, 4).get(0);
        assertEquals(productCard.getName(), boughtCard.getName());
    }
}