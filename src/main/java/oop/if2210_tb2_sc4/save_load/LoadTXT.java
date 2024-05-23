package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.GameData;
import oop.if2210_tb2_sc4.GameState;
import oop.if2210_tb2_sc4.Ladang;
import oop.if2210_tb2_sc4.Player;
import oop.if2210_tb2_sc4.card.EffectType;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.Deck;
import oop.if2210_tb2_sc4.Shop;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;



public class LoadTXT implements Load {
    private final InputStream game_state_file;
    private final InputStream player_1_file;
    private final InputStream player_2_file;

    public LoadTXT(){
        game_state_file = getClass().getResourceAsStream("default/gamestate.txt");
        player_1_file = getClass().getResourceAsStream("default/player1.txt");
        player_2_file = getClass().getResourceAsStream("default/player2.txt");
        GameData.initCards();
    }
    
    public LoadTXT(String folderName){
        game_state_file = getClass().getResourceAsStream(folderName + "/gamestate.txt");
        player_1_file = getClass().getResourceAsStream(folderName + "/player1.txt");
        player_2_file = getClass().getResourceAsStream(folderName + "/player2.txt");
    }

    @Override
    public void loadGameState(){
        // Load game state
        try {
            Scanner scanner = new Scanner(game_state_file);
            GameState.getInstance().setCurrentPlayer(scanner.nextInt());
            int countItem = scanner.nextInt();

            Shop shop = new Shop();
            for (int i = 0; i < countItem; i++){
                String item = scanner.next();
                int count = scanner.nextInt();
                shop.addCard((ProductCard) GameData.getCard(item), count);
            }
            GameState.getInstance().setShop(shop);
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Player loadPlayer(int no_player){
        try {
            InputStream temp = (no_player == 1) ? player_1_file : player_2_file;
            Scanner scanner = new Scanner(temp);
            Player player = new Player();
            Deck tempDeck = new Deck();

            player.setJumlahGulden(scanner.nextInt());
            tempDeck.setCardsInDeckCount(scanner.nextInt());
            tempDeck.setCardsInHandCount(scanner.nextInt());


            for (int i = 0; i < tempDeck.getActiveCardinHandCount(); i++){
                String slot = scanner.next();
                String card_id = scanner.next();
                tempDeck.setActiveCard(slot, GameData.createCard(card_id));
            }
            player.setDeck(tempDeck);

            int countCardinLandang = scanner.nextInt();

            Ladang tempLadang = new Ladang();
            for (int i = 0; i < countCardinLandang; i++){
                
                String lokasi = scanner.next();
                String kartu = scanner.next();
                int umur = scanner.nextInt();
                int count = scanner.nextInt();

                FarmResourceCard newCard = (FarmResourceCard) GameData.createCard(kartu);

                // check if newCard null
                assert newCard != null : "Card not found";

                if (newCard instanceof oop.if2210_tb2_sc4.card.PlantCard){
                    ((oop.if2210_tb2_sc4.card.PlantCard) newCard).setAge(umur);
                } else if (newCard instanceof oop.if2210_tb2_sc4.card.AnimalCard){
                    ((oop.if2210_tb2_sc4.card.AnimalCard) newCard).setWeight(umur);
                }

                for (int j = 0; j < count; j++){
                    String item = scanner.next();

                    if (Objects.equals(item, "ACCELERATE")){
                        newCard.addEffect(EffectType.ACCELERATE);
                    } else if (Objects.equals(item, "DELAY")){
                        newCard.addEffect(EffectType.DELAY);
                    }  else if (Objects.equals(item, "INSTANT_HARVEST")){
                        newCard.addEffect(EffectType.INSTANT_HARVEST);
                    } else if (Objects.equals(item, "PROTECT")){
                        newCard.addEffect(EffectType.PROTECT);
                    } else if (Objects.equals(item, "TRAP")){
                        newCard.addEffect(EffectType.TRAP);
                    }
                }

                tempLadang.setCard(lokasi, newCard);
            }
            player.setLadang(tempLadang);

            scanner.close();
            return player;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } 
        return null;
    }

    public InputStream getGameStateFile() {
        return game_state_file;
    }

    public InputStream getPlayer1File() {
        return player_1_file;
    }

    public InputStream getPlayer2File() {
        return player_2_file;
    }
}
