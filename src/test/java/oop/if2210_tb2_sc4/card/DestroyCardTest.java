package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestroyCardTest {

    @Test
    void applyEffect() {
        FarmResourceCard card = new AnimalCard("Farm Resource Card", "image_path");
        DestroyCard destroyCard = new DestroyCard("Destroy Card", "image_path");
        ProtectCard protectCard = new ProtectCard("Protect Card", "image_path");
        protectCard.applyEffect(card);
        assertTrue(card.getEffect().contains(EffectType.PROTECTION));
        destroyCard.applyEffect(card);
        assertFalse(card.getEffect().contains(EffectType.PROTECTION));
    }
}