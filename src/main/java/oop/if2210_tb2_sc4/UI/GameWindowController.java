package oop.if2210_tb2_sc4.UI;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Font;

import javafx.util.Duration;
import oop.if2210_tb2_sc4.card.Card;
import oop.if2210_tb2_sc4.deck.Deck;
import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.player.Player;
import org.jetbrains.annotations.NotNull;


import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GameWindowController {

//  Ui Related Variable
    private static PlayerUI currentPlayerPane;
    private static PlayerUI nextPlayerPane;

    private static PlayerUI player1;
    private static PlayerUI player2;

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
    public AnchorPane EndScreen;
    public Pane EndPane;
    public StackPane RootStack;
    public Label AvailableDeck;

    private Tab ladang;
    private Tab ladangMusuh;
    private Tab shop;
    private Tab save;
    private Tab load;
    private Tab addPlugin;

    public static SellZone sellZone;
    private static int roundrobin = 0;
    public static LadangUI ladang1;
    public static LadangUI ladang2;
    private  boolean running;

    private SelectCardsController cardPicker;

    private Thread gameThread;

    //    Make GameWindowController as Singleton obj
    private static GameWindowController instance;

    public static GameWindowController getInstance(){
        if(instance == null){
            instance = new GameWindowController();
        }
        return instance;
    }

    public void initialize() throws IOException {
        rootStatic = root;
        roundrobin = 0;
        CurrentTurn.setText(String.valueOf(roundrobin + 1));
        initializePlayer();
        initMainTab();
        startGame();
        initCardPicker();
        cardPicker.ShuffleCards();
        initializeThread();
    }

    private void initializeThread(){
        gameThread = new Thread(this::gameLoop); // Create a new thread with the gameLoop as the target
        running = true; // Set the running flag to true to start the game loop
        gameThread.start(); // Start the thread to execute the game loop
    }

    private void gameLoop(){
        final int TARGET_UPDATE_TIME = 60;
        final long UPDATE_TIME = 1000000000L / TARGET_UPDATE_TIME;

        long lastUpdated = System.nanoTime();
        long elapsedTime = 0;

        while (running) {
            long currentTimeNs = System.nanoTime();
            long passedTimeNs = currentTimeNs - lastUpdated;
            lastUpdated = currentTimeNs;

            elapsedTime += passedTimeNs;

            // Update the game if it's time for an update
            while (elapsedTime >= UPDATE_TIME) {
                update(); // Update game logic
                elapsedTime -= UPDATE_TIME;
            }

            // Add a sleep to control the update rate
            long sleepTimeMs = (UPDATE_TIME - elapsedTime) / 1_000_000L; // Convert nanoseconds to milliseconds
            if (sleepTimeMs > 0) {
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void update() {
        int gold1 = player1.getPlayerData().getJumlahGulden();
        int gold2 = player2.getPlayerData().getJumlahGulden();
        String player1GoldText = "Player1: " + gold1;
        String player2GoldText = "Player2: " + gold2;
        int maxDeck = currentPlayerPane.getPlayerData().getDeck().getMaxCards();
        int usedCard = currentPlayerPane.getPlayerData().getDeck().getCardsInDeckCount();
        String availableDeckText = usedCard + "/" + maxDeck;
        String currentTurnText = String.valueOf(roundrobin + 1);

        currentPlayerPane.getDeckUI().UpdateDataDeck();
        currentPlayerPane.getLadang().UpdateLadangData();
        // Update UI components on JavaFX Application Thread
        Platform.runLater(() -> {
            Player1Gold.setText(player1GoldText);
            Player2Gold.setText(player2GoldText);
            AvailableDeck.setText(availableDeckText);
            CurrentTurn.setText(currentTurnText);
        });
    }

    private void startGame(){
        nextPlayerPane.setVisible(false);
    }

//    private void initialize

    private void initializeDeck(Player player){
        List<Card> allCards = GameData.getAllCards();
        Deck newDeck = new Deck();
        newDeck.addCardToDeck(allCards);
        for(Card card : allCards){
            if(newDeck.isDeckFull()){
                break;
            }
            newDeck.addCardToDeck(card);
        }
        player.setDeck(newDeck);
    }

    public void initializePlayer() {

        // Initialize Player Data
        Player player1Data = new Player();
        Player player2Data = new Player();

        GameData.initCards();
        //Initialize Decks
        initializeDeck(player1Data);
        initializeDeck(player2Data);

        // Initialize Player panes
        player1 = new PlayerUI(player1Data);
        player2 = new PlayerUI(player2Data);

        // Initialize player current and next panes
        currentPlayerPane = player1;
        nextPlayerPane = player2;
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
    }

    private void StopThread(){
        running = false;
    }

    public boolean TryEndGame(){
        if(roundrobin + 1 >= 20) {
            running = false;
            int player1gold = player1.getPlayerData().getJumlahGulden();
            int player2gold = player2.getPlayerData().getJumlahGulden();
            Label Header = (Label)EndPane.getChildren().get(0);
            Label Winner = (Label)EndPane.getChildren().get(1);
            Label EndTitle = (Label)EndPane.getChildren().get(2);
            EndScreen.setVisible(true);
            if (player1gold > player2gold)
            {
                Winner.setText("Player 1");
            } else if (player1gold < player2gold) {
                Winner.setText("Player 2");
            } else {
                Font smallerFont = new Font(32);
                Header.setFont(smallerFont);
                Header.setText("Permainan Berakhir");
                Winner.setVisible(false);
                EndTitle.setFont(smallerFont);
                EndTitle.setText("Game Berakhir Seri");
            }
            return true;
        }
        return false;
    }

    public void switchToNextPlayer() {
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

        GameState.setCurrentPlayer((roundrobin) % 2);

        roundrobin++;
        cardPicker.InvokePanel();
        resetFieldLock();
        // TODO: MAKE THE SWITCHING ANIMATION
        openLadang();

        System.out.println("Switched to next player");
    }

    private void resetFieldLock(){
        currentPlayerPane.enableField();
        nextPlayerPane.enableField();
    }

    public static void addCard(Card card) {
        currentPlayerPane.addCard(card);
    }

    public static PlayerUI getCurrentPlayerPane(){
        return currentPlayerPane;
    }
    public static PlayerUI getNextPlayerPane(){
        return nextPlayerPane;
    }

    public static void addItem(Card card) {
        currentPlayerPane.addItem(card);
        //currentPlayerPane.addItem(name,roundrobin, Target.ENEMY);
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

    private void initShop() throws IOException {
        // Init Shop
        FXMLLoader loaderShop = new FXMLLoader(Objects.requireNonNull(getClass().getResource("Shop.fxml")));
        AnchorPane shopPane = loaderShop.load();
        StackPane temp_shop = new StackPane();
        temp_shop.setAlignment(Pos.CENTER);
        temp_shop.getChildren().add(shopPane);
        temp_shop.setPadding(new Insets(10, 10, 10, 10));
        shop.setContent(temp_shop);
        shopUI = loaderShop.getController();
        shopUI.initializeShopData();
    }

    private void initSaveLoad() throws IOException {
        save.setContent(makeSaveLoadPane());
        load.setContent(makeSaveLoadPane());
    }

    private StackPane makeSaveLoadPane() throws IOException {
        AnchorPane savePane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SaveLoad.fxml")));
        StackPane temp_save = new StackPane();
        temp_save.setAlignment(Pos.CENTER);
        temp_save.getChildren().add(savePane);
        temp_save.setPadding(new Insets(10, 10, 10, 100));
        return temp_save;
    }

    private void initAddPlugin() throws IOException {
        // Init Add Plugin
        AnchorPane addPluginPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPlugin.fxml")));
        StackPane temp_addPlugin = new StackPane();
        temp_addPlugin.setAlignment(Pos.CENTER);
        temp_addPlugin.getChildren().add(addPluginPane);
        temp_addPlugin.setPadding(new Insets(10, 10, 10, 100));
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
        if (roundrobin % 2 == 0){
            tabPane.getSelectionModel().select(ladang);
        } else {
            tabPane.getSelectionModel().select(ladangMusuh);
        }
    }

    public void openLadangMusuh(){
        currentPlayerPane.disableField();
        if (roundrobin % 2 == 0){
            tabPane.getSelectionModel().select(ladangMusuh);
        } else {
            tabPane.getSelectionModel().select(ladang);
        }
    }

    public void openShop(){
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(shop);
    }

    public void openSave(){
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(save);
    }

    public void openLoad(){
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(load);
    }

    public void openAddPlugin(){
        currentPlayerPane.disableField();
        tabPane.getSelectionModel().select(addPlugin);
    }

    public void StartNewGame(ActionEvent actionEvent) {
        MainMenuController mainMenuController = new MainMenuController();
        try{
            mainMenuController.NewGame(actionEvent);
            initialize();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
}
