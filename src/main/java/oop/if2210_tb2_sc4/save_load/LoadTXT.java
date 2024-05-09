package oop.if2210_tb2_sc4.save_load;

import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.player.Player;
import oop.if2210_tb2_sc4.card.EffectType;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.deck.Deck;
import oop.if2210_tb2_sc4.shop.Shop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class LoadTXT implements Load {
    private File game_state_file;
    private File player_1_file;
    private File player_2_file;

    public LoadTXT(){
        game_state_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/gamestate.txt");
        player_1_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/player1.txt");
        player_2_file = new File("src/main/java/oop/if2210_tb2_sc4/state/default/player2.txt");
    }
    
    public LoadTXT(String folderName){
        game_state_file = new File("oop/if2210_tb2_sc4/state/" + folderName + "/gamestate.txt");
        player_1_file = new File("oop/if2210_tb2_sc4/" + folderName + "/player1.txt");
        player_2_file = new File("oop/if2210_tb2_sc4/" + folderName + "/player2.txt");
    }

    @Override
    public void loadGameState(){
        // Load game state
        try {
            Scanner scanner = new Scanner(game_state_file);
            GameState.setCurrentPlayer(scanner.nextInt());
            GameState.setCountItems(scanner.nextInt());

            Shop shop = new Shop();
            for (int i = 0; i < GameState.getCountItems(); i++){
                String item = scanner.next();
                Integer count = scanner.nextInt();
                shop.addCard((ProductCard) GameData.getCard(item), count);
            }
            GameState.setShop(shop);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Player loadPlayer(int no_player){
        try {
            File temp = (no_player == 1) ? player_1_file : player_2_file;
            Scanner scanner = new Scanner(temp);
            Player player = new Player();
            
            player.setJumlahGulden(scanner.nextInt());
            player.getDeck().setCardsInDeckCount(scanner.nextInt());
            player.getDeck().setCardsInHandCount(scanner.nextInt());


            Deck tempDeck = new Deck();
            for (int i = 0; i < player.getJumlahDeckActive(); i++){
                String card = scanner.next();
                String card_id = scanner.next();
                tempDeck.setActiveCard(card, GameData.createCard(card_id));
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

                if (newCard instanceof oop.if2210_tb2_sc4.card.PlantCard){
                    ((oop.if2210_tb2_sc4.card.PlantCard) newCard).setAge(umur);
                } else if (newCard instanceof oop.if2210_tb2_sc4.card.AnimalCard){
                    ((oop.if2210_tb2_sc4.card.AnimalCard) newCard).setWeight(umur);
                }

                for (int j = 0; j < count; j++){
                    String item = scanner.next();

                    if (item == "ACCELERATE"){
                        newCard.addEffect(EffectType.ACCELERATE);
                    } else if (item == "DELAY"){
                        newCard.addEffect(EffectType.DELAY);
                    }  else if (item == "INSTANT_HARVEST"){
                        newCard.addEffect(EffectType.INSTANT_HARVEST);
                    } else if (item == "PROTECTION"){
                        newCard.addEffect(EffectType.PROTECTION);
                    } else if (item == "TRAP"){
                        newCard.addEffect(EffectType.TRAP);
                    }
                }

                tempLadang.setCard(lokasi, newCard);
            }
            player.setLadang(tempLadang);

            scanner.close();
            System.out.println("OutSafely" + no_player);
            return player;
            
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } 
        return null;
    }
}
