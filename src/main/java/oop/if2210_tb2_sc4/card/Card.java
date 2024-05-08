package oop.if2210_tb2_sc4.card;

/**
 * Base class for card object
 */
public class Card {
    private String name;
    private String image_path;

    public Card(String name, String image_path) {
        this.name = name;
        this.image_path = image_path;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return image_path;
    }
}


