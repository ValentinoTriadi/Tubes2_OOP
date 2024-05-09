package oop.if2210_tb2_sc4.save_load;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;

public class SaveTXT implements Save {
    private final String folderName;
    private final Player player1;
    private final Player player2;

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
        Path path = Paths.get("src/main/resources/oop/if2210_tb2_sc4/save_load/" + folderName + "/player" + no_player + ".txt");
        File file = handleNewFile(path);

        Player player = (no_player == 1) ? player1 : player2;
        try {
            FileWriter writer = new FileWriter(file);

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

            for (Map<String, Object> kartu : player.getLadang()){
                writer.write(kartu.get("lokasi") + " " + kartu.get("kartu") + " " + kartu.get("umur") + " " + kartu.get("countItem"));

                @SuppressWarnings("unchecked")
                List<Object> items = (List<Object>) kartu.get("item");
                items.forEach(
                    item -> {
                        try {
                            writer.write(" " + item);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                );

            }

            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveGameState(){
        Path path = Paths.get("src/main/resources/oop/if2210_tb2_sc4/save_load/" + folderName + "/gamestate.txt");
        File file = handleNewFile(path);

        try {
            FileWriter writer = new FileWriter(file);

            writer.write(GameState.getCurrentPlayer() + "\n");
            writer.write(GameState.getCountItems().toString());

            Map<String, Integer> items = GameState.getItems();
            items.forEach((key, value) -> {
                try {
                    writer.write("\n" + key + " " + value );
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });


            writer.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private File handleNewFile(Path path){
        File file = null;
        try {
            // if folder does not exist, create new file
            Files.createDirectories(path.getParent());

            file = new File(path.toString());
            if (!file.exists()) {
                // if file does not exist, create new file
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File creating failed.");
                }
            } else {
                System.out.println("File " + file.getName() + " already exists.");
                System.out.println("Save will overwrite the file.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println();
        }
        return file;
    }
}
