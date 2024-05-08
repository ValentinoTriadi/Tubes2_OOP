package oop.if2210_tb2_sc4.save_load;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;

public class SaveTXT implements Save {
    private String folderName;
    private Player player1;
    private Player player2;

    public SaveTXT(String folderName, Player player1, Player player2){
        this.folderName = folderName;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void save() {
        saveGameState();
        savePlayer(1);
        savePlayer(2);
    }

    private void savePlayer(int no_player){
        // Save player {no_player}
        File player_file = new File("oop/if2210_tb2_sc4/" + folderName + "/player" + no_player + ".txt");
        Player player = (no_player == 1) ? player1 : player2;
        try {
            FileWriter writer = new FileWriter(player_file);

            writer.write(player.getJumlahGulden() + "\n");
            
            writer.write(player.getJumlahDeck() + "\n");
            
            writer.write(player.getJumlahDeckActive() + "\n"); // alternative use player.getActiveDeck().size()
            
            Map<String, String> active_deck = player.getActiveDeck();
            active_deck.forEach((key, value) -> {
                try {
                    writer.write(key + " " + value + "\n");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            writer.write(player.getJumlahKartuLadang() + "\n"); // alternative use player.getKartuLadang().size()

            for (Map<String, Object> kartu : player.getKartuLadang()){
                writer.write(kartu.get("lokasi") + " " + kartu.get("kartu") + " " + kartu.get("countItem"));

                assert kartu.get("items") instanceof List;

                @SuppressWarnings("unchecked")
                List<String> items = new ArrayList<String>((List<String>) kartu.get("items"));
                items.stream().forEach(
                    item -> {
                        try {
                            writer.write(" " + item);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                );

                writer.write("\n");
            }

            writer.close();
        } catch (FileAlreadyExistsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveGameState(){
        File game_state_file = new File("oop/if2210_tb2_sc4/" + folderName + "/game_state.txt");
        try {
            FileWriter writer = new FileWriter(game_state_file);

            writer.write(GameState.getCurrentPlayer() + "\n");
            writer.write(GameState.getCountItems() + "\n");

            Map<String, Integer> items = GameState.getItems();
            items.forEach((key, value) -> {
                try {
                    writer.write(key + " " + value + "\n");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            writer.close();
        } catch (FileAlreadyExistsException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
