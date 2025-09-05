package Exeptions;

public class FailedReadingException extends RuntimeException {
    public FailedReadingException(String message) {
        super(message);
    }
}
