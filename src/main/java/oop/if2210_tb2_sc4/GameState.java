package oop.if2210_tb2_sc4;

import java.util.Map;

import oop.if2210_tb2_sc4.card.ProductCard;

public class GameState {

    private static GameState instance;

    public static GameState getInstance(){
        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }

    private Player player1;
    private Player player2;
    private int current_turn = 1;
    private Shop shop = new Shop();

    public void ResetData(){
        instance.current_turn = 1;
        instance.shop = new Shop();
    }

    public int getCurrentPlayer(){
        return instance.current_turn;
    }

    public void setCurrentPlayer(int player){
        instance.current_turn = player;
    }

    public Shop getShop(){
        return instance.shop;
    }

    public Integer getCountItems(){
        int itemCount= 0;
        for(Map.Entry<ProductCard, Integer> entry : instance.shop.getCardStock().entrySet()){
            itemCount += entry.getValue();
        }
        return itemCount;
    }


    public Map<ProductCard, Integer>
    getShopItems(){
        return instance.shop.getCardStock();
    }

    public void setShop(Shop shop){
        instance.shop = new Shop(shop);
    }
}

public oop.if2210_tb2_sc4.Player getPlayer(int player){
    if(player == 1){
        return player1;
    } else {
        return player2;
    }
}

public void setPlayer(int player, oop.if2210_tb2_sc4.Player p){
    if(player == 1){
        player1 = p;
    } else {
        player2 = p;
    }
}
