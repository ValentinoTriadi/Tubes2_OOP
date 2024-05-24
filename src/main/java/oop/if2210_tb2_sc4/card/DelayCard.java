package oop.if2210_tb2_sc4.card;

import static java.lang.Math.max;

public class DelayCard extends BadPotion {
    public DelayCard(String name) {
        super(name);
    }

    public DelayCard(DelayCard delayCard) {
        super(delayCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (!card.getEffect().contains(EffectType.PROTECT)) {

            if (card instanceof PlantCard) {
                int age = max(((PlantCard) card).getAge() - 2, 0);
                ((PlantCard) card).setAge(age);
            }

            if (card instanceof AnimalCard) {
                int weight = max(((AnimalCard) card).getWeight() - 5, 0);
                ((AnimalCard) card).setWeight(weight);
            }

            card.addEffect(EffectType.DELAY);
        }
    }

}
