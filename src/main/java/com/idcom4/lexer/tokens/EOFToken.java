package com.idcom4.lexer.tokens;

public class EOFToken extends Token {
    public EOFToken(int indexEnd) {
        super("", indexEnd);
    }
}
