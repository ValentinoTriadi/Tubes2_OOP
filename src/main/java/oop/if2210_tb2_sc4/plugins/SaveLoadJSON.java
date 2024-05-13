package oop.if2210_tb2_sc4.plugins;

import oop.if2210_tb2_sc4.card.AnimalCard;
import oop.if2210_tb2_sc4.card.FarmResourceCard;
import oop.if2210_tb2_sc4.card.PlantCard;
import oop.if2210_tb2_sc4.card.ProductCard;
import oop.if2210_tb2_sc4.deck.Deck;
import oop.if2210_tb2_sc4.game_manager.GameData;
import oop.if2210_tb2_sc4.game_manager.GameState;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.player.Player;
import oop.if2210_tb2_sc4.save_load.Load;
import oop.if2210_tb2_sc4.save_load.LoadTXT;
import oop.if2210_tb2_sc4.save_load.Save;
import oop.if2210_tb2_sc4.shop.Shop;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import oop.if2210_tb2_sc4.card.EffectType;

import java.io.*;
import java.util.Map;

public class SaveLoadJSON implements Load, Save {
    private final JSONParser parser;
    private JSONObject gameState;
    private JSONObject player1;
    private JSONObject player2;

    public SaveLoadJSON(String folderName) {
        parser = new JSONParser();
        gameState = new JSONObject();
        player1 = new JSONObject();
        player2 = new JSONObject();
        load(folderName);
    }

