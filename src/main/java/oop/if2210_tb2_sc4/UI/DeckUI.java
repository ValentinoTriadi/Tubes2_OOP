package oop.if2210_tb2_sc4.UI;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.util.ImageUtil;

public class DeckUI extends HBox {

    private Deck deckData;
    private final CardHolder[] activeDeck;

    public DeckUI(Deck userDeck) {
        this.deckData = userDeck;
        this.activeDeck = new CardHolder[6];
        this.setSpacing(20);
    }

    public Deck getDeckData(){return deckData;}

    public void initializeActiveDeck() {
        // Initialize activeDeck
        Image bgImage = ImageUtil.getComponentImage("HandTile.png");
        BackgroundImage bg = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100,100, true, false, true, true)
        );
        for (int i = 0; i < 6; i++) {
            activeDeck[i] = new CardHolder();
            activeDeck[i].setPrefSize(92, 130);
            activeDeck[i].setBackground(new Background(bg));

            this.getChildren().add(activeDeck[i]);
        }
    }

    public boolean isEmpty(int index) {
        return activeDeck[index].getChildren().isEmpty();
    }

    public void setDeckData(Deck deck){
        this.deckData = deck;
    }

    public void addCard(UICard card) {
        for (int i = 0; i < 6; i++) {
            if (activeDeck[i].getChildren().isEmpty()) {
                if(card instanceof ItemUI){
                    ((ItemUI)card).setParent(activeDeck[i]);
                }else{
                    ((CardUI)card).setParent(activeDeck[i]);
                }
                break;
            }
        }
    }

    public void setCard(UICard card, int location){
        if(!activeDeck[location].getChildren().isEmpty()){
            activeDeck[location].getChildren().remove(activeDeck[location].getChildren().size() - 1);
        }
        activeDeck[location].getChildren().add((Node) card);
    }

    public void UpdateDataDeck(){

        for (int i = 0; i < 6; i++) {
            if (activeDeck[i].getChildren().isEmpty()) {
                deckData.removeActiveCard(i);
            }else{
                Node currentUICard = activeDeck[i].getChildren().get(0);
                if(currentUICard instanceof CardUI){
                    deckData.setActiveCard(i, ((CardUI)currentUICard).getCardData());
                }else{
                    deckData.setActiveCard(i,((ItemUI)currentUICard).getCard());
                }
            }
        }


    }

    public void Clear(){
        for(int i = 0; i < 6; i++){
            activeDeck[i].getChildren().removeAll(activeDeck[i].getChildren());
        }
    }

    public void UpdateUIDeck(PlayerUI changedPlayer) throws FullActiveHandsException {
        Clear();
        for(int i = 0; i < 6; i++){
            if(!(deckData.getActiveCards()[i] == null)){
                Card cardData = deckData.getActiveCards()[i];

                if(cardData instanceof FarmResourceCard){
                    CardUI cardUI = new CardUI(activeDeck[i], changedPlayer.DropZoneAlocation(cardData));
                    cardUI.setCard((FarmResourceCard) cardData);
                    setCard(cardUI, i);
                }else{
                    ItemUI itemUI = new ItemUI(activeDeck[i],changedPlayer.DropZoneAlocation(cardData));
                    itemUI.setCard(cardData);
                    setCard(itemUI, i);
                }
            }
        }
    }


}
