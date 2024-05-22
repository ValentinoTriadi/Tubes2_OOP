package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.card.*;
import oop.if2210_tb2_sc4.Ladang;
import oop.if2210_tb2_sc4.Player;
import oop.if2210_tb2_sc4.util.ImageUtil;

import java.util.Arrays;

public class PlayerUI extends StackPane {

    private final Pane root;
    private DeckUI activeDeckHBox;
    private LadangUI myLadang;
    private Player playerData;

    public PlayerUI(Player playerData) {
        super();
        this.playerData = playerData;
        this.root = GameWindowController.rootStatic;
    }

    public Player getPlayerData(){
        return playerData;
    }

    public void setPlayerData(Player playerData){
        this.playerData = playerData;
        this.getLadang().setLadangData(playerData.getLadang());
        this.getDeckUI().setDeckData(playerData.getDeck());
    }
    public DeckUI getDeckUI(){
        return activeDeckHBox;
    }


    public void initPlayerUI(String name, Tab ladang) {

        VBox playerUI = new VBox();

        // Set properties
        playerUI.setAlignment(Pos.CENTER_LEFT);
        playerUI.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

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

    public void addCard(Card cardData) throws FullActiveHandsException {

        DropZone[] dropZones = DropZoneAlocation(cardData);
        CardUI card = new CardUI(root, dropZones);
        card.setCard((FarmResourceCard) cardData);
        playerData.getDeck().addActiveCard(cardData);
        activeDeckHBox.addCard(card);
    }

    public void addItem(Card cardData) throws FullActiveHandsException {

        DropZone[] dropZones = DropZoneAlocation(cardData);
        ItemUI card = new ItemUI(root, dropZones);
        card.setCard(cardData);
        playerData.getDeck().addActiveCard(cardData);
        activeDeckHBox.addCard(card);
    }

    public DropZone[] DropZoneAlocation(Card cardData){
        DropZone[] dropZones;
        if(cardData instanceof FarmResourceCard){ // Add Animal and Plants
            dropZones = myLadang.getLadang();
        }else{ // Add Product and Power Card
            if(cardData instanceof AccelerateCard || cardData instanceof InstantHarvestCard || cardData instanceof ProtectCard || cardData instanceof TrapCard){
                dropZones = GameWindowController.getCurrentPlayerPane().getLadang().getLadang();
            }else{
                dropZones = GameWindowController.getNextPlayerPane().getLadang().getLadang();
            }
            dropZones = Arrays.copyOf(dropZones, dropZones.length + 1);
            dropZones[dropZones.length - 1] = GameWindowController.sellZone;
        }
        return dropZones;
    }
}
