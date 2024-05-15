package oop.if2210_tb2_sc4.Exception;

import oop.if2210_tb2_sc4.UI.MessageBox;

public class GameException extends Exception{
    private final String title;
    private final String message;
    public GameException(String title, String message){
        this.title = title;
        this.message = message;
    }
    public void ShowErrorPanel(){
        MessageBox.showErrorMessage(title, message);
    }
}

