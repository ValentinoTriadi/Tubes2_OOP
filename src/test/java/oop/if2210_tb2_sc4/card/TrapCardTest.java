package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapCardTest {

    @Test
    void applyEffect() {
        TrapCard trapCard = new TrapCard("Trap");

        FarmResourceCard farmResourceCard = new AnimalCard("Farm Resource");
        trapCard.applyEffect(farmResourceCard);
        assertSame(farmResourceCard.effectApplied.get(0), EffectType.TRAP);
    }
}