package oop.if2210_tb2_sc4.card;

public class DestroyCard  extends BadPotion {
    public DestroyCard(String name) {
        super(name);
    }

    public DestroyCard(DestroyCard destroyCard) {
        super(destroyCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
       if (!card.getEffect().contains(EffectType.PROTECT)) {
           card = null;
       }
    }
}
