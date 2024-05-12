package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.player.Player;

import java.util.Arrays;

public class PlayerUI extends StackPane {

    private final Pane root;
    private DeckUI activeDeckHBox;
    private LadangUI myLadang;
    private final Player playerData;

    public PlayerUI(Player playerData) {
        super();
        this.playerData = playerData;
        this.root = GameWindowController.rootStatic;
    }

    public Player getPlayerData(){
        return playerData;
    }

    public DeckUI getDeckUI(){
        return activeDeckHBox;
    }

    public void initPlayerUI(String name, Tab ladang) {

        VBox playerUI = new VBox();

        // Set properties
        playerUI.setAlignment(Pos.TOP_CENTER);
        playerUI.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        playerUI.setStyle("-fx-background-color: lightgrey;");
        playerUI.setSpacing(10);
        this.getChildren().add(playerUI);

        // Init Ladang
        myLadang = new LadangUI(playerData.getLadang());
        ladang.setContent(myLadang);

        // Init Deck
        activeDeckHBox = new DeckUI(playerData.getDeck());
        activeDeckHBox.setAlignment(Pos.CENTER);
        activeDeckHBox.initializeActiveDeck();
        playerUI.getChildren().add(activeDeckHBox);
    }

    public LadangUI getLadang() {
        return myLadang;
    }

    public void disableField(){
        myLadang.disableField();
    }

    public void enableField(){
        myLadang.enableField();
    }

    public void addCard(Card cardData){
        DropZone[] dropZones = myLadang.getLadang();
        CardUI card = new CardUI(root, dropZones);

        card.initCard(cardData);
        playerData.getDeck().addActiveCard(cardData);
        System.out.println("Card" + cardData.getName() +" spawned");

        activeDeckHBox.addCard(card);
    }

    public void addItem(Card cardData){

        DropZone[] dropZones = new DropZone[0];
        if(cardData instanceof AccelerateCard || cardData instanceof InstantHarvestCard || cardData instanceof ProtectCard || cardData instanceof TrapCard){
            dropZones = GameWindowController.getNextPlayerPane().getLadang().getLadang();
        }else{
            dropZones = GameWindowController.getCurrentPlayerPane().getLadang().getLadang();
        }
        dropZones = Arrays.copyOf(dropZones, dropZones.length + 1);
        dropZones[dropZones.length - 1] = GameWindowController.sellZone;
        ItemUI card = new ItemUI(root, dropZones);
        card.initCard(cardData);
        playerData.getDeck().addActiveCard(cardData);

        System.out.println("Item " + cardData.getName() +" spawned");

        activeDeckHBox.addItem(card);
    }

    public void UpdateUIPlayer(){

    }
}
