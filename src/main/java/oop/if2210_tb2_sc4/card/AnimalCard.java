package oop.if2210_tb2_sc4.card;

/**
 * Base class for animal card object
 */
public class AnimalCard extends FarmResourceCard {

    private int weight;
    private AnimalType type;
    private int harvestWeight;

    public AnimalCard(String name, String image_path) {
        super(name, image_path);
        weight = 0;
        type = AnimalType.OMNIVORE; // Default type is set to "omnivore"
    }

    public AnimalCard(String name, String image_path, int weight, int harvestWeight, AnimalType type) {
        super(name, image_path);
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
        return weight >= harvestWeight;
    }
}



