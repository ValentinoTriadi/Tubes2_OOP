package oop.if2210_tb2_sc4.card;

/**
 * Base class for animal card object
 */
public abstract class AnimalCard extends FarmResourceCard {

    protected int weight;
    protected AnimalType type;
    protected int harvestWeight;

    public AnimalCard(String name) {
        super(name);
        weight = 0;
        type = AnimalType.OMNIVORE; // Default type is set to "omnivore"
    }

    public AnimalCard(AnimalCard animalCard) {
        super(animalCard.getName(), animalCard.productResult);
        weight = animalCard.getWeight();
        type = animalCard.getType();
        harvestWeight = animalCard.getHarvestWeight();
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

    public abstract boolean isFoodSuitable(ProductCard food);

    public abstract boolean feed(ProductCard food);
}



