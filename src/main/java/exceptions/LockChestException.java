package exceptions;

public class LockChestException extends ChestException {
    public LockChestException() {
        super("Le coffre est verrouillé.");
    }
}