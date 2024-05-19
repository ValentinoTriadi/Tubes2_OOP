package oop.if2210_tb2_sc4;

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
    public static List<Card> allCards = new ArrayList<>();

    public static void initCards() {
        // Create all cards

        // PRODUCT
        allCards.add(new ProductCard("SIRIP_HIU", 500, 12));
        allCards.add(new ProductCard("SUSU", 100, 4));
        allCards.add(new ProductCard("DAGING_DOMBA", 100, 6));
        allCards.add(new ProductCard("DAGING_KUDA", 150, 8));
        allCards.add(new ProductCard("TELUR", 50, 2));
        allCards.add(new ProductCard("DAGING_BERUANG", 500, 12));
        allCards.add(new ProductCard("JAGUNG", 150, 3));
        allCards.add(new ProductCard("LABU", 500, 10));
        allCards.add(new ProductCard("STROBERI", 350, 5));


        // ANIMAL
        allCards.add(new AnimalCard("HIU_DARAT", 0, 20, AnimalType.CARNIVORE, (ProductCard) getCard("SIRIP_HIU")));
        allCards.add(new AnimalCard("SAPI", 0, 10, AnimalType.HERBIVORE, (ProductCard) getCard("SUSU")));
        allCards.add(new AnimalCard("DOMBA", 0, 12, AnimalType.HERBIVORE, (ProductCard) getCard("DAGING_DOMBA")));
        allCards.add(new AnimalCard("KUDA", 0, 14, AnimalType.HERBIVORE, (ProductCard) getCard("DAGING_KUDA")));
        allCards.add(new AnimalCard("AYAM", 0, 5, AnimalType.OMNIVORE, (ProductCard) getCard("TELUR")));
        allCards.add(new AnimalCard("BERUANG", 0, 25, AnimalType.OMNIVORE, (ProductCard) getCard("DAGING_BERUANG")));

        // PLANT
        allCards.add(new PlantCard("BIJI_JAGUNG", 0, 3, (ProductCard) getCard("JAGUNG")));
        allCards.add(new PlantCard("BIJI_LABU", 0, 5, (ProductCard) getCard("LABU")));
        allCards.add(new PlantCard("BIJI_STROBERI", 0, 4, (ProductCard) getCard("STROBERI")));


        // ITEM
        allCards.add(new DelayCard("DELAY"));
        allCards.add(new AccelerateCard("ACCELERATE"));
        allCards.add(new ProtectCard("PROTECTION"));
        allCards.add(new TrapCard("TRAP"));
        allCards.add(new InstantHarvestCard("INSTANT HARVEST"));
        allCards.add(new DestroyCard("DESTROY"));
    }

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static Card createCard(String name) {
        for (Card card : allCards) {
            if (card.getName().equals(name)) {
                return returnCard(card);
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

    private static Card returnCard(Card card){
        if (card instanceof AnimalCard){
            return (AnimalCard) card;
        } else if (card instanceof PlantCard){
            return (PlantCard) card;
        } else if (card instanceof ProductCard){
            return (ProductCard) card;
        }
        return card;
    }

    public GameData() {
        initCards();   
    }
}
