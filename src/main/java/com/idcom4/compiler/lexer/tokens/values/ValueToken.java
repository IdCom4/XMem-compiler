package com.idcom4.compiler.lexer.tokens.values;

import com.idcom4.lexer.tokens.Token;

public abstract class ValueToken<T> extends Token {
    protected final T value;

    public ValueToken(T value, String slice, int indexEnd) {
        super(slice, indexEnd);
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }
}
