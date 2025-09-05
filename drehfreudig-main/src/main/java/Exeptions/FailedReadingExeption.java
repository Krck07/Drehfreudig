package Exeptions;

public class FailedReadingExeption extends RuntimeException {
    public FailedReadingExeption(String message) {
        super(message);
    }
}
