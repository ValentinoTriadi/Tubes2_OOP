package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javax.swing.event.ChangeListener;

public class Settings {

    public CheckBox muteCheckBox;

    public Slider volumeSlider;

    public Slider sfxSlider;

    @FXML
    public AnchorPane Root;

    public Settings() {

    }

    public void CloseSettings(ActionEvent actionEvent) {
        Root.setVisible(false);
    }
}
