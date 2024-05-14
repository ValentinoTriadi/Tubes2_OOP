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
import javafx.scene.text.Font;

import oop.if2210_tb2_sc4.Exception.FullActiveHandsException;
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
    public static LadangUI ladang1;
    public static LadangUI ladang2;
    private static boolean running;

    private UpdateThread gameThread;
    private SaveUI saver;
    private LoadUi loader;

    private SelectCardsController cardPicker;



    public void initialize() throws IOException {
        GameState instance = GameState.getInstance();
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
    }

    public void PostPoneThread(long milisecond) throws InterruptedException {
        try{
            gameThread.pauseThread(milisecond);
        }
        catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
    }


    public boolean TryEndGame(){
        if(GameState.getInstance().getCurrentPlayer() >= 20) {
            gameThread.stopThread();
            int player1gold = getPlayer1().getPlayerData().getJumlahGulden();
            int player2gold = getPlayer2().getPlayerData().getJumlahGulden();
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

        GameState instance = GameState.getInstance();
        instance.setCurrentPlayer(instance.getCurrentPlayer() + 1);


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
        try {
            currentPlayerPane.addCard(card);
        }catch (FullActiveHandsException ignored){

        }
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

    public static void addItem(Card card) {
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
        temp_shop.setPadding(new Insets(10, 10, 10, 10));
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
    }

    private StackPane MakeSaveLoadPanel(AnchorPane savePane){
        StackPane temp_save = new StackPane();
        temp_save.setAlignment(Pos.CENTER);
        temp_save.getChildren().add(savePane);
        temp_save.setPadding(new Insets(10, 10, 10, 100));
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
        if (GameState.getInstance().getCurrentPlayer() % 2 == 1){
            tabPane.getSelectionModel().select(ladang);
        } else {
            tabPane.getSelectionModel().select(ladangMusuh);
        }
    }

    public void openLadangMusuh(){
        currentPlayerPane.disableField();
        if (GameState.getInstance().getCurrentPlayer() % 2 == 1){
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
        saver.initialize();
        tabPane.getSelectionModel().select(save);

    }

    public void openLoad(){
        currentPlayerPane.disableField();
        loader.initialize();
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