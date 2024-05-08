package oop.if2210_tb2_sc4.card;

public class InstantHarvestCard extends ItemCard{
    public InstantHarvestCard(String name, String image_path) {
        super(name, image_path);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (card instanceof PlantCard plantCard) {
            plantCard.setAge(plantCard.getHarvestAge());
        }

        if (card instanceof AnimalCard) {
            AnimalCard animalCard = (AnimalCard) card;
            animalCard.setWeight(animalCard.getHarvestWeight());
        }
    }
}
