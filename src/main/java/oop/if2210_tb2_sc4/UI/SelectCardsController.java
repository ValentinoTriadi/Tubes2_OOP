package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import oop.if2210_tb2_sc4.Deck;

public class SelectCardsController implements SelectionFinishListener {

    public AnchorPane outerPane;
    public HBox HboxCards;
    private CardsChoiceUI selectedGrids;
    private SeranganBeruang seranganBeruangPhase;

    public SelectCardsController(){

    }

    public void initialize(){
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids = new CardsChoiceUI(HboxCards, outerPane, deck);
    }

    public void InvokePanel(SeranganBeruang seranganBeruangPhase){
        this.seranganBeruangPhase = seranganBeruangPhase;
        Deck deck = GameWindowController.getCurrentPlayerPane().getPlayerData().getDeck();
        selectedGrids.ResetCards(deck);
        selectedGrids.setSelectionFinishListener(this);
    }

    public void ShuffleCards(){
        selectedGrids.randomGenerateCards();
    }

    @Override
    public void selectionFinished() {
        if (Math.random() < 0.5) {
            return;
        }
        seranganBeruangPhase.start();
    }

}
