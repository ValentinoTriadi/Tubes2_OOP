package oop.if2210_tb2_sc4;

import oop.if2210_tb2_sc4.UI.GameWindowController;
import oop.if2210_tb2_sc4.save_load.SaveLoad;

import java.util.HashMap;
import java.util.Map;

public class Plugins {
    private final Map<String, SaveLoad> plugins = new HashMap<>();

    public static Plugins instance;

    public static Plugins getInstance(){
        if (instance == null) {
            instance = new Plugins();
        }
        return instance;
    }

    public Plugins() {

    }

    public void addClass(Map.Entry<String, SaveLoad> entry){
        plugins.put(entry.getKey(), entry.getValue());
    }

    public SaveLoad getPlugin(String type){
        return plugins.get(type);
    }

    public void loadJar(String path) {
        try {
            Map.Entry<String, SaveLoad> entry = JarLoader.getInstance().loadJar(path);

            if (entry != null) {
                addClass(entry);
                GameWindowController.saveLoad.choice.getItems().add(entry.getKey());
                GameWindowController.saveLoadLoad.choice.getItems().add(entry.getKey());
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
