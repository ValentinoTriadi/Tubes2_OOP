package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.card.Card;
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
        activeDeckHBox = new DeckUI();
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

    public void addCard(String name){
        DropZone[] dropZones = myLadang.getLadang();
        dropZones = Arrays.copyOf(dropZones, dropZones.length + 1);
        dropZones[dropZones.length - 1] = GameWindowController.sellZone;
        CardUI card = new CardUI(root, dropZones);
        Card dataCard = new Card(name);
        card.initCard(name);
        playerData.getDeck().addActiveCard(dataCard);
        System.out.println("Card" + name +" spawned");

        activeDeckHBox.addCard(card);
    }

    public void addItem(String name,int index, Target target){

        DropZone[] dropZones = new DropZone[0];
        if (target == Target.SELF){
            dropZones = myLadang.getLadang();
        } else {
            if (index % 2 == 0){ // Player 2
                dropZones = GameWindowController.ladang2.getLadang();
            } else { // Player 1
                dropZones = GameWindowController.ladang1.getLadang();
            }
        }
        ItemUI card = new ItemUI(root, dropZones);
        card.initCard(name);

        System.out.println("Item " + name +" spawned");

        activeDeckHBox.addItem(card);
    }

    public void UpdateUIPlayer(){

    }
}
