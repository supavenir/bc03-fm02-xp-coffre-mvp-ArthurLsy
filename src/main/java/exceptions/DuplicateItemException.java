package exceptions;

public class DuplicateItemException extends ItemException {
    public DuplicateItemException() {
        super("L'item que vous tentez d'ajouter existe deja dans le coffre.");
    }
}