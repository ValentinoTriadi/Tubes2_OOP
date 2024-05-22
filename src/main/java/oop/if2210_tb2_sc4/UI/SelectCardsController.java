package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import oop.if2210_tb2_sc4.Deck;

public class SelectCardsController {

    public AnchorPane outerPane;
    public HBox HboxCards;
    private CardsChoiceUI selectedGrids;


    public SelectCardsController(){

    }

    public void initialize(){
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids = new CardsChoiceUI(HboxCards, outerPane, deck);
    }

    public void InvokePanel(){
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids.ResetCards(deck);
    }

    public void ShuffleCards(){
        selectedGrids.randomGenerateCards();
    }

}
