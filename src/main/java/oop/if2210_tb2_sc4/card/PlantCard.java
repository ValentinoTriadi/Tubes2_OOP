package oop.if2210_tb2_sc4.card;

public class PlantCard extends Card {

    private int age;

    public PlantCard(String name, String image_path) {
        super(name, image_path);
        age = 0;
    }

    public PlantCard(String name, String image_path, int age) {
        super(name, image_path);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
