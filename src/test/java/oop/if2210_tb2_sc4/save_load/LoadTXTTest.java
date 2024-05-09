package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.game_manager.GameState;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class LoadTXTTest {

    LoadTXT load = new LoadTXT();

    @Test
    void loadGameState() {
        load = new LoadTXT("SAVE");
        load.loadGameState();
        load = new LoadTXT();
        Scanner scanner = new Scanner(load.getGameStateFile());
        assertEquals(1, scanner.nextInt());
        assertEquals(5, scanner.nextInt());
        Map<String, Integer> items = new HashMap<>();
        for(int i = 0; i < 5; i++) {
            String item = scanner.next();
            int count = scanner.nextInt();
            items.put(item, count);
        }
        assertEquals(items, GameState.getItems());
        scanner.close();
    }

    @Test
    void loadPlayer() {
        load = new LoadTXT();
        Scanner scanner = new Scanner(load.getPlayer1File());
        assertEquals(500, scanner.nextInt());
        assertEquals(39, scanner.nextInt());
        assertEquals(1, scanner.nextInt());
        scanner.nextLine();
        assertEquals("A01 BERUANG", scanner.nextLine());
        assertEquals(1, scanner.nextInt());
        scanner.nextLine();
        assertEquals("A01 DOMBA 5 3 ACCELERATE DELAY PROTECT", scanner.nextLine());
        scanner.close();

        scanner = new Scanner(load.getPlayer2File());
        assertEquals(300, scanner.nextInt());
        assertEquals(38, scanner.nextInt());
        assertEquals(2, scanner.nextInt());
        scanner.nextLine();
        assertEquals("A01 BERUANG", scanner.nextLine());
        assertEquals("B01 BERUANG", scanner.nextLine());
        assertEquals(1, scanner.nextInt());
        scanner.nextLine();
        assertEquals("B02 AYAM 0 0", scanner.nextLine());
        scanner.close();

        load = new LoadTXT("SAVE");
        load.loadPlayer(1);
        load.loadPlayer(2);
    }
}
