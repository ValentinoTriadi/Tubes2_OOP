package oop.if2210_tb2_sc4;

import oop.if2210_tb2_sc4.card.FarmResourceCard;

import java.util.Map;
import java.util.HashMap;

public class Ladang {
    public static final int LADANG_COLUMN = 5;
    public static final int LADANG_ROW = 4;

    private FarmResourceCard[][] ladang;

    public Ladang() {
        ladang = new FarmResourceCard[LADANG_ROW][LADANG_COLUMN];
    }

    public FarmResourceCard[][] getLadang() {
        return ladang;
    }

    public void setLadang(FarmResourceCard[][] ladang) {
        this.ladang = ladang;
    }

    public FarmResourceCard getCard(int row, int column) {
        return ladang[row][column];
    }

    public void setCard(int row, int column, FarmResourceCard card) {

        ladang[row][column] = card;
    }

    public void setCard(String slot, FarmResourceCard card) {
        int row = slot.charAt(0) - 'A';
        int column = Integer.parseInt(slot.substring(1)) - 1;
        ladang[row][column] = card;
    }

    public void removeCard(int row, int column) {
        ladang[row][column] = null;
    }

    public boolean isFull() {
        for (int i = 0; i < LADANG_ROW; i++) {
            for (int j = 0; j < LADANG_COLUMN; j++) {
                if (ladang[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (int i = 0; i < LADANG_ROW; i++) {
            for (int j = 0; j < LADANG_COLUMN; j++) {
                if (ladang[i][j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getCardinLadangCount() {
        int count = 0;
        for (int i = 0; i < LADANG_ROW; i++) {
            for (int j = 0; j < LADANG_COLUMN; j++) {
                if (ladang[i][j] != null) {
                    count++;
                }
            }
        }
        return count;
    }

    public FarmResourceCard[] getCardListinLadang() {
        FarmResourceCard[] cardList = new FarmResourceCard[getCardinLadangCount()];
        int index = 0;
        for (int i = 0; i < LADANG_ROW; i++) {
            for (int j = 0; j < LADANG_COLUMN; j++) {
                if (ladang[i][j] != null) {
                    cardList[index] = ladang[i][j];
                    index++;
                }
            }
        }
        return cardList;
    }

    public Map<String, FarmResourceCard> getAllCardwithLocationinLadang() {
        Map<String, FarmResourceCard> cardList = new HashMap<>();
        for (int i = 0; i < LADANG_ROW; i++) {
            for (int j = 0; j < LADANG_COLUMN; j++) {
                if (ladang[i][j] != null) {
                    cardList.put((char) (i + 'A') + "0" + (j+1), ladang[i][j]);
                }
            }
        }
        return cardList;
    }
    public void removeCard(String location) {
        int row = location.charAt(0) - 'A';
        int column = Integer.parseInt(location.substring(1)) - 1;
        ladang[row][column] = null;
    }
}