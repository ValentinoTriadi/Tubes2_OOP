package oop.if2210_tb2_sc4.card;

public class InstantHarvestCard extends ItemCard{
    public InstantHarvestCard(String name) {
        super(name);
    }

    @Override
    public void applyEffect(FarmResourceCard card) {
        card.addEffect(EffectType.INSTANT_HARVEST);
    }
}
