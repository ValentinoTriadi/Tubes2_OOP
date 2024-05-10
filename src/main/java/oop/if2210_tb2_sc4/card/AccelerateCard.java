package oop.if2210_tb2_sc4.card;

public class AccelerateCard extends ItemCard{
    
    public AccelerateCard(String name) {
        super(name);
    }

    public AccelerateCard(AccelerateCard accelerateCard) {
        super(accelerateCard.name);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        card.addEffect(EffectType.ACCELERATE);
    }
}
