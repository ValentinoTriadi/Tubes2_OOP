package oop.if2210_tb2_sc4.game_manager;

import java.util.ArrayList;
import java.util.List;

import oop.if2210_tb2_sc4.card.AccelerateCard;
import oop.if2210_tb2_sc4.card.AnimalCard;
import oop.if2210_tb2_sc4.card.AnimalType;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.DelayCard;
import oop.if2210_tb2_sc4.card.DestroyCard;
import oop.if2210_tb2_sc4.card.InstantHarvestCard;
import oop.if2210_tb2_sc4.card.PlantCard;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.card.ProtectCard;
import oop.if2210_tb2_sc4.card.TrapCard;

public class GameData {
    public static List<Card> allCards = new ArrayList<Card>();

    public static void initCards() {
        // Create all cards

        // PRODUCT
        allCards.add(new ProductCard("Sirip Hiu", 500, 12));
        allCards.add(new ProductCard("Susu", 100, 4));
        allCards.add(new ProductCard("Daging Domba", 100, 6));
        allCards.add(new ProductCard("Daging Kuda", 150, 8));
        allCards.add(new ProductCard("Telur", 50, 2));
        allCards.add(new ProductCard("Daging Beruang", 500, 12));
        allCards.add(new ProductCard("Jagung", 150, 3));
        allCards.add(new ProductCard("Labu", 500, 10));
        allCards.add(new ProductCard("Stroberi", 350, 5));


        // ANIMAL
        allCards.add(new AnimalCard("Hiu Darat", 0, 20, AnimalType.CARNIVORE, (ProductCard) getCard("Sirip Hiu")));
        allCards.add(new AnimalCard("Sapi", 0, 10, AnimalType.HERBIVORE, (ProductCard) getCard("Susu")));
        allCards.add(new AnimalCard("Domba", 0, 12, AnimalType.HERBIVORE, (ProductCard) getCard("Daging Domba")));
        allCards.add(new AnimalCard("Kuda", 0, 14, AnimalType.HERBIVORE, (ProductCard) getCard("Daging Kuda")));
        allCards.add(new AnimalCard("Ayam", 0, 5, AnimalType.OMNIVORE, (ProductCard) getCard("Telur")));
        allCards.add(new AnimalCard("Beruang", 0, 25, AnimalType.OMNIVORE, (ProductCard) getCard("Daging Beruang")));

        // PLANT
        allCards.add(new PlantCard("Biji Jagung", 0, 3, (ProductCard) getCard("Jagung")));
        allCards.add(new PlantCard("Biji Labu", 0, 5, (ProductCard) getCard("Labu")));
        allCards.add(new PlantCard("Biji Stroberi", 0, 4, (ProductCard) getCard("Stroberi")));


        // ITEM
        allCards.add(new DelayCard("Delay"));
        allCards.add(new AccelerateCard("Accelerate"));
        allCards.add(new ProtectCard("Protection"));
        allCards.add(new TrapCard("Trap"));
        allCards.add(new InstantHarvestCard("Instant Harvest"));
        allCards.add(new DestroyCard("Destroy"));
    }

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static Card createCard(String name) {
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                return new ProductCard((ProductCard) card);
            }
        }
        return null;
    }

    public static Card getCard(String name) {
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                return card;
            }
        }
        return null;
    }

    public GameData() {
        initCards();   
    }
}
