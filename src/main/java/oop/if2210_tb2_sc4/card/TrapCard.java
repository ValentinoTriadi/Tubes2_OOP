package oop.if2210_tb2_sc4.card;

public class TrapCard extends ItemCard{
    public TrapCard(String name) {
        super(name);
    }

    public TrapCard(TrapCard trapCard) {
        super(trapCard.getName());
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        card.addEffect(EffectType.TRAP);
    }
}
