package oop.if2210_tb2_sc4.GameEngine;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;
import oop.if2210_tb2_sc4.save_load.Load;
import oop.if2210_tb2_sc4.shop.Shop;

public class DataManager {
    private Player player1;
    private Player player2;

    private static DataManager instance;

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public static void StartNewGame(){
        DataManager.getInstance().player1 = new Player();
        DataManager.getInstance().player2 = new Player();

        //GameState.setShop(new Shop());
    }

    public static Player getCurrentPlayer(){
        int currentPlayer = GameState.getCurrentPlayer();
        if(currentPlayer == 1){
            return instance.player1;
        }else{
            return  instance.player2;
        }
    }

    public static void LoadGames(Load loader){
        loader.loadGameState();
        instance.player1 = loader.loadPlayer(1);
        instance.player2 = loader.loadPlayer(2);
    }

    public Player getPlayer1() {
        return player1;
    }
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }
    public Player getPlayer2() {
        return player2;
    }
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
