package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.player.Player;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SaveTXTTest {

    @Test
    void save() {
        LoadTXT load = new LoadTXT();
        load.loadGameState();
        Player p1 = load.loadPlayer(1);
        Player p2 = load.loadPlayer(2);

        SaveTXT save = new SaveTXT("SAVETEST", p1, p2);
        save.save();

        try {
            Scanner sc1 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/default/gamestate.txt"));
            Scanner sc2 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/SAVETEST/gamestate.txt"));

            // check currentPlayer
            assertEquals(sc1.nextLine(), sc2.nextLine());
            // check count item
            String count = sc1.nextLine();
            assertEquals(count, sc2.nextLine());
            // check item inside
            List<String> s1 = new ArrayList<>();
            List<String> s2 = new ArrayList<>();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                s1.add(sc1.nextLine());
                s2.add(sc2.nextLine());
            }
            Collections.sort(s1);
            Collections.sort(s2);
            assertEquals(s1,s2);
            assert !sc1.hasNext() && !sc2.hasNext() : "File doesn't same";

            sc1 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/default/player1.txt"));
            sc2 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/SAVETEST/player1.txt"));
            while (sc1.hasNextLine() && sc2.hasNextLine()){
                assertEquals(sc1.nextLine(), sc2.nextLine());
            }
            assert !sc1.hasNext() && !sc2.hasNext() : "File doesn't same";

            sc1 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/default/player2.txt"));
            sc2 = new Scanner(new File("src/main/resources/oop/if2210_tb2_sc4/save_load/SAVETEST/player2.txt"));
            while (sc1.hasNextLine() && sc2.hasNextLine()){
                assertEquals(sc1.nextLine(), sc2.nextLine());
            }
            assert !sc1.hasNext() && !sc2.hasNext() : "File doesn't same";
        } catch (Exception e){
            assert true;
        }
    }
}