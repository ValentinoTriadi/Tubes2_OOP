package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;

public class PlayerUI extends StackPane {

    private final Pane root;
    private DeckUI activeDeckHBox;
    private LadangUI myLadang;

    public PlayerUI() {
        super();
        this.root = GameWindowController.rootStatic;
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
        myLadang = new LadangUI();
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

    public void addCard(){
        String[] cardNames = {"hewan/bear", "hewan/chicken", "hewan/cow", "hewan/sheep", "hewan/horse"};
        String name = cardNames[(int) (Math.random() * cardNames.length)];

        CardUI card = new CardUI(root, myLadang.getLadang());
        card.initCard(name);

        System.out.println("Card" + name +" spawned");

        activeDeckHBox.addCard(card);
    }
}
