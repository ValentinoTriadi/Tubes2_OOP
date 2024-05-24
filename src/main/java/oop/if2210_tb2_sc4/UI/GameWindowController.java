package oop.if2210_tb2_sc4.UI;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.card.PlantCard;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.GameData;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Player;
import oop.if2210_tb2_sc4.save_load.Load;
import org.jetbrains.annotations.NotNull;


import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class GameWindowController {

//  Ui Related Variable
    private static PlayerUI currentPlayerPane;
    private static PlayerUI nextPlayerPane;

    private static ShopUI shopUI;

    @FXML
    public AnchorPane root;
    public StackPane rootPane;

    public Button MyFieldButton;
    public Button EnemyFieldButton;
    public Button ShopButton;
    public Button SaveButton;
    public Button LoadButton;
    public Button AddPluginButton;
    public static AnchorPane rootStatic;

    public TabPane tabPane;
    public Label CurrentTurn;
    public Label Player2Gold;
    public Label Player1Gold;
    public StackPane RootStack;
    public Label AvailableDeck;
    public Button nextTurn;
    public Label ladangLabel;
    public Label enemyLadangLabel;
    public Pane bearroot;

    private Tab ladang;
    private Tab ladangMusuh;
    private Tab shop;
    private Tab save;
    private Tab load;
    private Tab addPlugin;
    private final Pane Bear = new Pane();
    private SeranganBeruang seranganBeruang;

    private EndPanel endGamePanel;

    public static SellZone sellZone;
    public static LadangUI ladang1;
    public static LadangUI ladang2;
    private static boolean running;

    private UpdateThread gameThread;
    private SaveUI saver;
    private LoadUi loader;
    public static SaveLoad saveLoad;
    public static SaveLoad saveLoadLoad;

    private final Color currentSelectedButtonColor  = Color.GREEN;

    private SelectCardsController cardPicker;

    private final AudioManager audioManager = AudioManager.getInstance();

    public void initialize() throws IOException {
        GameState instance = GameState.getInstance();
        GameData.ResetData();
        instance.ResetData();
        rootStatic = root;
        instance.setCurrentPlayer(1);
        CurrentTurn.setText(String.valueOf(instance.getCurrentPlayer()));
        initializeMessageBox();
        initializePlayer();
        initMainTab();
        startGame();
        initCardPicker();
        cardPicker.ShuffleCards();
        gameThread = new UpdateThread(Player1Gold, Player2Gold, AvailableDeck, CurrentTurn);
        gameThread.initializeThread();
        audioManager.playBackgroundMusic("FullBG.wav");
        initializeEndPanel();
        setActiveMenuButton(MyFieldButton);
    }

    private void startGame(){
        nextPlayerPane.setVisible(false);

    }

//    private void initialize

    private void initializeDeck(Player player){
        Deck newDeck = player.getDeck().initializeDeck(new Deck());
        player.setDeck(newDeck);
    }

    private void initializeEndPanel() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndPanel.fxml"));
        AnchorPane pane = loader.load();
        this.endGamePanel = loader.getController();
        pane.setVisible(false);
        root.getChildren().add(pane);
        endGamePanel.setRoot(this);
    }

    public void initializePlayer() {

        // Initialize Player Data
        Player player1Data = new Player();
        Player player2Data = new Player();

        GameData.initCards();
        //Initialize Decks
        initializeDeck(player1Data);
        initializeDeck(player2Data);

        // Initialize player current and next panes
        currentPlayerPane = new PlayerUI(player1Data);
        nextPlayerPane = new PlayerUI(player2Data);
        ladang = new Tab();
        ladangMusuh = new Tab();

        currentPlayerPane.initPlayerUI("Player 1", ladang);
        nextPlayerPane.initPlayerUI("Player 2", ladangMusuh);

        ladang1 = currentPlayerPane.getLadang();
        ladang2 = nextPlayerPane.getLadang();

        // Set properties
        currentPlayerPane.setAlignment(Pos.CENTER);
        nextPlayerPane.setAlignment(Pos.CENTER);

        currentPlayerPane.setPrefSize(800, 600);
        nextPlayerPane.setPrefSize(800, 600);

        // Add both player panes to the root and hide the next player's pane
        rootPane.getChildren().addAll(currentPlayerPane, nextPlayerPane);

        int col = currentPlayerPane.getLadang().getColumnCount();
        int row = currentPlayerPane.getLadang().getRowCount();
        seranganBeruang = new SeranganBeruang(col, row, currentPlayerPane.getLadang());
        seranganBeruang.initializer();
        bearroot.setMouseTransparent(true);
    }

    public UpdateThread getGameThread(){
        return gameThread;
    }


    public boolean TryEndGame(){
        if(GameState.getInstance().getCurrentPlayer() >= 20) {
            gameThread.stopThread();
            int player1gold = getPlayer1().getPlayerData().getJumlahGulden();
            int player2gold = getPlayer2().getPlayerData().getJumlahGulden();
            String title = "";
            String winner = "";
            String Message = "";
            if (player1gold > player2gold)
            {
                title = "Congratulations";
                winner = "Player 1";
                Message = "You Win with "+ player1gold+ " Gold";
            } else if (player1gold < player2gold) {
                title = "Congratulations";
                winner = "Player 2";
                Message = "You Win with "+ player2gold+ " Gold";
            } else {
                title = "Game Ended Draw";
                Message = "Both Player have "+player1gold + " Gold";
            }
            endGamePanel.setData(title, winner, Message);
            endGamePanel.ShowEndScreen();
            return true;
        }
        return false;
    }

    private void setActiveMenuButton(Button selectedButton){
        MyFieldButton.setTextFill(Color.WHITE);
        EnemyFieldButton.setTextFill(Color.WHITE);
        ShopButton.setTextFill(Color.WHITE);
        LoadButton.setTextFill(Color.WHITE);
        SaveButton.setTextFill(Color.WHITE);
        AddPluginButton.setTextFill(Color.WHITE);
        selectedButton.setTextFill(currentSelectedButtonColor);
        SetActiveBeruangPane(selectedButton == MyFieldButton);
    }

    public void switchToNextPlayer() {
        if(seranganBeruang.isAlive()){
            return;
        }

        FarmResourceCard[] cardlistLadang = currentPlayerPane.getLadang().getLadangData().getCardListinLadang();
        for(FarmResourceCard card : cardlistLadang) {
            if(card instanceof PlantCard){
                ((PlantCard) card).setAge(((PlantCard) card).getAge()+1);
            }
        }
        // Hide the current player's pane
        currentPlayerPane.setVisible(false);

        if(TryEndGame()){
            //Game Ended
            return;
        }
        // Show the next player's pane
        nextPlayerPane.setVisible(true);

        // Swap the current and next player's pane
        PlayerUI temp = currentPlayerPane;
        currentPlayerPane = nextPlayerPane;

        nextPlayerPane = temp;

        GameState instance = GameState.getInstance();
        instance.setCurrentPlayer(instance.getCurrentPlayer() + 1);


        StartNewTurn();
        resetFieldLock();
        openLadang();
    }

    private void StartNewTurn(){
        BeruangMenyerangPhase();
        cardPicker.InvokePanel(seranganBeruang);
    }
    private void BeruangMenyerangPhase(){
        if (seranganBeruang.isAlive()) {
            seranganBeruang.stopThread();
        }
        bearroot.getChildren().clear();

        LadangUI currentladang = currentPlayerPane.getLadang();
        int col = currentladang.getColumnCount();
        int row = currentladang.getRowCount();
        seranganBeruang = new SeranganBeruang(col, row, currentPlayerPane.getLadang());

        seranganBeruang.initializer();
        bearroot.getChildren().add(seranganBeruang.getPane());
//        seranganBeruang.start();
    }

    private void resetFieldLock(){
        currentPlayerPane.enableField();
        nextPlayerPane.enableField();
        nextPlayerPane.getLadang().setIsDisabled(false);
        currentPlayerPane.getLadang().setIsDisabled(false);
    }

    public static void addCard(Card card) {
        try {
            currentPlayerPane.addCard(card);
        } catch (FullActiveHandsException ignored){}
    }

    public static PlayerUI getPlayer1(){
        if(GameState.getInstance().getCurrentPlayer() % 2 == 1){
            return currentPlayerPane;
        }else{
            return nextPlayerPane;
        }
    }
    public static PlayerUI getPlayer2(){
        if(GameState.getInstance().getCurrentPlayer() % 2 == 0){
            return currentPlayerPane;
        }else{
            return nextPlayerPane;
        }
    }

    public static PlayerUI getCurrentPlayerPane(){
        return currentPlayerPane;
    }
    public static PlayerUI getNextPlayerPane(){
        return nextPlayerPane;
    }

    public static void addItem(Card card){
        try {
            currentPlayerPane.addItem(card);
        }catch (FullActiveHandsException ignored){

        }
    }

    public static ShopUI getShop(){
        return shopUI;
    }

    public void initMainTab() throws IOException {
        // Disable tab closing and enable tab reordering
        tabPane.setTabDragPolicy(TabPane.TabDragPolicy.REORDER);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tabPane.setStyle("-fx-tab-min-height: 0; -fx-tab-max-height: 0; -fx-tab-min-width: 0; -fx-tab-max-width: 0;");
        tabPane.getStyleClass().add("tab-pane-no-tabs");

        // Create tabs
        ladang = createLadang();
        ladangMusuh = createLadang();
        shop = createShop();
        save = createSave();
        load = createLoad();
        addPlugin = createAddPlugin();

        // Add tabs to tab pane
        tabPane.getTabs().addAll(
                ladang,
                ladangMusuh,
                shop,
                save,
                load,
                addPlugin
        );

        // Init content of tabs
        initLadang();
        initShop();
        initSaveLoad();
        initAddPlugin();
    }

    private void initLadang(){
        // Set properties
        ladang.setContent(currentPlayerPane.getLadang());
        ladangMusuh.setContent(nextPlayerPane.getLadang());
    }

    private void initCardPicker() throws  IOException{
        FXMLLoader loadCardPicker = new FXMLLoader(Objects.requireNonNull(getClass().getResource("SelectCards.fxml")));
        AnchorPane SelectCardsPane = loadCardPicker.load();
        RootStack.getChildren().add(SelectCardsPane);
        cardPicker = loadCardPicker.getController();
    }

    private void initializeMessageBox() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MessageBox.fxml"));
        AnchorPane rootMessage = fxmlLoader.load();
        MessageBox.getInstance();
        MessageBox.setBody(rootMessage);
        MessageBox.setRoot(rootStatic);
    }

    private void initShop() throws IOException {
        // Init Shop
        FXMLLoader loaderShop = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Shop.fxml")));
        AnchorPane shopPane = loaderShop.load();
        StackPane temp_shop = new StackPane();
        temp_shop.setAlignment(Pos.CENTER);
        temp_shop.getChildren().add(shopPane);
        temp_shop.setPadding(new Insets(0,50,100,10));
        shop.setContent(temp_shop);
        shopUI = loaderShop.getController();
        shopUI.initializeShopData();
    }

    private void initSaveLoad() throws IOException {
        FXMLLoader saverLoader = MakeSaveLoadFXMLLoader();
        FXMLLoader loadLoader = MakeSaveLoadFXMLLoader();
        AnchorPane savePane  = saverLoader.load();
        AnchorPane loadPane = loadLoader.load();
        save.setContent(MakeSaveLoadPanel(savePane));
        load.setContent(MakeSaveLoadPanel(loadPane));
        saver = new SaveUI(saverLoader.getController());
        loader = new LoadUi(loadLoader.getController(), this);
        saveLoad = saverLoader.getController();
        saveLoadLoad = loadLoader.getController();
    }

    private StackPane MakeSaveLoadPanel(AnchorPane savePane){
        StackPane temp_save = new StackPane();
        temp_save.setAlignment(Pos.CENTER);
        temp_save.getChildren().add(savePane);
        temp_save.setPadding(new Insets(0, 10, 0, 10));
        return  temp_save;
    }

    private FXMLLoader MakeSaveLoadFXMLLoader() throws IOException {
        return new FXMLLoader(Objects.requireNonNull(getClass().getResource("SaveLoad.fxml")));
    }

    private void initAddPlugin() throws IOException {
        // Init Add Plugin
        AnchorPane addPluginPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPlugin.fxml")));
        StackPane temp_addPlugin = new StackPane();
        temp_addPlugin.setAlignment(Pos.CENTER);
        temp_addPlugin.getChildren().add(addPluginPane);
        temp_addPlugin.setPadding(new Insets(0, 10, 0, 10));
        addPlugin.setContent(temp_addPlugin);
    }

    @NotNull
    private Tab createLadang(){
        Tab tabPane = new Tab();
        tabPane.setClosable(false);
        tabPane.setContent(new Pane());
        return tabPane;
    }

    @NotNull
    private Tab createShop(){
        Tab tabPane = new Tab();
        tabPane.setClosable(false);
        tabPane.setContent(new Pane());

        return tabPane;
    }

    @NotNull
    private Tab createSave(){
        Tab tabPane = new Tab();
        tabPane.setClosable(false);
        tabPane.setContent(new Pane());
        return tabPane;
    }

    @NotNull
    private Tab createLoad(){
        Tab tabPane = new Tab();
        tabPane.setClosable(false);
        tabPane.setContent(new Pane());
        return tabPane;
    }

    @NotNull
    private Tab createAddPlugin(){
        Tab tabPane = new Tab();
        tabPane.setClosable(false);
        tabPane.setContent(new Pane());
        return tabPane;
    }

    public void openLadang(){
        currentPlayerPane.enableField();
        setActiveMenuButton(MyFieldButton);
        if (GameState.getInstance().getCurrentPlayer() % 2 == 1){
            tabPane.getSelectionModel().select(ladang);
        } else {
            tabPane.getSelectionModel().select(ladangMusuh);
        }
    }

    public void openLadangMusuh(){
        nextPlayerPane.getLadang().setIsDisabled(true);
        currentPlayerPane.disableField();
        setActiveMenuButton(EnemyFieldButton);
        if (GameState.getInstance().getCurrentPlayer() % 2 == 1){
            tabPane.getSelectionModel().select(ladangMusuh);
        } else {
            tabPane.getSelectionModel().select(ladang);
        }


    }

    public void openShop(){
        setActiveMenuButton(ShopButton);
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(shop);
    }

    public void openSave(){
        setActiveMenuButton(SaveButton);
        currentPlayerPane.disableField();
        saver.initialize();
        tabPane.getSelectionModel().select(save);
    }

    public void openLoad(){
        setActiveMenuButton(LoadButton);
        currentPlayerPane.disableField();
        loader.initialize();
        tabPane.getSelectionModel().select(load);
    }

    private void SetActiveBeruangPane(boolean state){
        bearroot.setVisible(state);
    }

    public void openAddPlugin(){
        setActiveMenuButton(AddPluginButton);
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(addPlugin);
    }

}