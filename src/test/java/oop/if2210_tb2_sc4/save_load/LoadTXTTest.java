package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LoadTXTTest {

    LoadTXT load = new LoadTXT();

    @Test
    void loadGameState() {
        // load
        load = new LoadTXT();
        load.loadGameState();

        load = new LoadTXT();
        Scanner scanner = new Scanner(load.getGameStateFile());

        // get current player
        assertEquals(GameState.getCurrentPlayer(), scanner.nextInt());

        // get count items
        int countItem = scanner.nextInt();
        assertEquals(GameState.getCountItems(), countItem);

        // get items from file
        Map<String, Integer> items = new HashMap<>();
        for(int i = 0; i < countItem; i++) {
            String item = scanner.next();
            int count = scanner.nextInt();
            items.put(item, count);
        }

        // get shop items from GameState
        Map<ProductCard, Integer> shopItems = GameState.getShopItems();

        // map shop items into string and integer (name and count)
        Map<String, Integer> expectedItems = new HashMap<>();
        for (Map.Entry<ProductCard, Integer> entry : shopItems.entrySet()) {
            expectedItems.put(entry.getKey().getName(), entry.getValue());
        }

        assertEquals(items, expectedItems);
        scanner.close();
    }

    @Test
    void loadPlayer() {
        // load player
        load = new LoadTXT("default");
        Player p1 = load.loadPlayer(1);
        Player p2 = load.loadPlayer(2);

        // check loaded value
        load = new LoadTXT("default");
        Scanner scanner = new Scanner(load.getPlayer1File());

        // check value for player 1
        assertEquals(p1.getJumlahGulden(), scanner.nextInt()); // gulden
        assertEquals(p1.getJumlahDeck(), scanner.nextInt()); // jumlah deck
        assertEquals(p1.getJumlahDeckActive(), scanner.nextInt()); // jumlah active deck

        // Only can be tested if location is added to the card
        /*
         Card[] active_deck = p1.getActiveDeck();
         List<String> active_deck_name = Arrays.stream(active_deck).map(Card::getName).toList();
         for (int i = 0; i < active_deck.length; i++) {
             scanner.next(); // lokasi
             String nama = scanner.next(); // nama kartu
             if (active_deck_name.contains(nama)) {
                 active_deck_name.remove(nama);
             } else {
                 fail("Card not found in active deck");
             }
         }
         */

        // Temporary testing
        scanner.nextLine(); // skip to next line
        assertEquals("A01 BERUANG", scanner.nextLine());

        assertEquals(1, scanner.nextInt()); // jumlah kartu ladang

        // Only can be tested if location is added to the card
        /*
        Card[] kartu_ladang = p1.getLadang().getCardListinLadang();
        List<String> kartu_ladang_name = Arrays.stream(kartu_ladang).map(Card::getName).toList();
        for (int i = 0; i < kartu_ladang.length; i++) {
            scanner.next(); // lokasi
            assertEquals(kartu_ladang[i].getName(), scanner.next()); // nama kartu
            // TODO: check age or weight
            // TODO: check effect
        }
        */

        // Temporary testing
        scanner.nextLine(); // ignore next line
        assertEquals("A01 DOMBA 5 3 ACCELERATE DELAY PROTECT", scanner.nextLine());
        scanner.close();

        scanner = new Scanner(load.getPlayer2File());

        assertEquals(p2.getJumlahGulden(), scanner.nextInt()); // gulden
        assertEquals(p2.getJumlahDeck(), scanner.nextInt()); // jumlah deck
        assertEquals(p2.getJumlahDeckActive(), scanner.nextInt()); // jumlah active deck

        // Only can be tested if location is added to the card
        /*
        active_deck = p2.getActiveDeck();
        active_deck_name = Arrays.stream(active_deck).map(Card::getName).toList();
        for (int i = 0; i < active_deck.length; i++) {
            scanner.next(); // lokasi
            String nama = scanner.next(); // nama kartu
            if (active_deck_name.contains(nama)) {
                active_deck_name.remove(nama);
            } else {
                fail("Card not found in active deck");
            }
         */

        // Temporary testing
        scanner.nextLine(); // skip to next line
        assertEquals("A01 BERUANG", scanner.nextLine());
        assertEquals("B01 BERUANG", scanner.nextLine());

        assertEquals(1, scanner.nextInt()); // jumlah kartu ladang

        // Only can be tested if location is added to the card
        /*
        kartu_ladang = p2.getLadang().getCardListinLadang();
        kartu_ladang_name = Arrays.stream(kartu_ladang).map(Card::getName).toList();
        for (int i = 0; i < kartu_ladang.length; i++) {
            scanner.next(); // lokasi
            assertEquals(kartu_ladang[i].getName(), scanner.next()); // nama kartu
            // TODO: check age or weight
            // TODO: check effect
        }
         */

        // Temporary testing
        scanner.nextLine(); // ignore next line
        assertEquals("B02 AYAM 0 0", scanner.nextLine());
        scanner.close();
    }
}
