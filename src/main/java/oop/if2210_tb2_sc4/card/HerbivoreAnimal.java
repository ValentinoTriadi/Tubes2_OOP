package oop.if2210_tb2_sc4.card;

public class HerbivoreAnimal extends AnimalCard{
    public HerbivoreAnimal(String name, int weight, int harvestWeight, ProductCard result){
        super(name, weight, harvestWeight, AnimalType.HERBIVORE, result);
    }

    public boolean isFoodSuitable(ProductCard food) {
        String[] herbivoreFoodStrings = {"JAGUNG", "LABU", "STROBERI"};
        for (String herbivoreFoodString : herbivoreFoodStrings) {
            if (food.getName().equals(herbivoreFoodString)) {
                return true;
            }
        }
        return false;
    }

    public boolean feed(ProductCard food) {
        if (isFoodSuitable(food)) {
            int tempWeight = getWeight();
            setWeight(tempWeight + food.getWeightAddition());
            return true;
        }

        return false;

    }
}
