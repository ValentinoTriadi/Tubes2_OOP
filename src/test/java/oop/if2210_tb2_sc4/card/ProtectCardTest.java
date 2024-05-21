package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProtectCardTest {

    @Test
    void applyEffect() {
        ProtectCard protectCard = new ProtectCard("Protect");

        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource");
        protectCard.applyEffect(farmResourceCard);
        assertSame(farmResourceCard.effectApplied.get(0), EffectType.PROTECT);
    }
}