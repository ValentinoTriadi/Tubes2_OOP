package oop.if2210_tb2_sc4.card;

public class PlantCard extends FarmResourceCard {

    private int age;
    private int harvestAge;

    public PlantCard(String name) {
        super(name);
        age = 0;
    }

    public PlantCard(String name, int age, int harvestAge, ProductCard result) {
        super(name, result);
        this.age = age;
        this.harvestAge = harvestAge;
    }

    public PlantCard(PlantCard plantCard) {
        super(plantCard.getName(), plantCard.productResult);
        this.age = plantCard.getAge();
        this.harvestAge = plantCard.getHarvestAge();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHarvestAge() {
        return harvestAge;
    }

    public void setHarvestAge(int harvestAge) {
        this.harvestAge = harvestAge;
    }

    public boolean isHarvestable() {
        return age >= harvestAge || this.isInstantHarvest();
    }
}
