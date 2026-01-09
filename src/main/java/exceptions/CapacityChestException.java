package exceptions;

public class CapacityChestException extends ChestException {
    public CapacityChestException(int limit) {
        super("Le coffre à atteint sa capacité maximal fixé à " + limit + " kg.");
    }
}