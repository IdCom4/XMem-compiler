package com.idcom4.compiler.lexer.tokens.symbols;

import com.idcom4.lexer.tokens.Token;

public abstract class SymbolToken extends Token {
    public SymbolToken(String slice, int indexEnd) {
        super(slice, indexEnd);
    }
}
