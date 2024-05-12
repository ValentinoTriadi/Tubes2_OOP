package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import oop.if2210_tb2_sc4.GameEngine.DataManager;
import oop.if2210_tb2_sc4.deck.Deck;

public class SelectCardsController {

    public AnchorPane outerPane;
    private CardsChoiceUI selectedGrids;
    public GridPane itemsGrid;

    public SelectCardsController(){

    }

    public void initialize(){
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids = new CardsChoiceUI(itemsGrid, outerPane, deck);
    }

    public void InvokePanel(){
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids.ResetCards(deck);
    }

    public void ShuffleCards(){
        selectedGrids.randomGenerateCards();
    }

}
