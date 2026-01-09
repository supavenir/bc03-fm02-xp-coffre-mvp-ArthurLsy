package exceptions;

public class BadWeightItemException extends ItemException {

    public BadWeightItemException() {
        super("Bad weight item");
    }
}