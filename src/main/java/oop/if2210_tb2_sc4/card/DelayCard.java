package oop.if2210_tb2_sc4.card;

public class DelayCard extends ItemCard{
    public DelayCard(String name, String image_path) {
        super(name, image_path);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        if (card.getEffect().contains(EffectType.PROTECTION)) {
            card.removeEffect(EffectType.PROTECTION);
        } else {
            card.addEffect(EffectType.DELAY);
        }
    }

}
