package oop.if2210_tb2_sc4.Exception;

public class ZeroItemStockException extends GameException {
    public ZeroItemStockException() {
        super("Zero item stock", "The stock of the item is zero. Cannot perform this operation.");
    }
}
