package oop.if2210_tb2_sc4.player;

import oop.if2210_tb2_sc4.deck.Deck;
import oop.if2210_tb2_sc4.ladang.Ladang;
import oop.if2210_tb2_sc4.card.Card;

public class Player {
    private Integer jumlah_gulden;
    private Deck deck;
    private Ladang ladang;

    public Player(){
        jumlah_gulden = 0;
        deck = new Deck();
        ladang = new Ladang();
    }

    public Integer getJumlahGulden(){
        return jumlah_gulden;
    }

    public void addGulden(Integer gulden){
        jumlah_gulden += gulden;
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

    public void setLadang(Ladang ladang){
        this.ladang = ladang;
    }

    public void setDeck(Deck deck){
        this.deck = deck;
    }

    public Ladang getLadang(){
        return ladang;
    }

    public Deck getDeck(){
        return deck;
    }


}