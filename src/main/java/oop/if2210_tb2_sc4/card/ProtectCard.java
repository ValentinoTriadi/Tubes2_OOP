package oop.if2210_tb2_sc4.card;

public class ProtectCard extends ItemCard{
    public ProtectCard(String name) {
        super(name);
    }

    public ProtectCard(ProtectCard protectCard) {
        super(protectCard.name);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        card.addEffect(EffectType.PROTECT);
    }
}
