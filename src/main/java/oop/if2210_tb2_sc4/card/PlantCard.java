package oop.if2210_tb2_sc4.card;

public class PlantCard extends FarmResourceCard {

    private int age;
    private int harvestAge;

    public PlantCard(String name, String image_path) {
        super(name, image_path);
        age = 0;
    }

    public PlantCard(String name, String image_path, int age, int harvestAge) {
        super(name, image_path);
        this.age = age;
        this.harvestAge = harvestAge;
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
