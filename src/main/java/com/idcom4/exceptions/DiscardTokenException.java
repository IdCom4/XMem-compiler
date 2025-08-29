package com.idcom4.exceptions;

public class DiscardTokenException extends CompilationException {

    public DiscardTokenException(String message) {
        super(message);
    }

    public DiscardTokenException(Throwable cause) {
        super(cause);
    }


    public DiscardTokenException(String message, Throwable cause) {
        super(message,  cause);
    }
}
