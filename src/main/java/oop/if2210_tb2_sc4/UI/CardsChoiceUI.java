package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.deck.Deck;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CardsChoiceUI {

    private final ImageView[] cardImages = new ImageView[4];
    private final Card[] availableCard = new Card[4];

    private final AnchorPane outerPane;
    private GridPane parent = new GridPane();
    private Deck playerDeck;
    public CardsChoiceUI(GridPane parent, AnchorPane outerPane, Deck currentDeck) {
        this.parent = parent;
        this.playerDeck = currentDeck;
        parent.getChildren().clear();
        // Initialize card images
        for (int i = 0; i < cardImages.length; i++) {
            cardImages[i] = new ImageView();
            cardImages[i].setFitHeight(150);
            cardImages[i].setFitWidth(150);
            parent.add(cardImages[i], i % 2, i / 2);
            GridPane.setHalignment(cardImages[i], Pos.CENTER.getHpos());
            GridPane.setValignment(cardImages[i], Pos.CENTER.getVpos());

            final int index = i;
            cardImages[i].setOnMouseClicked(event -> handleCardClick(index));
        }
        this.outerPane = outerPane;
    }

    public void  ResetCards(Deck currentDeck){
        playerDeck = currentDeck;
        if(currentDeck.isFull()){
            // Hand is full
            return;
        }

        outerPane.setVisible(true);
        for (int i = 0; i < cardImages.length; i++) {
            cardImages[i] = new ImageView();
            cardImages[i].setFitHeight(150);
            cardImages[i].setFitWidth(150);
            parent.add(cardImages[i], i % 2, i / 2);
            GridPane.setHalignment(cardImages[i], Pos.CENTER.getHpos());
            GridPane.setValignment(cardImages[i], Pos.CENTER.getVpos());

            final int index = i;
            cardImages[i].setOnMouseClicked(event -> handleCardClick(index));
        }
        randomGenerateCards();
    }

    private void handleCardClick(int index) {
        // Handle the click event for the card at the specified index
        System.out.println("Card at index " + index + " clicked.");
        // Add your custom event handling logic here
        // Remove the ImageView from the parent GridPane
        parent.getChildren().remove(cardImages[index]);

        // Add your custom event handling logic here
        // Remove the ImageView from the parent GridPane
        parent.getChildren().remove(cardImages[index]);

        Card card = availableCard[index];
        String cardName = CardFolderType(card) + "/" + card.getName();
        boolean isCardProduct = isCardProduct(card);
        if(isCardProduct){
            System.out.println("Add Item");
            System.out.println("Folder: "+ CardFolderType(card));
            GameWindowController.addItem(cardName);
        }else{
            System.out.println("Add Card");
            GameWindowController.addCard(cardName);
        }

        if(playerDeck.isFull()){
            outerPane.setVisible(false);
        }else{
            System.out.println(playerDeck.getActiveCardinHandCount());
        }

        if(parent.getChildren().isEmpty()){
            outerPane.setVisible(false);
        }
    }

    private boolean isCardProduct(Card card){
        return !(card instanceof AnimalCard) && !(card instanceof PlantCard);
    }
    private  String CardFolderType(Card card){
        if(card instanceof AnimalCard){
            return "Hewan";
        }else if(card instanceof PlantCard){
            return "Tanaman";
        }else if(card instanceof ProductCard){
            return "Produk";
        }else{
            return "Item";
        }
    }
       public void randomGenerateCards() {

        // Load images from the specified folders
        String folderPath = "assets/card/";

        List<Card> generatedCard= playerDeck.generateCards();
        // Check if the total number of images is sufficient
       if(cardImages.length >= generatedCard.size()){
           for (int i = 0; i < cardImages.length; i++) {
               Card card = generatedCard.get(i);

               String folderName = CardFolderType(card);
               System.out.println("Folder: "+ CardFolderType(card));
               String path = folderPath + folderName + "/" + card.getName() + ".png";
               Image image = new Image(Objects.requireNonNull(getClass().getResource(path)).toExternalForm());
               cardImages[i].setImage(image);
               availableCard[i] = card;
           }
       }
    }

}
