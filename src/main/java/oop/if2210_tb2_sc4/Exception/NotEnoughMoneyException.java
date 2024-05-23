package oop.if2210_tb2_sc4.Exception;

import oop.if2210_tb2_sc4.util.StringUtil;

public class NotEnoughMoneyException extends GameException {
    public NotEnoughMoneyException(String cardName) {
        super("Not enough Gulden To Buy", "Not Enough Gulden To Buy "+ StringUtil.toUpperSnakeCase(cardName));
    }
}
