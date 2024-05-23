package oop.if2210_tb2_sc4.card;

public class CarnivoreAnimal extends AnimalCard{
    public CarnivoreAnimal(String name, int weight, int harvestWeight, ProductCard result){
        super(name, weight, harvestWeight, AnimalType.CARNIVORE, result);
    }

    public boolean isFoodSuitable(ProductCard food) {
        String[] carnivoreFoodStrings = {"DAGING_BERUANG", "DAGING_DOMBA", "DAGING_KUDA", "SIRIP_HIU", "TELUR", "SUSU"};
        for (String carnivoreFoodString : carnivoreFoodStrings) {
            if (food.getName().equals(carnivoreFoodString)) {
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
