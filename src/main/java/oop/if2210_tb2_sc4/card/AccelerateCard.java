package oop.if2210_tb2_sc4.card;

public class AccelerateCard extends GoodPotion{
    
    public AccelerateCard(String name) {
        super(name);
    }

    public AccelerateCard(AccelerateCard accelerateCard) {
        super(accelerateCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (card instanceof PlantCard) {
            ((PlantCard) card).setAge(((PlantCard) card).getAge() + 2);
        }

        if (card instanceof AnimalCard) {
            ((AnimalCard) card).setWeight(((AnimalCard) card).getWeight() + 8);
        }

        card.addEffect(EffectType.ACCELERATE);
    }
}
