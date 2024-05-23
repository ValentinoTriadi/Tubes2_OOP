package oop.if2210_tb2_sc4.UI;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class EndPanel {

    public Label title;
    public AnchorPane Root;
    public Label message;
    public Label player;
    private GameWindowController root;
    public EndPanel() {
    }

    public void setRoot(GameWindowController root) {
        this.root = root;
    }

    public void setData(String title, String user, String message){
        this.title.setText(title);
        this.message.setText(message);
        if(Objects.equals(user, "")){
            this.message.setTranslateY(-30);
        }else{
            this.message.setTranslateY(0);
        }
        this.player.setText(user);
    }

    public void ShowEndScreen(){
        Root.setVisible(true);
    }

    public void StartNewGame(ActionEvent actionEvent) {
        try{
            root.initialize();
            Root.setVisible(false);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
