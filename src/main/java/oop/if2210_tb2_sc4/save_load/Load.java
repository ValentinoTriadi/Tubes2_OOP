package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.Player;

public interface Load {
    void loadGameState();
    Player loadPlayer(int no_player);
}
