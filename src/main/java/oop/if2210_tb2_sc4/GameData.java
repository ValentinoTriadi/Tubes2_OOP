package oop.if2210_tb2_sc4;

import java.util.ArrayList;
import java.util.List;

import oop.if2210_tb2_sc4.card.*;

public class GameData {
    public static List<Card> allCards = new ArrayList<>();
    public static List<Card> deckCards = new ArrayList<>();

    public static void initCards() {
        // Create all cards
        setAllCards();
        addDeckCard();
    }

    private static void setAllCards(){
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
        allCards.add(new CarnivoreAnimal("HIU_DARAT", 0, 20, (ProductCard) getCard("SIRIP_HIU")));
        allCards.add(new HerbivoreAnimal("SAPI", 0, 10, (ProductCard) getCard("SUSU")));
        allCards.add(new HerbivoreAnimal("DOMBA", 0, 12, (ProductCard) getCard("DAGING_DOMBA")));
        allCards.add(new HerbivoreAnimal("KUDA", 0, 14, (ProductCard) getCard("DAGING_KUDA")));
        allCards.add(new OmnivoreAnimal("AYAM", 0, 5,  (ProductCard) getCard("TELUR")));
        allCards.add(new OmnivoreAnimal("BERUANG", 0, 25,  (ProductCard) getCard("DAGING_BERUANG")));

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

    private static void addDeckCard(){

        // ANIMAL
        deckCards.add(new CarnivoreAnimal("HIU_DARAT", 0, 20, (ProductCard) getCard("SIRIP_HIU")));
        deckCards.add(new HerbivoreAnimal("SAPI", 0, 10, (ProductCard) getCard("SUSU")));
        deckCards.add(new HerbivoreAnimal("DOMBA", 0, 12, (ProductCard) getCard("DAGING_DOMBA")));
        deckCards.add(new HerbivoreAnimal("KUDA", 0, 14, (ProductCard) getCard("DAGING_KUDA")));
        deckCards.add(new OmnivoreAnimal("AYAM", 0, 5,  (ProductCard) getCard("TELUR")));

        // PLANT
        deckCards.add(new PlantCard("BIJI_JAGUNG", 0, 3, (ProductCard) getCard("JAGUNG")));
        deckCards.add(new PlantCard("BIJI_LABU", 0, 5, (ProductCard) getCard("LABU")));
        deckCards.add(new PlantCard("BIJI_STROBERI", 0, 4, (ProductCard) getCard("STROBERI")));

        // PRODUCT
        deckCards.add(new ProductCard("SUSU", 100, 4));
        deckCards.add(new ProductCard("DAGING_DOMBA", 100, 6));
        deckCards.add(new ProductCard("TELUR", 50, 2));

        // ITEM
        deckCards.add(new DelayCard("DELAY"));
        deckCards.add(new AccelerateCard("ACCELERATE"));
        deckCards.add(new ProtectCard("PROTECTION"));
        deckCards.add(new TrapCard("TRAP"));
        deckCards.add(new InstantHarvestCard("INSTANT HARVEST"));
        deckCards.add(new DestroyCard("DESTROY"));
    }

    public static void ResetData(){
        allCards.clear();
    }

    public static List<Card> getAllCards() {
        return allCards;
    }

    public static List<Card> getDeckCards() {
        return deckCards;
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

    public static Card returnCard(Card card) {
        if (card instanceof CarnivoreAnimal carnivore) {
            return new CarnivoreAnimal(carnivore.getName(), carnivore.getWeight(), carnivore.getHarvestWeight(), carnivore.getProductResult());
        } else if (card instanceof HerbivoreAnimal herbivore) {
            return new HerbivoreAnimal(herbivore.getName(), herbivore.getWeight(), herbivore.getHarvestWeight(), herbivore.getProductResult());
        } else if (card instanceof OmnivoreAnimal omnivore) {
            return new OmnivoreAnimal(omnivore.getName(), omnivore.getWeight(), omnivore.getHarvestWeight(), omnivore.getProductResult());
        } else if (card instanceof PlantCard plant) {
            return new PlantCard(plant.getName(), plant.getAge(), plant.getHarvestAge(), plant.getProductResult());
        } else if (card instanceof ProductCard product) {
            return new ProductCard(product.getName(), product.getPrice(), product.getWeightAddition());
        } else if (card instanceof DelayCard) {
            return new DelayCard(card.getName());
        } else if (card instanceof AccelerateCard) {
            return new AccelerateCard(card.getName());
        } else if (card instanceof ProtectCard) {
            return new ProtectCard(card.getName());
        } else if (card instanceof TrapCard) {
            return new TrapCard(card.getName());
        } else if (card instanceof InstantHarvestCard) {
            return new InstantHarvestCard(card.getName());
        } else if (card instanceof DestroyCard) {
            return new DestroyCard(card.getName());
        }
        // Add more else if clauses for any other specific card types
        return new Card(card.getName());
    }

    public GameData() {
        initCards();   
    }
}
