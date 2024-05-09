package oop.if2210_tb2_sc4.game_manager;

import java.util.Map;
import java.util.HashMap;

public class GameState {
    private static Integer current_player = 1;
    private static Integer count_items = 0;
    private static Map<String, Integer> items = new HashMap<String, Integer>();

    public static Integer getCurrentPlayer(){
        return current_player;
    }

    public static void setCurrentPlayer(Integer player){
        current_player = player;
    }

    public static Integer getCountItems(){
        return count_items;
    }

    public static void setCountItems(Integer count){
        count_items = count;
    }

    public static Map<String, Integer> getItems(){
        return items;
    }

    public static void setItems(Map<String, Integer> newItems){
        items = newItems;
    }
}
