package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.save_load.Load;
import oop.if2210_tb2_sc4.save_load.LoadTXT;

public class LoadUi extends SaveLoad{
    protected Load loader;
    private final SaveLoad controller;
    private final GameWindowController threadControl;

    private AnchorPane scene;
    public LoadUi(SaveLoad controller,GameWindowController rootController){
        this.controller = controller;
        this.threadControl = rootController;
    }


    public void initialize() {

        controller.title.setText("Load Game");

        controller.message.setText("Click The Button to Save The Data");
        System.out.println("Load UI");

        controller.clickButton.setOnMouseClicked(event -> {
            OnSaveLoad(event);
            System.out.println("Button clicked!");
        });

    }
    private void handleTextInputChange(String choice){
        String path = controller.getFolderName();

        switch (choice.toLowerCase()) {
            case "txt":
                loader = new LoadTXT(path);
                break;
            case "yaml":
                // Handle YAML choice
                System.out.println("YAML chosen");
                break;
            case "json":
                // Handle JSON choice
                System.out.println("JSON chosen");
                break;
            default:
                loader = null;
                break;
        }
    }
    public SaveLoad getController(){return  controller; }

    public void OnSaveLoad(MouseEvent mouseEvent) {
        // Get the text from the ComboBox
        String newText = controller.choice.getValue();

        // Do something with the new text, such as invoking a function
        handleTextInputChange(newText);
        if(loader == null){
            MessageBox.showErrorMessage("Non Exist Choice", "Tolong pilih salah satu jenis file yang akan disave");
            return;
        }

        try{
            threadControl.PostPoneThread(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        controller.OnSaveLoad();
        LoadState();
        LoadPlayer();
        //LoadDeck();
        LoadLadang();
    }
    private void LoadLadang(){
        LadangUI ladangUI1 = GameWindowController.getPlayer1().getLadang();
        LadangUI ladangUI2 = GameWindowController.getPlayer2().getLadang();
        ladangUI1.UpdateLadangUI();
        ladangUI2.UpdateLadangUI();
    }
    private void LoadDeck(){
        DeckUI deckUI1 = GameWindowController.getPlayer1().getDeckUI();
        DeckUI deckUI2 = GameWindowController.getPlayer2().getDeckUI();

    }
    private void LoadPlayer(){
        GameWindowController.getPlayer1().setPlayerData(loader.loadPlayer(1));
        GameWindowController.getPlayer2().setPlayerData(loader.loadPlayer(2));
    }
    private void LoadState(){
        loader.loadGameState();
    }
}