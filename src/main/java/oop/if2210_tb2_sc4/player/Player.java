package oop.if2210_tb2_sc4.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    private Integer jumlah_gulden;
    private Integer jumlah_deck;
    private Integer jumlah_deck_active;
    private Map<String, String> active_deck;
    private Integer jumlah_kartu_ladang;
    private List<Map<String,Object>> kartu_ladang;

    public Player(){
        jumlah_gulden = 0;
        jumlah_deck = 0;
        jumlah_deck_active = 0;
        jumlah_kartu_ladang = 0;
        active_deck = new HashMap<String, String>();
        kartu_ladang = new ArrayList<>();
    }

    public Integer getJumlahGulden(){
        return jumlah_gulden;
    }

    public void setJumlahGulden(Integer gulden){
        jumlah_gulden = gulden;
    }

    public Integer getJumlahDeck(){
        return jumlah_deck;
    }

    public void setJumlahDeck(Integer deck){
        jumlah_deck = deck;
    }

    public Integer getJumlahDeckActive(){
        return jumlah_deck_active;
    }

    public void setJumlahDeckActive(Integer deck){
        jumlah_deck_active = deck;
    }

    public Map<String, String> getActiveDeck(){
        return active_deck;
    }

    public void setActiveDeck(Map<String, String> deck){
        active_deck = deck;
    }

    public Integer getJumlahKartuLadang(){
        return jumlah_kartu_ladang;
    }

    public void setJumlahKartuLadang(Integer kartu){
        jumlah_kartu_ladang = kartu;
    }

    public List<Map<String,Object>> getKartuLadang(){
        return kartu_ladang;
    }

    public void setKartuLadang(List<Map<String,Object>> kartu){
        kartu_ladang = kartu;
    }
}