package exceptions;

public class DuplicateItemException extends Exception {
    public DuplicateItemException(String itemName) {
        super("L'item '" + itemName + "' existe déjà dans le coffre");
    }
}