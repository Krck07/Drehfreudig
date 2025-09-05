package Exeptions;

public class DivByZeroExeption extends RuntimeException {
    public DivByZeroExeption() {
        super("You have divided by Zero somewhere.");
    }
}
