package oop.if2210_tb2_sc4.UI;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import oop.if2210_tb2_sc4.Exception.FolderNotExistException;
import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.Exception.GameException;
import oop.if2210_tb2_sc4.Exception.InvalidInputException;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.Plugins;
import oop.if2210_tb2_sc4.save_load.Load;
import oop.if2210_tb2_sc4.save_load.LoadTXT;
import oop.if2210_tb2_sc4.save_load.Save;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Objects;

public class LoadUi {
    protected Load loader;
    private final SaveLoad controller;
    private final GameWindowController threadControl;

    private AnchorPane scene;
    public LoadUi(SaveLoad controller,GameWindowController rootController){
        this.controller = controller;
        this.threadControl = rootController;
    }


    public void initialize() {

        controller.title.setText("Load");

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
                loader = new LoadTXT(path);
                break;
            default:
                Class loaderClass = Plugins.getInstance().getPlugin(choice).getClass();
                try {
                    loader = (Load) loaderClass.getConstructor(String.class).newInstance(path);
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }

                break;
        }
    }
    public SaveLoad getController(){return  controller; }

    public void OnSaveLoad(MouseEvent mouseEvent) {
        try{
            controller.OnSaveLoad();
            handleInput();
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
            LoadState();
            LoadPlayer();
            LoadDeck();
            LoadLadang();
        }catch (GameException e){
            e.ShowErrorPanel();
            return;
        }
    }
    private void LoadLadang(){
        LadangUI ladangUI1 = GameWindowController.getPlayer1().getLadang();
        LadangUI ladangUI2 = GameWindowController.getPlayer2().getLadang();
        ladangUI1.UpdateLadangUI();
        ladangUI2.UpdateLadangUI();
    }
    private void LoadDeck(){
        DeckUI deckUI1 = GameWindowController.getPlayer1().getDeckUI();
        Deck newDeck = deckUI1.getDeckData().initializeDeck(deckUI1.getDeckData());
        deckUI1.setDeckData(newDeck);
        DeckUI deckUI2 = GameWindowController.getPlayer2().getDeckUI();
        newDeck = deckUI2.getDeckData().initializeDeck(deckUI2.getDeckData());
        deckUI2.setDeckData(newDeck);
        try{
            deckUI1.UpdateUIDeck(GameWindowController.getPlayer1());
            deckUI2.UpdateUIDeck(GameWindowController.getPlayer2());
        }catch (FullActiveHandsException e){
            System.out.println("The Data Loaded are Wrong in terms of Deck");
        }
    }


    public void handleInput() throws GameException {
        controller.handleInput();
        if(!isFolderExist(controller.getFolderName())){
            throw new FolderNotExistException("Folder Couldn't be found");
        }
    }

    private boolean isFolderExist(String folderPath){
        URL path = Save.class.getResource(folderPath);
        if (path != null) {
            File folder = new File(path.getFile());
            return folder.exists() && folder.isDirectory();
        } else {
            return false;
        }
    }


    private void LoadPlayer(){
        PlayerUI playerUI1 = GameWindowController.getPlayer1();
        playerUI1.setPlayerData(GameState.getInstance().getPlayer(1));

        PlayerUI playerUI2 = GameWindowController.getPlayer2();
        playerUI2.setPlayerData(GameState.getInstance().getPlayer(2));
    }

    private void LoadState(){
        loader.loadGameState();
        loader.loadPlayer(1);
        loader.loadPlayer(2);
    }
}