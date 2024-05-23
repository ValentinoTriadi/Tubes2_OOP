package oop.if2210_tb2_sc4.card;

public class DelayCard extends BadPotion {
    public DelayCard(String name) {
        super(name);
    }

    public DelayCard(DelayCard delayCard) {
        super(delayCard.name);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (!card.getEffect().contains(EffectType.PROTECT)) {
            card.addEffect(EffectType.DELAY);
        }
    }

}
