package Exeptions;

public class DivByZeroException extends RuntimeException {
    public DivByZeroException() {
        super("You have divided by Zero somewhere.");
    }
}
