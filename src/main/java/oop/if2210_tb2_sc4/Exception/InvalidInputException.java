package oop.if2210_tb2_sc4.Exception;

public class InvalidInputException extends GameException{
    public InvalidInputException(String message){
        super("Invalid Input",message);
    }
}
