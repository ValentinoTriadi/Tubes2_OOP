package oop.if2210_tb2_sc4.card;

public class DelayCard extends ItemCard{
    public DelayCard(String name) {
        super(name);
    }

    public DelayCard(DelayCard delayCard) {
        super(delayCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (card.getEffect().contains(EffectType.PROTECT)) {
            card.removeEffect(EffectType.PROTECT);
        } else {
            card.addEffect(EffectType.DELAY);
        }
    }

}
