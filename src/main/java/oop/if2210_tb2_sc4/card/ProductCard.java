package oop.if2210_tb2_sc4.card;

public class ProductCard extends Card{

    private final int price;
    private final int weightAddition;

    public ProductCard(String name, String image_path) {
        super(name, image_path);
        price = 0;
        weightAddition = 0;
    }

    public ProductCard(String name, String image_path, int price, int weightAddition) {
        super(name, image_path);
        this.price = price;
        this.weightAddition = weightAddition;
    }

    public int getPrice() {
        return price;
    }

    public int getWeightAddition() {
        return weightAddition;
    }
}
