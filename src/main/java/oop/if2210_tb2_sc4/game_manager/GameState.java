package oop.if2210_tb2_sc4.game_manager;

import java.util.Map;

import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.shop.Shop;

public class GameState {
    private static int current_player = 1;
    private static int count_items = 0;
    private static Shop shop = new Shop();

    public static int getCurrentPlayer(){
        return current_player;
    }

    public static void setCurrentPlayer(int player){
        current_player = player;
    }

    public static Integer getCountItems(){
        return count_items;
    }

    public static void setCountItems(int count){
        count_items = count;
    }

    public static Map<ProductCard, Integer> getShopItems(){
        return shop.getCardStock();
    }

    public static void setShop(Shop shop){
        GameState.shop = new Shop(shop);
    }
}