package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapCardTest {

    @Test
    void applyEffect() {
        TrapCard trapCard = new TrapCard("Trap", "image_path");

        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource", "image_path");
        trapCard.applyEffect(farmResourceCard);
        assertSame(farmResourceCard.effectApplied.get(0), EffectType.TRAP);
    }
}