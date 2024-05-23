package oop.if2210_tb2_sc4.card;

public abstract class GoodPotion extends ItemCard {

    public GoodPotion(String name) {
        super(name);
    }

    @Override
    public abstract void applyEffect(FarmResourceCard card);
}