    public void load(String folderName) {
        try {
            System.out.println("Load JSON from " + folderName);

            // get Resource File
            InputStream resourceFile = LoadTXT.class.getResourceAsStream(folderName + "/stateJSON.json");
            if (resourceFile != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile));

                // Parse JSON file
                Object obj = parser.parse(reader);
                JSONObject jsonObject = (JSONObject) obj;

                // Set JSON Object
                this.gameState = (JSONObject) jsonObject.get("GameState");
                this.player1 = (JSONObject) jsonObject.get("Player1");
                this.player2 = (JSONObject) jsonObject.get("Player2");

            } else {
                throw new FileNotFoundException("File not found");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void loadGameState() {
        try {
            System.out.println("Load Game State");

            GameState.setCurrentPlayer(Integer.parseInt(gameState.get("CurrentPlayer").toString())); // Set Current Player
            GameState.setCountItems(Integer.parseInt(gameState.get("TotalItems").toString())); // Set Total Items

            // Load Shop Items
            JSONArray items = (JSONArray) gameState.get("Items");
            Shop shop = new Shop();
            for (int i = 0; i < (long) gameState.get("TotalItems"); i++) {
                JSONObject item = (JSONObject) items.get(i);
                shop.addCard((ProductCard) GameData.getCard(item.get("Name").toString()), Integer.parseInt(item.get("Count").toString()));
            }
            GameState.setShop(shop);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Player loadPlayer(int no_player) {
        System.out.println("Load Player " + no_player);

        // init object
        Player playerObj = new Player();
        Deck deck = new Deck();
        Ladang ladang = new Ladang();
        JSONObject player = (no_player == 1) ? player1 : player2;

        // Set Player Object
        playerObj.setJumlahGulden(Integer.parseInt(player.get("Gulden").toString())); // set Gulden
        deck.setCardsInDeckCount(Integer.parseInt(player.get("TotalDeck").toString())); // set Total Deck

        // Set Active Deck
        int totalActive = Integer.parseInt(player.get("TotalActive").toString()); // set Total Active
        deck.setCardsInHandCount(totalActive);

        JSONArray activeDeck = (JSONArray) player.get("ActiveDeck"); // set Active Deck
        for (int i = 0; i < totalActive; i++) {
            JSONObject card = (JSONObject) activeDeck.get(i);
            deck.setActiveCard(card.get("Location").toString(), GameData.createCard(card.get("Name").toString()));
        }
        playerObj.setDeck(deck);

        // Set Ladang
        int countCardinLandang = Integer.parseInt(player.get("TotalLadang").toString()); // set Total Ladang

        JSONArray cardLadang = (JSONArray) player.get("CardLadang"); // set Card Ladang
        for (int i = 0; i < countCardinLandang; i++) {
            JSONObject card = (JSONObject) cardLadang.get(i);

            // Create new Card
            FarmResourceCard newCard = (FarmResourceCard) GameData.createCard(card.get("Name").toString());
            assert newCard != null : "Card not found";
            // TODO: set location

            // Set Age or Weight
            if (newCard instanceof PlantCard) {
                ((PlantCard) newCard).setAge(Integer.parseInt(card.get("AgeorWeight").toString()));
            } else if (newCard instanceof AnimalCard) {
                ((AnimalCard) newCard).setWeight(Integer.parseInt(card.get("AgeorWeight").toString()));
            }

            // Set Effects
            int totalEffect = Integer.parseInt(card.get("TotalEffect").toString());
            JSONArray effects = (JSONArray) card.get("Effects");
            for (int j = 0; j < totalEffect; j++) {
                if (effects.get(j).toString().equals("PROTECT")) {
                    newCard.addEffect(EffectType.PROTECT);
                } else if (effects.get(j).toString().equals("DELAY")) {
                    newCard.addEffect(EffectType.DELAY);
                } else if (effects.get(j).toString().equals("INSTANT_HARVEST")) {
                    newCard.addEffect(EffectType.INSTANT_HARVEST);
                } else if (effects.get(j).toString().equals("TRAP")) {
                    newCard.addEffect(EffectType.TRAP);
                } else if (effects.get(j).toString().equals("ACCELERATE")) {
                    newCard.addEffect(EffectType.ACCELERATE);
                }
            }
            ladang.setCard(card.get("Location").toString(), newCard);
        }
        playerObj.setLadang(ladang);

        return playerObj;
    }

    public void save(String folderName, Player player1, Player player2) {
        JSONObject GameState = saveGameState();
        JSONObject Player1 = savePlayer(player1);
        JSONObject Player2 = savePlayer(player2);

        // Combine JSON
        JSONObject saveData = new JSONObject();
        saveData.put("GameState", GameState);
        saveData.put("Player1", Player1);
        saveData.put("Player2", Player2);

        // Save to JSON
        try {
            // writing JSON to file:"stateJSON.json" in src/main/resources/oop/if2210_tb2_sc4/save_load/{folderName}
            PrintWriter pw = new PrintWriter("src/main/resources/oop/if2210_tb2_sc4/save_load/" + folderName + "/stateJSON.json");
            pw.write(saveData.toJSONString());

            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private JSONObject saveGameState() {
        // Save Game State
        JSONObject gameState = new JSONObject();

        gameState.put("CurrentPlayer", GameState.getCurrentPlayer()); // Save Current Player
        gameState.put("TotalItems", GameState.getCountItems()); // Save Total Items

        // Save Shop Items
        JSONArray items = new JSONArray();
        Map<ProductCard, Integer> shopItemList = GameState.getShopItems();
        for (ProductCard card : shopItemList.keySet()) {
            JSONObject item = new JSONObject(); // Create new Item
            item.put("Name", card.getName()); // Save Card Name
            item.put("Count", shopItemList.get(card)); // Save Card Count
            items.add(item); // Add Item to List
        }
        gameState.put("Items", items);

        return gameState;
    }

    private JSONObject savePlayer(Player p){
        // Save Player
        JSONObject player = new JSONObject();

        player.put("Gulden", p.getJumlahGulden()); // Save Gulden
        player.put("TotalDeck", p.getDeck().getCardsInDeckCount()); // Save Total Deck
        player.put("TotalActive", p.getDeck().getActiveCardinHandCount()); // Save Total Active

        // Save Active Deck
        JSONArray activeDeck = new JSONArray();
        for (int i = 0; i < p.getDeck().getActiveCardinHandCount(); i++) {
            JSONObject card = new JSONObject(); // Create new Card
            card.put("Name", p.getDeck().getActiveCards()[i].getName()); // Save Card Name
            card.put("Location", "A0" + String.valueOf(i+1)); // Save Card Location
            activeDeck.add(card); // Add Card to List
        }
        player.put("ActiveDeck", activeDeck);

        // Save Ladang
        player.put("TotalLadang", p.getLadang().getCardinLadangCount()); // Save Total Ladang

        // Save Card Ladang
        JSONArray cardLadang = new JSONArray();
        Map<String, FarmResourceCard> tempLadang = p.getLadang().getAllCardwithLocationinLadang();
        for (String loc : tempLadang.keySet()) {
            JSONObject card = new JSONObject(); // Create new Card
            card.put("Name", tempLadang.get(loc).getName()); // Save Card Name
            card.put("Location", loc); // Save Card Location

            // Save Age or Weight
            if (tempLadang.get(loc) instanceof PlantCard) {
                card.put("AgeorWeight", ((PlantCard) tempLadang.get(loc)).getAge());
            } else if (tempLadang.get(loc) instanceof AnimalCard) {
                card.put("AgeorWeight", ((AnimalCard) tempLadang.get(loc)).getWeight());
            } else {
                card.put("AgeorWeight", 0);
            }

            card.put("TotalEffect", tempLadang.get(loc).getEffect().size()); // Save Total Effect

            // Save Effects
            JSONArray effects = new JSONArray();
            for (EffectType effect : tempLadang.get(loc).getEffect()) {
                effects.add(effect.toString()); // Add Effect to List
            }
            card.put("Effects", effects); // Save Effects

            cardLadang.add(card); // Add Card to List
        }
        player.put("CardLadang", cardLadang);

        return player;
    }
}
