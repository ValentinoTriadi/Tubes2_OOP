package oop.if2210_tb2_sc4.MediaPlayer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import org.testfx.framework.junit5.ApplicationTest;

class AudioManagerTest extends ApplicationTest {

    @Test
    void playBackgroundMusic() {
        AudioManager audioManager = new AudioManager();

        // Test playBackgroundMusic
        audioManager.playBackgroundMusic("b.wav");
        assertNotNull(audioManager.getBackgroundMusicPlayer());
    }

    @Test
    void mute() {
        AudioManager audioManager = new AudioManager();

        // Test mute
        audioManager.mute();
        assertTrue(audioManager.getIsMuted());
    }

    @Test
    void stopBackgroundMusic() {
        AudioManager audioManager = new AudioManager();

        // Test stopBackgroundMusic
        audioManager.stopBackgroundMusic();
        assertNull(audioManager.getBackgroundMusicPlayer());
    }

    @Test
    void playSFX() {
        AudioManager audioManager = new AudioManager();

        // Test playSFX
        audioManager.playSFX("a.wav");
        assertNotNull(audioManager.getSFX());
    }

    @Test
    void stopSFX() {
        AudioManager audioManager = new AudioManager();

        // Setup
        audioManager.playSFX("a.wav");
        assert audioManager.getSFX() != null;

        // Test stopSFX
        audioManager.stopSFX();
        assertNull(audioManager.getSFX());
    }

    @Test
    void setBackgroundMusicVolume() {
        AudioManager audioManager = new AudioManager();

        // Test setBackgroundMusicVolume

        // Setup
        audioManager.playBackgroundMusic("b.wav");
        assert audioManager.getBackgroundMusicPlayer() != null;

        // Test setBackgroundMusicVolume
        audioManager.setBackgroundMusicVolume(0.5);

        // Check if the volume is set correctly
        assertEquals(0.5, audioManager.getBackgroundMusicPlayer().getVolume());
    }

    @Test
    void setSFXVolume() {
        AudioManager audioManager = new AudioManager();

        // Test setSFXVolume

        // Setup
        audioManager.playSFX("a.wav");
        assert audioManager.getSFX() != null;

        // Test setSFXVolume
        audioManager.setSFXVolume(0.5);

        // Check if the volume is set correctly
        assertEquals(0.5, audioManager.getSFX().getVolume());
    }
}