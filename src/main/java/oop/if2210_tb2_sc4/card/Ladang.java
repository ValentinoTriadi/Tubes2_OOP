package oop.if2210_tb2_sc4.card;


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
}
