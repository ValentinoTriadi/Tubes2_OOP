package oop.if2210_tb2_sc4.card;

public class InstantHarvestCard extends GoodPotion {
    public InstantHarvestCard(String name) {
        super(name);
    }

    public InstantHarvestCard(InstantHarvestCard instantHarvestCard) {
        super(instantHarvestCard.getName());
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
