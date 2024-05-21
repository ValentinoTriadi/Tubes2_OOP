package oop.if2210_tb2_sc4.card;

public class DestroyCard extends ItemCard{
    public DestroyCard(String name) {
        super(name);
    }


    @Override
    public void applyEffect(FarmResourceCard card) {
       if (card.getEffect().contains(EffectType.PROTECT)) {
           card.removeEffect(EffectType.PROTECT);
       } else {
           card = null;
       }
    }
}
