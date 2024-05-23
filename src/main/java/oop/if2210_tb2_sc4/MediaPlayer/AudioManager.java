package oop.if2210_tb2_sc4.MediaPlayer;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AudioManager {

    private static AudioManager instance = null;
    private MediaPlayer backgroundMusicPlayer;
    private MediaPlayer sfxPlayer;

    // Variables volume
    private double backgroundMusicVolume;
    private double sfxVolume;
    private boolean isMuted = false;

    private double volumeBGMBeforeMute;
    private double volumeSFXBeforeMute;

    Map<String, String> cardSoundMap = new HashMap<>();

    public AudioManager() {
        backgroundMusicPlayer = null;
        sfxPlayer = null;
        backgroundMusicVolume = 1;
        sfxVolume = 1;
        volumeBGMBeforeMute =  backgroundMusicVolume;
        volumeSFXBeforeMute = sfxVolume;

        cardSoundMap.put("BERUANG", "Bear.wav");
        cardSoundMap.put("HIU_DARAT", "HiuDarat.wav");
        cardSoundMap.put("SAPI", "Cow.wav");
        cardSoundMap.put("DOMBA", "Sheep.wav");
        cardSoundMap.put("KUDA", "Horse.wav");
        cardSoundMap.put("AYAM", "Chicken.wav");
        cardSoundMap.put("BIJI_JAGUNG", "Gardening.wav");
        cardSoundMap.put("BIJI_LABU", "Gardening.wav");
        cardSoundMap.put("BIJI_STROBERI", "Gardening.wav");
        cardSoundMap.put("DELAY", "PotionDrink.wav");
        cardSoundMap.put("ACCELERATE", "PotionDrink.wav");
        cardSoundMap.put("PROTECTION", "PotionDrink.wav");
        cardSoundMap.put("TRAP", "BearTrap.wav");
        cardSoundMap.put("INSTANT HARVEST", "PotionDrink.wav");
        cardSoundMap.put("DESTROY", "PotionDrink.wav");
        cardSoundMap.put("Eating", "Eats.wav");
        cardSoundMap.put("Harvest", "Gardening.wav");
        cardSoundMap.put("Win", "Winning.wav");
        cardSoundMap.put("BearAttack", "a.wav");
    }

    public Map<String, String> getCardSoundMap() {
        return cardSoundMap;
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }
    // Method to play background music
    public void playBackgroundMusic(String musicFilePath) {
        if (instance.backgroundMusicPlayer != null) {
            instance.backgroundMusicPlayer.stop();
        }
        Media backgroundMusic = new Media(Objects.requireNonNull(getClass().getResource(musicFilePath)).toExternalForm());
        instance.backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
        instance.backgroundMusicPlayer.setOnEndOfMedia(() -> backgroundMusicPlayer.seek(Duration.ZERO));
        instance.backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        instance.backgroundMusicPlayer.setVolume(backgroundMusicVolume);
        instance.backgroundMusicPlayer.play();
    }

    // Method to mute
    public void mute() {
        if(instance.backgroundMusicPlayer == null || instance.sfxPlayer == null){
            if (isMuted){
                backgroundMusicVolume = volumeBGMBeforeMute;
                sfxVolume = volumeSFXBeforeMute;
                return;
            } else {
                volumeBGMBeforeMute = backgroundMusicVolume;
                volumeSFXBeforeMute = sfxVolume;

                backgroundMusicVolume = 0;
                sfxVolume = 0;
                return;
            }
        }

        if (isMuted) {
            instance.backgroundMusicPlayer.setVolume(backgroundMusicVolume);
            instance.sfxPlayer.setVolume(sfxVolume);
        } else {
            instance.backgroundMusicPlayer.setVolume(0);
            instance.sfxPlayer.setVolume(0);
        }

        instance.isMuted = !instance.isMuted;
    }

    public MediaPlayer getSFX() {
        return instance.sfxPlayer;
    }

    public MediaPlayer getBackgroundMusicPlayer() {
        return instance.backgroundMusicPlayer;
    }

    public boolean getIsMuted() {
        return instance.isMuted;
    }

    // Method to stop background music
    public void stopBackgroundMusic() {
        if (instance.backgroundMusicPlayer != null) {
            instance.backgroundMusicPlayer.stop();
        }
    }

    // Method to play sound effects
    public void playSFX(String sfxFilePath) {
        if (instance.sfxPlayer != null) {
            instance.sfxPlayer.stop();
        }
        Media sfx = new Media(Objects.requireNonNull(getClass().getResource(sfxFilePath)).toExternalForm());
        instance.sfxPlayer = new MediaPlayer(sfx);
        instance.sfxPlayer.setCycleCount(1);
        instance.sfxPlayer.setVolume(sfxVolume);
        instance.sfxPlayer.play();
    }

    // Method to stop sound effects
    public void stopSFX() {
        if (instance.sfxPlayer != null) {
            instance.sfxPlayer.stop();
        }
    }

    // Method to set the volume of the background music
    public void setBackgroundMusicVolume(double volume) {
        instance.backgroundMusicVolume = volume;
        if (instance.backgroundMusicPlayer == null) return;
        if (!instance.isMuted) instance.backgroundMusicPlayer.setVolume(volume);
    }

    // Method to set the volume of the sound effects
    public void setSFXVolume(double volume) {
        instance.sfxVolume = volume;
        if (instance.sfxPlayer == null) return;
        if (!instance.isMuted) instance.sfxPlayer.setVolume(volume);
    }
}