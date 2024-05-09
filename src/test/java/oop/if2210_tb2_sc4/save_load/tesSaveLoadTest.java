package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class tesSaveLoadTest {

    @Test
    void main() {
        LoadTXT load = new LoadTXT();
        load.loadGameState();
        Player player1 = load.loadPlayer(1);
        Player player2 = load.loadPlayer(2);

        StringBuilder textGameState = new StringBuilder();
        StringBuilder textPlayer1 = new StringBuilder();
        StringBuilder textPlayer2 = new StringBuilder();

        textGameState.append("Game State: \n");
        textGameState.append("Current Player: ").append(GameState.getCurrentPlayer()).append("\n");
        textGameState.append("Count Items: ").append(GameState.getCountItems()).append("\n");
        textGameState.append("Items: \n");
        for (String key : GameState.getItems().keySet()){
            textGameState.append("    ").append(key).append(": ").append(GameState.getItems().get(key)).append("\n");
        }
        textGameState.append("\n");

        textPlayer1.append("Player 1: \n");
        textPlayer1.append("Gulden: ").append(player1.getJumlahGulden()).append("\n");
        textPlayer1.append("Jumlah Deck: ").append(player1.getJumlahDeck()).append("\n");
        textPlayer1.append("Jumlah Active Deck: ").append(player1.getJumlahDeckActive()).append("\n");
        textPlayer1.append("Active Deck: ").append("\n");
        for (Map.Entry<String, String> stringStringEntry : player1.getActiveDeck().entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            textPlayer1.append("    ").append(key).append(": ").append(value).append("\n");
        }
        textPlayer1.append("Jumlah Kartu Ladang: ").append(player1.getJumlahKartuLadang()). append("\n");
        textPlayer1.append("Kartu Ladang: \n");
        List<Map<String, Object>> kartuLadang = player1.getKartuLadang();
        for (int i = 0; i < player1.getJumlahKartuLadang(); i++){
            textPlayer1.append("    Lokasi: ").append(kartuLadang.get(i).get("lokasi")).append("\n");
            textPlayer1.append("    Kartu: ").append(kartuLadang.get(i).get("kartu")).append("\n");
            textPlayer1.append("    Umur: ").append(kartuLadang.get(i).get("umur")).append("\n");
            textPlayer1.append("    Count Item: ").append(kartuLadang.get(i).get("countItem")).append("\n");
            textPlayer1.append("    Items: \n");

            for (String item : (List<String>) kartuLadang.get(i).get("item")) textPlayer1.append("        ").append(item).append("\n");
        }
        textPlayer1.append("\n");

        textPlayer2.append("Player 2: \n");
        textPlayer2.append("Gulden: ").append(player2.getJumlahGulden()).append("\n");
        textPlayer2.append("Jumlah Deck: ").append(player2.getJumlahDeck()).append("\n");
        textPlayer2.append("Jumlah Active Deck: ").append(player2.getJumlahDeckActive()).append("\n");
        textPlayer2.append("Active Deck: ").append("\n");
        for (Map.Entry<String, String> stringStringEntry : player2.getActiveDeck().entrySet()) {
            String key = stringStringEntry.getKey();
            String value = stringStringEntry.getValue();
            textPlayer2.append("    ").append(key).append(": ").append(value).append("\n");
        }
        textPlayer2.append("Jumlah Kartu Ladang: ").append(player2.getJumlahKartuLadang()). append("\n");
        textPlayer2.append("Kartu Ladang: \n");
        kartuLadang = player2.getKartuLadang();
        for (int i = 0; i < player2.getJumlahKartuLadang(); i++){
            textPlayer2.append("    Lokasi: ").append(kartuLadang.get(i).get("lokasi")).append("\n");
            textPlayer2.append("    Kartu: ").append(kartuLadang.get(i).get("kartu")).append("\n");
            textPlayer2.append("    Umur: ").append(kartuLadang.get(i).get("umur")).append("\n");
            textPlayer2.append("    Count Item: ").append(kartuLadang.get(i).get("countItem")).append("\n");
            textPlayer2.append("    Items: \n");

            for (String item : (List<String>) kartuLadang.get(i).get("item")) textPlayer1.append("        ").append(item).append("\n");
        }
        textPlayer1.append("\n");

        assertEquals(textGameState.toString(),
                """
                        Game State:\s
                        Current Player: 1
                        Count Items: 5
                        Items:\s
                            SIRIP_HIU: 5
                            SUSU: 2
                            DAGING_DOMBA: 3
                            DAGING_BERUANG: 1
                            DAGING_KUDA: 10

                        """);

        assertEquals(textPlayer1.toString(),
                """
                        Player 1:\s
                        Gulden: 500
                        Jumlah Deck: 39
                        Jumlah Active Deck: 1
                        Active Deck:\s
                            A01: BERUANG
                        Jumlah Kartu Ladang: 1
                        Kartu Ladang:\s
                            Lokasi: A01
                            Kartu: DOMBA
                            Umur: 5
                            Count Item: 3
                            Items:\s
                                ACCELERATE
                                DELAY
                                PROTECT
                        
                        
                        """);

        assertEquals(textPlayer2.toString(), """
                Player 2:\s
                Gulden: 300
                Jumlah Deck: 38
                Jumlah Active Deck: 2
                Active Deck:\s
                    A01: BERUANG
                    B01: BERUANG
                Jumlah Kartu Ladang: 1
                Kartu Ladang:\s
                    Lokasi: B02
                    Kartu: AYAM
                    Umur: 0
                    Count Item: 0
                    Items:\s
                """);

        SaveTXT save = new SaveTXT("SAVE", player1, player2);
        save.save();
        try {
            assertEquals(new String(Objects.requireNonNull(getClass().getResourceAsStream("SAVE/gamestate.txt")).readAllBytes(), StandardCharsets.UTF_8), new String(Objects.requireNonNull(getClass().getResourceAsStream("default/gamestate.txt")).readAllBytes(), StandardCharsets.UTF_8));
            assertEquals(new String(Objects.requireNonNull(getClass().getResourceAsStream("SAVE/player1.txt")).readAllBytes(), StandardCharsets.UTF_8), new String(Objects.requireNonNull(getClass().getResourceAsStream("default/player1.txt")).readAllBytes(), StandardCharsets.UTF_8));
            assertEquals(new String(Objects.requireNonNull(getClass().getResourceAsStream("SAVE/player2.txt")).readAllBytes(), StandardCharsets.UTF_8), new String(Objects.requireNonNull(getClass().getResourceAsStream("default/player2.txt")).readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Test Successfully");
    }

}