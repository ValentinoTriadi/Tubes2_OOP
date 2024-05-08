package oop.if2210_tb2_sc4.UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;
import javafx.geometry.Pos;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class GameWindowController {

    private static PlayerUI currentPlayerPane;
    private static PlayerUI nextPlayerPane;

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

    private Tab ladang;
    private Tab ladangMusuh;
    private Tab shop;
    private Tab save;
    private Tab load;
    private Tab addPlugin;

    private static int roundrobin = 0;

    public void initialize() throws IOException {
        rootStatic = root;
        initializePlayer();
        initMainTab();
        startGame();
    }

    private void startGame(){
        nextPlayerPane.setVisible(false);
    }

    public void initializePlayer() throws IOException {

        // Initialize player panes
        currentPlayerPane = new PlayerUI();
        nextPlayerPane = new PlayerUI();

        ladang = new Tab();
        ladangMusuh = new Tab();

        currentPlayerPane.initPlayerUI("Player 1", ladang);
        nextPlayerPane.initPlayerUI("Player 2", ladangMusuh);

        // Set properties
        currentPlayerPane.setAlignment(Pos.CENTER);
        nextPlayerPane.setAlignment(Pos.CENTER);

        currentPlayerPane.setPrefSize(800, 600);
        nextPlayerPane.setPrefSize(800, 600);

        // Add both player panes to the root and hide the next player's pane
        rootPane.getChildren().addAll(currentPlayerPane, nextPlayerPane);
    }

    public void switchToNextPlayer() {

        // TODO: CHECK WIN CONDITION

        // Hide the current player's pane
        currentPlayerPane.setVisible(false);

        // Show the next player's pane
        nextPlayerPane.setVisible(true);

        // Swap the current and next player's pane
        PlayerUI temp = currentPlayerPane;
        currentPlayerPane = nextPlayerPane;
        nextPlayerPane = temp;

        roundrobin++;
        resetFieldLock();
        // TODO: MAKE THE SWITCHING ANIMATION
        openLadang();

        System.out.println("Switched to next player");
    }

    private void resetFieldLock(){
        currentPlayerPane.enableField();
        nextPlayerPane.enableField();
    }

    public void addCard() {
        currentPlayerPane.addCard();
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
        initSave();
        initLoad();
        initAddPlugin();
    }

    private void initLadang(){
        // Set properties
        ladang.setContent(currentPlayerPane.getLadang());
        ladangMusuh.setContent(nextPlayerPane.getLadang());
    }

    private void initShop() throws IOException {
        // Init Shop
        AnchorPane shopPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Shop.fxml")));
        StackPane temp_shop = new StackPane();
        temp_shop.getChildren().add(shopPane);
        shop.setContent(temp_shop);
    }

    private void initLoad() throws IOException {
        // Init Load
        AnchorPane loadPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Load.fxml")));
        StackPane temp_load = new StackPane();
        temp_load.getChildren().add(loadPane);
        load.setContent(temp_load);
    }

    private void initSave() throws IOException {
        // Init Save
        AnchorPane savePane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Save.fxml")));
        StackPane temp_save = new StackPane();
        temp_save.getChildren().add(savePane);
        save.setContent(temp_save);
    }

    private void initAddPlugin() throws IOException {
        // Init Add Plugin
        AnchorPane addPluginPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPlugin.fxml")));
        StackPane temp_addPlugin = new StackPane();
        temp_addPlugin.getChildren().add(addPluginPane);
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
        if (roundrobin % 2 == 0){
            currentPlayerPane.enableField();
            tabPane.getSelectionModel().select(ladang);
        } else {
            currentPlayerPane.enableField();
            tabPane.getSelectionModel().select(ladangMusuh);
        }
    }

    public void openLadangMusuh(){
        if (roundrobin % 2 == 0){
            currentPlayerPane.disableField();
            tabPane.getSelectionModel().select(ladangMusuh);
        } else {
            currentPlayerPane.disableField();
            tabPane.getSelectionModel().select(ladang);
        }
    }

    public void openShop(){
        tabPane.getSelectionModel().select(shop);
    }

    public void openSave(){
        tabPane.getSelectionModel().select(save);
    }

    public void openLoad(){
        tabPane.getSelectionModel().select(load);
    }

    public void openAddPlugin(){
        tabPane.getSelectionModel().select(addPlugin);
    }
}
