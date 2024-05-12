package oop.if2210_tb2_sc4.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.util.StringUtil;

public class Shop {
    Map<ProductCard, Integer> cardStock;

    public Shop() {
        this.cardStock = new HashMap<>();

        // Initialize card stock to 0
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Sirip Hiu")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Susu")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Daging Domba")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Daging Kuda")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Telur")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Daging Beruang")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Jagung")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Labu")), 0);
        cardStock.put((ProductCard) GameData.getCard(StringUtil.toUpperSnakeCase("Stroberi")), 0);
    }

    public Shop(Map<ProductCard, Integer> cardStock) {
        this.cardStock = cardStock;
    }

    public Shop(Shop shop) {
        this.cardStock = shop.getCardStock();
    }

    public void addCard(ProductCard card, int stock) {
        if (cardStock.get(card) != null) {
            cardStock.put(card, cardStock.get(card) + stock);
        } else {
            cardStock.put(card, stock);
        }
        cardStock.remove(null);
    }

    public void addCard(ProductCard card) {
        addCard(card, 1);
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

    public void sellCardToShop(ProductCard card) {
        cardStock.put(card, cardStock.get(card) + 1);
    }

    public int sellCardToShop(ProductCard card, int stock) {
        cardStock.put(card, cardStock.get(card) + stock);
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
