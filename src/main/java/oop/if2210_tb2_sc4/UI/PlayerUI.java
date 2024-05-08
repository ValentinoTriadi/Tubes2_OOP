package oop.if2210_tb2_sc4.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;

import java.util.Arrays;

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

        DropZone[] dropZones = myLadang.getLadang();
        dropZones = Arrays.copyOf(dropZones, dropZones.length + 1);
        dropZones[dropZones.length - 1] = GameWindowController.sellZone;
        CardUI card = new CardUI(root, dropZones);
        card.initCard(name);

        System.out.println("Card" + name +" spawned");

        activeDeckHBox.addCard(card);
    }

    public void addItem(int index, Target target){
        String[] cardNames = {"hewan/bear", "hewan/chicken", "hewan/cow", "hewan/sheep", "hewan/horse"};
        String name = cardNames[(int) (Math.random() * cardNames.length)];

        DropZone[] dropZones = new DropZone[0];
        if (target == Target.SELF){
            dropZones = myLadang.getLadang();
        } else {
            if (index % 2 == 0){
                dropZones = GameWindowController.ladang2.getLadang();
            } else {
                dropZones = GameWindowController.ladang1.getLadang();
            }
        }
        ItemUI card = new ItemUI(root, dropZones);
        card.initCard(name);

        System.out.println("Item " + name +" spawned");

        activeDeckHBox.addItem(card);
    }
}
