package oop.if2210_tb2_sc4.card;

public class AccelerateCard extends ItemCard{
    
    public AccelerateCard(String name, String image_path) {
        super(name, image_path);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (card instanceof PlantCard) {
            PlantCard plantCard = (PlantCard) card;
            plantCard.setAge(plantCard.getAge() + 2);
        }

        if (card instanceof AnimalCard) {
            AnimalCard animalCard = (AnimalCard) card;
            animalCard.setWeight(animalCard.getWeight() + 8);
        }
    }
}
