package oop.if2210_tb2_sc4.card;

/**
 * Base class for animal card object
 */
public class AnimalCard extends FarmResourceCard {

    private int weight;
    private AnimalType type;
    private int harvestWeight;

    public AnimalCard(String name) {
        super(name);
        weight = 0;
        type = AnimalType.OMNIVORE; // Default type is set to "omnivore"
    }

    public AnimalCard(String name, int weight, int harvestWeight, AnimalType type, ProductCard result) {
        super(name, result);
        this.weight = weight;
        this.type = type;
        this.harvestWeight = harvestWeight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public int getHarvestWeight() {
        return harvestWeight;
    }

    public void setHarvestWeight(int harvestWeight) {
        this.harvestWeight = harvestWeight;
    }

    public boolean isHarvestable() {
        return weight >= harvestWeight || this.isInstantHarvest();
    }

    public boolean isFoodSuitable(ProductCard food) {
        String[] herbivoreFoodStrings = {"Jagung", "Labu", "Stroberi"};
        String[] carnivoreFoodStrings = {"Daging Beruang", "Daging Domba", "Daging Kuda", "Sirip Hiu", "Telur", "Susu"};

        if (type == AnimalType.HERBIVORE) {
            for (String herbivoreFoodString : herbivoreFoodStrings) {
                if (food.getName().equals(herbivoreFoodString)) {
                    return true;
                }
            }
        } else if (type == AnimalType.CARNIVORE) {
            for (String carnivoreFoodString : carnivoreFoodStrings) {
                if (food.getName().equals(carnivoreFoodString)) {
                    return true;
                }
            }
        } else {
            return true;
        }

        return false;
    }

    public boolean feed(ProductCard food) {
        if (isFoodSuitable(food)) {
            weight += food.getWeightAddition();
            return true;
        }

        return false;
    }
}



