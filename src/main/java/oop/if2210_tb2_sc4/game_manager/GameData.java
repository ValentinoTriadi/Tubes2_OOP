package oop.if2210_tb2_sc4.game_manager;

import java.util.ArrayList;
import java.util.List;

import oop.if2210_tb2_sc4.card.AnimalCard;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.DelayCard;
import oop.if2210_tb2_sc4.card.PlantCard;
import oop.if2210_tb2_sc4.card.ProductCard;

public class GameData {
    public static List<Card> allCards = new ArrayList<Card>();

    public static void initCards() {
        // Create all cards

        // PRODUCT
        allCards.add(new ProductCard("Sirip Hiu", null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));
        allCards.add(new ProductCard(null, null));

        // ANIMAL
        allCards.add(new AnimalCard(null, null));
        allCards.add(new AnimalCard(null, null));
        allCards.add(new AnimalCard(null, null));
        allCards.add(new AnimalCard(null, null));
        allCards.add(new AnimalCard(null, null));
        allCards.add(new AnimalCard(null, null));

        // PLANT
        allCards.add(new PlantCard(null, null));
        allCards.add(new PlantCard(null, null));
        allCards.add(new PlantCard(null, null));

        // ITEM
        allCards.add(new DelayCard(null, null));
        allCards.add(new DelayCard(null, null));
        allCards.add(new DelayCard(null, null));
        allCards.add(new DelayCard(null, null));
        allCards.add(new DelayCard(null, null));
        allCards.add(new DelayCard(null, null));
    }

    public GameData() {
        // Create all cards
    }
}
