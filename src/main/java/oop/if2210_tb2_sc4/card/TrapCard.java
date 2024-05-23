package oop.if2210_tb2_sc4.card;

public class TrapCard extends GoodPotion {
    public TrapCard(String name) {
        super(name);
    }

    public TrapCard(TrapCard trapCard) {
        super(trapCard.name);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        card.addEffect(EffectType.TRAP);
    }
}
