package com.idcom4.exceptions;

public class GrammarException extends CompilationException {

    public GrammarException(String message) {
        super(message);
    }

    public GrammarException(Throwable cause) {
        super(cause);
    }


    public GrammarException(String message, Throwable cause) {
        super(message,  cause);
    }
}
