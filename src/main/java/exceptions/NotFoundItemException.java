package exceptions;

public class NotFoundItemException extends ItemException {
    public NotFoundItemException() {
        super("L'item n'as pas été trouvé.");
    }
}