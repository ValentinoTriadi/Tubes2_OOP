package oop.if2210_tb2_sc4.Exception;

public class FullActiveHandsException extends GameException {
    public FullActiveHandsException() {
        super("Full active hands", "The active hands are already full. Cannot add more items.");
    }
}
