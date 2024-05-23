package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import oop.if2210_tb2_sc4.MediaPlayer.AudioManager;

import javax.swing.event.ChangeListener;

public class Settings {

    public CheckBox muteCheckBox;

    public Slider volumeSlider;

    public Slider sfxSlider;

    @FXML
    public AnchorPane Root;

    public Settings() {

    }
    @FXML
    public void initialize() {
        // Add a listener to the slider's value property
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioManager.getInstance().setBackgroundMusicVolume((double)newValue);
        });
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            AudioManager.getInstance().setSFXVolume((double)newValue);
        });
        muteCheckBox.setSelected(false);
        muteCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            AudioManager.getInstance().mute();
        });

    }

    public void CloseSettings(ActionEvent actionEvent) {
        Root.setVisible(false);
    }
}
