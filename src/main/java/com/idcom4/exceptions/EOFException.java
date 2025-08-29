package com.idcom4.exceptions;

public class EOFException extends GrammarException {

    public EOFException() {
        super("unexpected end of file");
    }

    public EOFException(String message) {
        super(message);
    }

    public EOFException(Throwable cause) {
        super(cause);
    }


    public EOFException(String message, Throwable cause) {
        super(message,  cause);
    }
}
