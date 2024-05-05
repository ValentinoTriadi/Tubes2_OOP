package oop.if2210_tb2_sc4.card;

/**
 * Base class for animal card object
 */
public class AnimalCard extends Card {

    private int weight;

    public AnimalCard(String name, String image_path) {
        super(name, image_path);
        weight = 0;
    }

    public AnimalCard(String name, String image_path, int weight) {
        super(name, image_path);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
