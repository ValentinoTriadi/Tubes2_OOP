package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

public class Settings {

    public CheckBox muteCheckBox;

    public Slider volumeSlider;

    public Slider sfxSlider;

    @FXML
    public AnchorPane Root;

    public Settings() {
    }

    public void initialize() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = newValue.doubleValue() / volumeSlider.getMax();
            String style = String.format("-fx-background-color: linear-gradient(to right, #4caf50 %f%%, #e0e0e0 %f%%);",
                    percentage * 100, percentage * 100);
            volumeSlider.lookup(".track .fill").setStyle(style);
        });

        sfxSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            double percentage = newValue.doubleValue() / sfxSlider.getMax();
            String style = String.format("-fx-background-color: linear-gradient(to right, #4caf50 %f%%, #e0e0e0 %f%%);",
                    percentage * 100, percentage * 100);
            sfxSlider.lookup(".track .fill").setStyle(style);
        });
    }


    public void CloseSettings(ActionEvent actionEvent) {
        Root.setVisible(false);
    }
}
