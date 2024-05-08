package oop.if2210_tb2_sc4.card;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalCardTest {

    @Test
    void getWeight() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        assertEquals(0, animalCard.getWeight());
    }

    @Test
    void setWeight() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        animalCard.setWeight(10);
        assertEquals(10, animalCard.getWeight());
    }

    @Test
    void getType() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        assertEquals(AnimalType.OMNIVORE, animalCard.getType());

        AnimalCard animalCard2 = new AnimalCard("name", "image_path", 10, 5, AnimalType.CARNIVORE);
        assertEquals(AnimalType.CARNIVORE, animalCard2.getType());
    }

    @Test
    void setType() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        animalCard.setType(AnimalType.CARNIVORE);
        assertEquals(AnimalType.CARNIVORE, animalCard.getType());
    }

    @Test
    void getHarvestWeight() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        assertEquals(0, animalCard.getHarvestWeight());
    }

    @Test
    void setHarvestWeight() {
        AnimalCard animalCard = new AnimalCard("name", "image_path");
        animalCard.setHarvestWeight(10);
        assertEquals(10, animalCard.getHarvestWeight());
    }

    @Test
    void isHarvestable() {
        AnimalCard animalCard = new AnimalCard("name", "image_path", 10, 5, AnimalType.OMNIVORE);
        assertTrue(animalCard.isHarvestable());
    }
}