package com.idcom4.compiler.lexer.tokens.properties;

import com.idcom4.lexer.tokens.Token;

public abstract class PropertyToken extends Token {
    public PropertyToken(String slice, int indexEnd) {
        super(slice, indexEnd);
    }
}
