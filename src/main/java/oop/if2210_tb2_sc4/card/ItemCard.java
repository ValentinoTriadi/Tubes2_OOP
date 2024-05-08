package oop.if2210_tb2_sc4.card;

public abstract class ItemCard extends Card{

    public ItemCard(String name) {
        super(name);
    }

    public abstract void applyEffect(FarmResourceCard card);
}
