package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.List;
import java.util.Stack;

public class CardsChoiceUI {

    private final ImageView[] cardImages = new ImageView[4];
    private final Card[] availableCard = new Card[4];
    private final HBox parent;
    private final AnchorPane outerPane;
    private Deck playerDeck;

    public CardsChoiceUI(HBox parent, AnchorPane outerPane, Deck currentDeck) {
        this.parent = parent;
        this.playerDeck = currentDeck;
        this.outerPane = outerPane;
        ResetCards(currentDeck);
    }

    public void  ResetCards(Deck currentDeck){
        playerDeck = currentDeck;
        if(currentDeck.isHandFull() || currentDeck.isDeckEmpty()){
            GameWindowController.isShuffleDone = true;
            return;
        }

        outerPane.setVisible(true);
        int generatedCount = Math.min(Deck.GENERATED_CARD_COUNT, playerDeck.getCardsInDeckCount());

        for (int i = 0; i < cardImages.length; i++) {
            cardImages[i] = new ImageView();
            cardImages[i].setFitHeight(150);
            cardImages[i].setFitWidth(150);
            StackPane pane = (StackPane) parent.getChildren().get(i);
            pane.getChildren().add(cardImages[i]);
            final int index = i;
            pane.setOnMouseEntered(event -> pane.setCursor(Cursor.HAND));
            pane.setOnMouseClicked(event -> handleCardClick(index));
        }
        randomGenerateCards();
    }

    // Remove the image From the Stackpane
    private void removeImage(int index){
        // Remove the ImageView from the parent GridPane
        StackPane pane= (StackPane) parent.getChildren().get(index);
        int lastIndex = pane.getChildren().size()-1;
        if(lastIndex == 1){
            pane.getChildren().remove(lastIndex);
        }
    }

    private boolean isEmptyParent() {
        // Iterate through all children of the parent pane
        for (Node node : parent.getChildren()) {
            if (node instanceof StackPane pane) {
                // Check if the StackPane is empty
                if (pane.getChildren().size() > 1) {
                    return false;
                }
            }
        }
        return true;
    }

    // Handle the click event for the card at the specified index
    private void handleCardClick(int index) {

        // Remove Image
        removeImage(index);
        // get the Card from random generated Card
        Card card = availableCard[index];

        // Check the card Instance
        boolean isCardProduct = isCardProduct(card);
        StackPane pane  = (StackPane) parent.getChildren().get(index);
        pane.setDisable(true);
        pane.setCursor(Cursor.DEFAULT);
        if(isCardProduct){
            GameWindowController.addItem(card);
        }else{
            GameWindowController.addCard(card);
        }

        playerDeck.removeCardFromDeck();
        if(playerDeck.isHandFull()){
            outerPane.setVisible(false);
            for (int i = 0; i < cardImages.length; i++) {
                removeImage(i);
            }
            GameWindowController.isShuffleDone = true;
        }

        if(isEmptyParent()){
            outerPane.setVisible(false);
            GameWindowController.isShuffleDone = true;
        }
    }

    private boolean isCardProduct(Card card){
        return !(card instanceof AnimalCard) && !(card instanceof PlantCard);
    }

       public void randomGenerateCards() {


        List<Card> generatedCard= playerDeck.generateCards();
        System.out.println("Amount of deck: "+ playerDeck.getCardsInDeckCount());
        // Check if the total number of images is sufficient
        for (int i = 0; i < generatedCard.size(); i++) {
           Card card = generatedCard.get(i);
           Image image = ImageUtil.getCardImage(card);
           cardImages[i].setImage(image);
           availableCard[i] = card;
           StackPane pane = (StackPane) parent.getChildren().get(i);
           pane.setOnMouseEntered(event -> pane.setCursor(Cursor.HAND));
           pane.setDisable(false);
        }
    }

}
