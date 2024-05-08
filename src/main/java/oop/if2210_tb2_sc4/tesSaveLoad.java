package oop.if2210_tb2_sc4;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;
import oop.if2210_tb2_sc4.save_load.LoadTXT;
import oop.if2210_tb2_sc4.save_load.SaveTXT;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class tesSaveLoad {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        LoadTXT load = new LoadTXT();
        load.loadGameState();
        Player player1 = load.loadPlayer(1);
        Player player2 = load.loadPlayer(2);


        System.out.println("Game State: ");
        System.out.println("Current Player: " + GameState.getCurrentPlayer());
        System.out.println("Count Items: " + GameState.getCountItems());
        System.out.println("Items: ");
        for (String key : GameState.getItems().keySet()){
            System.out.println("    " + key + ": " + GameState.getItems().get(key));
        }
        System.out.println();

        System.out.println("Player 1: ");
        System.out.println("Gulden: " + player1.getJumlahGulden());
        System.out.println("Jumlah Deck: " + player1.getJumlahDeck());
        System.out.println("Jumlah Active Deck: " + player1.getJumlahDeckActive());
        System.out.println("Active Deck: ");
        player1.getActiveDeck().forEach((key, value) -> System.out.println("    " + key + ": " + value));
        System.out.println("Jumlah Kartu Ladang: " + player1.getJumlahKartuLadang());
        System.out.println("Kartu Ladang: ");
        List<Map<String, Object>> kartuLadang = player1.getKartuLadang();
        for (int i = 0; i < player1.getJumlahKartuLadang(); i++){
            System.out.println("    Lokasi: " + kartuLadang.get(i).get("lokasi"));
            System.out.println("    Kartu: " + kartuLadang.get(i).get("kartu"));
            System.out.println("    Umur: " + kartuLadang.get(i).get("umur"));
            System.out.println("    Count Item: " + kartuLadang.get(i).get("countItem"));
            System.out.println("    Items: ");

            for (String item : (ArrayList<String>) kartuLadang.get(i).get("item")){
                System.out.println("        " + item);
            }
        }
        System.out.println();

        System.out.println("Player 2: ");
        System.out.println("Gulden: " + player2.getJumlahGulden());
        System.out.println("Jumlah Deck: " + player2.getJumlahDeck());
        System.out.println("Jumlah Active Deck: " + player2.getJumlahDeckActive());
        System.out.println("Active Deck: ");
        player2.getActiveDeck().forEach((key, value) -> System.out.println("    " + key + ": " + value));
        System.out.println("Jumlah Kartu Ladang: " + player2.getJumlahKartuLadang());
        System.out.println("Kartu Ladang: ");
        kartuLadang = player2.getKartuLadang();
        for (int i = 0; i < player2.getJumlahKartuLadang(); i++){
            System.out.println("    Lokasi: " + kartuLadang.get(i).get("lokasi"));
            System.out.println("    Kartu: " + kartuLadang.get(i).get("kartu"));
            System.out.println("    Umur: " + kartuLadang.get(i).get("umur"));
            System.out.println("    Count Item: " + kartuLadang.get(i).get("countItem"));
            System.out.println("    Items: ");

            for (String item : (ArrayList<String>) kartuLadang.get(i).get("item")){
                System.out.println("        " + item);
            }
        }


        SaveTXT save = new SaveTXT("SAVE", player1, player2);
        save.save();

    }
}
