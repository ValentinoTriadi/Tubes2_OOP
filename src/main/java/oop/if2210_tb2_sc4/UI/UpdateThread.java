package oop.if2210_tb2_sc4.UI;

import javafx.application.Platform;
import javafx.scene.control.Label;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.GameData;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Player;

public class UpdateThread implements Runnable {
    private boolean running;
    private Thread gameThread;
    private final Label player1GoldLabel;
    private final Label player2GoldLabel;
    private final Label availableDeckLabel;
    private final Label currentTurnLabel;

    private Player player1;
    private Player player2;

    // Constructor to pass UI components
    public UpdateThread(Label player1GoldLabel, Label player2GoldLabel, Label availableDeckLabel, Label currentTurnLabel) {
        this.player1GoldLabel = player1GoldLabel;
        this.player2GoldLabel = player2GoldLabel;
        this.availableDeckLabel = availableDeckLabel;
        this.currentTurnLabel = currentTurnLabel;
    }

    // Method to start the thread
    public void initializeThread() {
        if (gameThread == null || !gameThread.isAlive()) {
            gameThread = new Thread(this); // Create a new thread with this class as the target
            running = true;
            gameThread.start();
        }
    }

    // Method to pause the thread
    public void pauseThread(long milisecond) throws InterruptedException {
        Thread.sleep(milisecond);
    }

    // Method to resume the thread
    public void resumeThread() {
        running = true;
    }

    // Method to stop the thread
    public void stopThread() {
        running = false;
        try {
            gameThread.join(); // Wait for the thread to terminate
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        final int TARGET_UPDATE_TIME = 60;
        final long UPDATE_TIME = 1000000000L / TARGET_UPDATE_TIME;

        long lastUpdated = System.nanoTime();
        long elapsedTime = 0;

        while (!Thread.currentThread().isInterrupted() && running) {
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
            long sleepTimeMs = (UPDATE_TIME - elapsedTime) / 1_000_000L;
            if (sleepTimeMs > 0) {
                try {
                    Thread.sleep(sleepTimeMs);
                } catch (InterruptedException e) {
                    // Restore the interrupted status
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    private void update() {
        try{
            Player player1 = GameWindowController.getPlayer1().getPlayerData();
            Player player2 = GameWindowController.getPlayer2().getPlayerData();
            //System.out.println("Player 1 Ladang: "+ GameWindowController.getCurrentPlayerPane().getLadang().getAllCardWithLocationinLadang().size());

            int currentTurn = GameState.getInstance().getCurrentPlayer();

            updatePlayerGold(player1, player2);
            updateAvailableDeck(GameWindowController.getCurrentPlayerPane().getPlayerData());
            //updatePlayerDeck(GameWindowController.getCurrentPlayerPane().getDeckUI());
            updateLadang(GameWindowController.getCurrentPlayerPane().getLadang());
            updateCurrentTurn(currentTurn);
        }catch (Exception e){
            System.out.println(e.getMessage());
            //DO NOTHING
        }
    }

    private void updateLadang(LadangUI ladangUI){
        ladangUI.UpdateLadangData();
    }

    private void updatePlayerDeck(DeckUI deck){
        // Remove any card from hand if a change made
        deck.UpdateDataDeck();
    }

    private void updatePlayerGold(Player player1, Player player2) {
        int gold1 = player1.getJumlahGulden();
        int gold2 = player2.getJumlahGulden();
        String player1GoldText = String.valueOf(gold1);
        String player2GoldText = String.valueOf(gold2);
        Platform.runLater(() -> {
            player1GoldLabel.setText(player1GoldText);
            player2GoldLabel.setText(player2GoldText);
        });
    }

    private void updateAvailableDeck(Player currentPlayer) {
        int maxDeck = currentPlayer.getDeck().getCurrentDeck().size();
        int usedCard = maxDeck - currentPlayer.getJumlahDeck();
        String availableDeckText = usedCard + "/" + maxDeck;

        Platform.runLater(() -> {
            availableDeckLabel.setText(availableDeckText);
        });
    }

    private void updateCurrentTurn(int turn) {
        String currentTurnText = String.valueOf(turn);

        Platform.runLater(() -> {
            currentTurnLabel.setText(currentTurnText);
        });
    }

}
