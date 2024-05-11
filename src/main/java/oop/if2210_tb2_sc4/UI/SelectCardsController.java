package oop.if2210_tb2_sc4.UI;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import oop.if2210_tb2_sc4.GameEngine.DataManager;

public class SelectCardsController {

    public AnchorPane outerPane;
    private CardsChoiceUI selectedGrids;
    public GridPane itemsGrid;

    public SelectCardsController(){

    }

    public void initialize(){
        selectedGrids = new CardsChoiceUI(itemsGrid, outerPane, DataManager.getCurrentPlayer().getDeck());
    }

    public void InvokePanel(){
        selectedGrids.ResetCards(DataManager.getCurrentPlayer().getDeck());
    }

    public void ShuffleCards(){
        selectedGrids.randomGenerateCards();
    }

}
