package oop.if2210_tb2_sc4.card;

public class OmnivoreAnimal extends AnimalCard implements Eatable {
    public OmnivoreAnimal(String name, int weight, int harvestWeight, ProductCard result){
        super(name, weight, harvestWeight, AnimalType.OMNIVORE, result);
    }
    public boolean isFoodSuitable(ProductCard food) {
        String[] foods = {"JAGUNG", "LABU", "STROBERI","DAGING_BERUANG", "DAGING_DOMBA", "DAGING_KUDA", "SIRIP_HIU", "TELUR", "SUSU"};
        for (String Omnifood : foods) {
            if (food.getName().equals(Omnifood)) {
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
