package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.List;

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
        if(currentDeck.isHandFull() || currentDeck.isDeckEmpty()){
            return;
        }

        outerPane.setVisible(true);
        int generatedCount = Math.min(Deck.GENERATED_CARD_COUNT, playerDeck.getCardsInDeckCount());

        for (int i = 0; i < generatedCount; i++) {
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

        Card card = availableCard[index];

        boolean isCardProduct = isCardProduct(card);
        if(isCardProduct){
            GameWindowController.addItem(card);
        }else{
            GameWindowController.addCard(card);
        }

        playerDeck.removeCardFromDeck();
        if(playerDeck.isHandFull()){
            outerPane.setVisible(false);
            for (int i = 0; i < cardImages.length; i++) {
                parent.getChildren().remove(cardImages[i]);
            }
        }

        if(parent.getChildren().isEmpty()){
            outerPane.setVisible(false);
        }
    }

    private boolean isCardProduct(Card card){
        return !(card instanceof AnimalCard) && !(card instanceof PlantCard);
    }

       public void randomGenerateCards() {


        List<Card> generatedCard= playerDeck.generateCards();
        System.out.println("Amount of deck: "+ playerDeck.getCardsInDeckCount());
        // Check if the total number of images is sufficient
        if(cardImages.length >= generatedCard.size()){
           for (int i = 0; i < cardImages.length; i++) {
               Card card = generatedCard.get(i);
               Image image = ImageUtil.getCardImage(card);
               cardImages[i].setImage(image);
               availableCard[i] = card;
           }
       }
    }

}
