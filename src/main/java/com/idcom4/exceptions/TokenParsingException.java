package com.idcom4.exceptions;

public class TokenParsingException extends IDException {

    public TokenParsingException(String message) {
        super(message);
    }

    public TokenParsingException(Throwable cause) {
        super(cause);
    }


    public TokenParsingException(String message, Throwable cause) {
        super(message,  cause);
    }
}
