package com.idcom4.compiler.lexer.tokens.values;

public class StringValueToken extends ValueToken<String> {
    public StringValueToken(String value, String slice, int indexEnd) {
        super(value, slice, indexEnd);
    }
}
