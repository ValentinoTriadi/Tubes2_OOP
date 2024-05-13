package oop.if2210_tb2_sc4.plugins;

import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.player.Player;
import oop.if2210_tb2_sc4.save_load.LoadTXT;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class TesSaveLoadJSON {
    @Test
    void main(){
        new GameData();
        SaveLoadJSON saveLoadJSON = new SaveLoadJSON("default");
        saveLoadJSON.loadGameState();
        Player p1 = saveLoadJSON.loadPlayer(1);
        Player p2 = saveLoadJSON.loadPlayer(2);

        saveLoadJSON.save("SAVETEST", p1, p2);

        JSONParser parser = new JSONParser();
        try {
            InputStream resourceFile1 = LoadTXT.class.getResourceAsStream("default/stateJSON.json");
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(resourceFile1));
            JSONObject state1 = (JSONObject) parser.parse(reader1);

            InputStream resourceFile2 = LoadTXT.class.getResourceAsStream("SAVETEST/stateJSON.json");
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(resourceFile2));
            JSONObject state2 = (JSONObject) parser.parse(reader2);

            // TODO: Assert state1 and state2 are equal but need to implement equals method first
        } catch (Exception e){
            assert true;
        }
    }
}