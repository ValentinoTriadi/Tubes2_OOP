package oop.if2210_tb2_sc4.Exception;

public class FolderNotExistException extends GameException{
    public FolderNotExistException(String message){
        super("Folder Not Exist", message);
    }
}
