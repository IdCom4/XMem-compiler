package com.idcom4.compiler.lexer.tokens.symbols;

public class CloseArraySymbolToken extends SymbolToken {

    public static final String SYMBOL = "]";

    public CloseArraySymbolToken(int indexEnd) {
        super(SYMBOL, indexEnd);
    }
}
