package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class LoadTXT implements Load {
    private File game_state_file;
    private File player_1_file;
    private File player_2_file;

    public LoadTXT(){
        game_state_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/gamestate.txt");
        player_1_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/player1.txt");
        player_2_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/player2.txt");
    }
    
    public LoadTXT(String folderName){
        game_state_file = new File("oop/if2210_tb2_sc4/state/" + folderName + "/gamestate.txt");
        player_1_file = new File("oop/if2210_tb2_sc4/" + folderName + "/player1.txt");
        player_2_file = new File("oop/if2210_tb2_sc4/" + folderName + "/player2.txt");
    }

    @Override
    public void loadGameState(){
        // Load game state
        try {
            Scanner scanner = new Scanner(game_state_file);
            GameState.setCurrentPlayer(scanner.nextInt());
            GameState.setCountItems(scanner.nextInt());
            Map<String, Integer> temp = new HashMap<String, Integer>();
            for (int i = 0; i < GameState.getCountItems(); i++){
                String item = scanner.next();
                Integer count = scanner.nextInt();
                temp.put(item, count);
            }
            GameState.setItems(temp);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Player loadPlayer(int no_player){
        try {
            File temp = (no_player == 1) ? player_1_file : player_2_file;
            Scanner scanner = new Scanner(temp);
            Player player = new Player();
            
            player.setJumlahGulden(scanner.nextInt());
            player.getDeck().setCardsInDeckCount(scanner.nextInt());
            player.getDeck().setCardsInHandCount(scanner.nextInt());


            Map<String, String> temp_deck = new HashMap<String, String>();
            for (int i = 0; i < player.getJumlahDeckActive(); i++){
                String card = scanner.next();
                String card_id = scanner.next();
                temp_deck.put(card, card_id);
            }
            player.setActiveDeck(temp_deck);

            player.setJumlahKartuLadang(scanner.nextInt());

            List<Map<String, Object>> temp_ladang = new ArrayList<>();
            for (int i = 0; i < player.getJumlahKartuLadang(); i++){
                Map<String, Object> temp_card = new HashMap<>();
                
                String lokasi = scanner.next();
                temp_card.put("lokasi", lokasi);
                
                String kartu = scanner.next();
                temp_card.put("kartu", kartu);

                int umur = scanner.nextInt();
                temp_card.put("umur", umur);

                int count = scanner.nextInt();
                temp_card.put("countItem", count);

                List<String> temp_item = new ArrayList<>();
                for (int j = 0; j < count; j++){
                    temp_item.add(scanner.next());
                }
                temp_card.put("item", temp_item);

                temp_ladang.add(temp_card);
            }
            player.setKartuLadang(temp_ladang);

            scanner.close();
            System.out.println("OutSafely" + no_player);
            return player;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } 
        return null;
    }
}
