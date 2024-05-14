package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import oop.if2210_tb2_sc4.save_load.LoadTXT;
import oop.if2210_tb2_sc4.save_load.Save;
import oop.if2210_tb2_sc4.save_load.SaveTXT;

import java.io.IOException;

public class SaveUI{
    private SaveLoad controller;
    private Save saver;
    private AnchorPane scene;
    public SaveUI(SaveLoad controller){
        this.controller = controller;
    }

    public void initialize() {

        controller.title.setText("Save The Game");

        controller.message.setText("Click The Button to Save The Data");
        System.out.println("Load UI");

        controller.clickButton.setOnMouseClicked(event -> {
            OnSaveLoad(event);
            System.out.println("Button clicked!");
        });

    }

    private void handleTextInputChange(String choice){
        String path = controller.getFolderName();

        switch (choice.toLowerCase()) {
            case "txt":
                saver = new SaveTXT(path, GameWindowController.getPlayer1().getPlayerData(), GameWindowController.getPlayer2().getPlayerData());
                break;
            case "yaml":
                // Handle YAML choice
                System.out.println("YAML chosen");
                break;
            case "json":
                // Handle JSON choice
                System.out.println("JSON chosen");
                break;
            default:
                saver = null;
                break;
        }
    }

    public SaveLoad getController(){return  controller; }

    public void OnSaveLoad(MouseEvent mouseEvent) {
        // Get the text from the ComboBox
        String newText = controller.choice.getValue();

        // Do something with the new text, such as invoking a function
        handleTextInputChange(newText);
        if(saver == null){
            MessageBox.showErrorMessage("Non Exist Choice", "Tolong pilih salah satu jenis file yang akan disave");
            return;
        }
        controller.OnSaveLoad();
        GameWindowController gamewindow;
        saver.save();
    }
}
