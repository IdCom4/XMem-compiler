package com.idcom4.compiler.lexer.tokens.values;

public class NumberValueToken extends ValueToken<Integer> {
    public NumberValueToken(int value, String slice, int indexEnd) {
        super(value, slice, indexEnd);
    }
}
