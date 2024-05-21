package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyCardTest {

    @Test
    void applyEffect() {
        FarmResourceCard card = new AnimalCard("Farm Resource Card");
        DestroyCard destroyCard = new DestroyCard("Destroy Card");
        ProtectCard protectCard = new ProtectCard("Protect Card");
        protectCard.applyEffect(card);
        assertTrue(card.getEffect().contains(EffectType.PROTECT));
        destroyCard.applyEffect(card);
        assertFalse(card.getEffect().contains(EffectType.PROTECT));
    }
}