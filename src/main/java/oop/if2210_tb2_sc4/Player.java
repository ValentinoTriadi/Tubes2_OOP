package oop.if2210_tb2_sc4;

import oop.if2210_tb2_sc4.Exception.NotEnoughMoneyException;
import oop.if2210_tb2_sc4.card.Card;

public class Player {
    private Integer jumlah_gulden;
    private oop.if2210_tb2_sc4.Deck deck;
    private oop.if2210_tb2_sc4.Ladang ladang;

    public Player(){
        jumlah_gulden = 0;
        deck = new oop.if2210_tb2_sc4.Deck();
        ladang = new oop.if2210_tb2_sc4.Ladang();
    }

    public Integer getJumlahGulden(){
        return jumlah_gulden;
    }

    public void addGulden(Integer gulden){
        jumlah_gulden += gulden;
        System.out.println("Jumlah Gulden: "+ gulden);
    }

    public void reduceGulden(int gulden, String Cardname) throws  NotEnoughMoneyException{
        if(gulden > this.jumlah_gulden){
            throw new NotEnoughMoneyException(Cardname);
        }
        this.jumlah_gulden -= gulden;
    }

    public void setJumlahGulden(Integer gulden){
        jumlah_gulden = gulden;
    }

    public Integer getJumlahDeck(){
        return deck.getCardsInDeckCount();
    }

    public Integer getJumlahDeckActive(){
        return deck.getActiveCardinHandCount();
    }

    public Card[] getActiveDeck(){
        return deck.getActiveCards();
    }

    public Integer getJumlahKartuLadang(){
        return ladang.getCardinLadangCount();
    }

    public void setLadang(oop.if2210_tb2_sc4.Ladang ladang){
        this.ladang = ladang;
    }

    public void setDeck(oop.if2210_tb2_sc4.Deck deck){
        this.deck = deck;
    }

    public oop.if2210_tb2_sc4.Ladang getLadang(){
        return ladang;
    }

    public oop.if2210_tb2_sc4.Deck getDeck(){
        return deck;
    }
}