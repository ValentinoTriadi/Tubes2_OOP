package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.Exception.GameException;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Plugins;
import oop.if2210_tb2_sc4.save_load.Save;
import oop.if2210_tb2_sc4.save_load.SaveTXT;

import java.io.IOException;

public class SaveUI {
    private final SaveLoad controller;
    private Save saver;
    private AnchorPane scene;
    public SaveUI(SaveLoad controller){
        this.controller = controller;
    }

    public void initialize() {

        controller.title.setText("Save");

        System.out.println("Load UI");

        controller.clickButton.setOnMouseClicked(event -> {
            OnSaveLoad(event);
            System.out.println("Button clicked!");
        });

        controller.ActivateMascot(this);

    }

    private void handleTextInputChange(String choice){

        String path = controller.getFolderName();

        switch (choice.toLowerCase()) {
            case "txt":
                saver = new SaveTXT(path);
                break;
            default:
                saver = Plugins.getInstance().getPlugin(choice);
                break;
        }
    }

    public SaveLoad getController(){return  controller; }

    public void OnSaveLoad(MouseEvent mouseEvent) {
        try{
            controller.OnSaveLoad();

            String newText = controller.choice.getValue();
            handleTextInputChange(newText);
            if(saver == null){
                MessageBox.showErrorMessage("Non Exist Choice", "Tolong pilih salah satu jenis file yang akan disave");
                return;
            }

            GameState.getInstance().setPlayer(1, GameWindowController.getPlayer1().getPlayerData());
            GameState.getInstance().setPlayer(2, GameWindowController.getPlayer2().getPlayerData());

            saver.save(controller.getFolderName());
        }catch (GameException e){
            e.ShowErrorPanel();
        }
    }
}
