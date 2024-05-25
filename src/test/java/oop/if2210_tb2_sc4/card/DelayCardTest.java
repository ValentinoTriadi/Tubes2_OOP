package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DelayCardTest {

    @Test
    void applyEffect() {
        FarmResourceCard card = new AnimalCard("Farm Resource Card");
        DelayCard delayCard = new DelayCard("Delay Card");
        delayCard.applyEffect(card);
        assertTrue(card.getEffect().contains(EffectType.DELAY));
        delayCard.applyEffect(card);
        assertFalse(card.getEffect().contains(EffectType.DELAY));
    }
}