package oop.if2210_tb2_sc4.card;

public class ProductCard extends Card{

    private final int price;
    private final int weightAddition;

    public ProductCard(String name) {
        super(name);
        price = 0;
        weightAddition = 0;
    }

    public ProductCard(ProductCard productCard) {
        super(productCard.getName());
        price = productCard.getPrice();
        weightAddition = productCard.getWeightAddition();
    }

    public ProductCard(String name, int price, int weightAddition) {
        super(name);
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
