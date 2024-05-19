package oop.if2210_tb2_sc4.card;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Shop {
    Map<ProductCard, Integer> cardStock;

    public Shop() {
        this.cardStock = new HashMap<>();
    }

    public Shop(Map<ProductCard, Integer> cardStock) {
        this.cardStock = cardStock;
    }

    public void addCard(ProductCard card, int stock) {
        if (cardStock.containsKey(card)) {
            cardStock.put(card, cardStock.get(card) + stock);
        } else {
            cardStock.put(card, stock);
        }
    }

    public void addCard(ProductCard card) {
        if (cardStock.containsKey(card)) {
            cardStock.put(card, cardStock.get(card) + 1);
        } else {
            cardStock.put(card, 1);
        }
    }

    public void removeCard(ProductCard card) {
        if (cardStock.get(card) == 0) {
            throw new IllegalArgumentException("Card stock is empty");
        }

        cardStock.put(card, cardStock.get(card) - 1);
    }

    public void removeCard(ProductCard card, int stock) {
        if (cardStock.get(card) < stock) {
            throw new IllegalArgumentException("Card stock is not enough");
        }

        cardStock.put(card, cardStock.get(card) - stock);
    }

    public Map<ProductCard, Integer> getCardStock() {
        return cardStock;
    }

    public int sellCardToShop(ProductCard card) {
        try {
            if (cardStock.get(card) == 0) {
                throw new IllegalArgumentException("Card stock is empty");
            } else {
                return card.getPrice();
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int sellCardToShop(ProductCard card, int stock) {
        if (cardStock.containsKey(card)) {
            cardStock.put(card, cardStock.get(card) + stock);
        } else {
            cardStock.put(card, stock);
        }
        return card.getPrice() * stock;
    }

    public ProductCard buyCardFromShop(ProductCard card) {
        if (cardStock.get(card) == 0) {
            throw new IllegalArgumentException("Card stock is empty");
        }

        cardStock.put(card, cardStock.get(card) - 1);
        return new ProductCard(card);
    }

    public List<ProductCard> buyCardFromShop(ProductCard card, int stock) {
        if (cardStock.get(card) < stock) {
            throw new IllegalArgumentException("Card stock is not enough");
        }

        cardStock.put(card, cardStock.get(card) - stock);
        return List.of(new ProductCard(card));
    }
}
