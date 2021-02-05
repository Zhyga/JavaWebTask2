package by.epam.exception;

public class CustomParserException extends Exception {
    public CustomParserException() {
    }

    public CustomParserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomParserException(String message) {
        super(message);
    }

    public CustomParserException(Throwable cause) {
        super(cause);
    }
}
