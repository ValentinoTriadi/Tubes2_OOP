package oop.if2210_tb2_sc4.save_load;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import oop.if2210_tb2_sc4.card.*;
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
            
            Card[] active_deck = player.getActiveDeck();

            for (int i = 0; i < player.getJumlahDeckActive(); i++) {
                Card card = active_deck[i];
                try {
                    if(card == null){
                        continue;
                    }
                    writer.write((char) (i+'A') + "01 " + card.getName() + "\n");

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            writer.write(player.getJumlahKartuLadang() + "\n"); // alternative use player.getKartuLadang().size()

            Map<String, FarmResourceCard> kartu_ladang = player.getLadang().getAllCardwithLocationinLadang();
            for (Map.Entry<String, FarmResourceCard> kartu : kartu_ladang.entrySet()) {
                FarmResourceCard card = kartu.getValue();

                // write lokasi, nama kartu
                writer.write(kartu.getKey() + " " + card.getName());
                if (card instanceof AnimalCard){
                    writer.write(" " + ((AnimalCard) card).getWeight());
                } else if (card instanceof PlantCard) {
                    writer.write(" " + ((PlantCard) card).getAge());
                }

                // write jumlah efek
                writer.write(" " + card.getEffect().size());

                // write effect
                card.getEffect().forEach(
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void saveGameState(){
        Path path = Paths.get("src/main/resources/oop/if2210_tb2_sc4/save_load/" + folderName + "/gamestate.txt");
        File file = handleNewFile(path);

        try {
            FileWriter writer = new FileWriter(file);

            GameState instance = GameState.getInstance();
            writer.write(instance.getCurrentPlayer() + "\n");
            writer.write(instance.getCountItems().toString());

            Map<ProductCard, Integer> items = instance.getShopItems();
            items.forEach((key, value) -> {
                try {
                    writer.write("\n" + key.getName() + " " + value );
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
