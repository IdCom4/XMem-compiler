package com.idcom4.exceptions;

import com.idcom4.lexer.tokens.BadToken;

public class BadTokenException extends TokenParsingException {

    public BadTokenException(BadToken token) {
        super("bad token at index " + token.getIndexStart() + ": " + token.getSlice());
    }

    public BadTokenException(String message) {
        super(message);
    }

    public BadTokenException(Throwable cause) {
        super(cause);
    }


    public BadTokenException(String message, Throwable cause) {
        super(message,  cause);
    }
}
